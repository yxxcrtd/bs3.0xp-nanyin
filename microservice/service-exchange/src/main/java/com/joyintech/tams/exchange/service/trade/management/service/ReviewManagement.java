package com.joyintech.tams.exchange.service.trade.management.service;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.exchange.common.bean.*;
import com.joyintech.tams.exchange.common.constant.*;
import com.joyintech.tams.exchange.common.db.domain.Entrust;
import com.joyintech.tams.exchange.common.db.domain.EntrustInterface;
import com.joyintech.tams.exchange.common.db.domain.Order;
import com.joyintech.tams.exchange.common.microservice.ServiceApplication;
import com.joyintech.tams.exchange.common.microservice.TradeServiceURL;
import com.joyintech.tams.exchange.datacommon.db.service.EntrustService;
import com.joyintech.tams.exchange.datacommon.db.service.InstrumentService;
import com.joyintech.tams.exchange.datacommon.db.service.IntegratedEntrustService;
import com.joyintech.tams.exchange.datacommon.db.service.OrderService;
import com.joyintech.tams.exchange.service.trade.processor.TradeHelper;
import com.joyintech.tams.exchange.service.trade.processor.constants.Constants;
import com.joyintech.tams.exchange.service.trade.processor.msg.MsgReceiverManager;
import com.joyintech.tams.exchange.service.trade.processor.msg.MsgType;
import com.joyintech.tams.exchange.service.trade.processor.msg.TradeMsgReceiver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class ReviewManagement extends BaseService {
    private static final Logger logger = LoggerFactory.getLogger(ReviewManagement.class);
    // 记录已经登录的账号
    private static final Map<String, WorkflowBean> reviewMap = Maps.newConcurrentMap();
    @Autowired
    private EntrustService entrustService;
    @Autowired
    private IntegratedEntrustService integratedEntrustService;
    @Autowired
    private MsgReceiverManager msgReceiverManager;
    @Autowired
    private OrderService orderService;
    @Autowired
    private TradeHelper tradeHelper;
    @Autowired
    private InvestRiskManagement investRiskManagement;
    @Autowired
    private InstrumentService instrumentService;
    /**
     * 是否单体服务
     */
    @Value("${application.micorservice.contextPath.single:false}")
    private boolean isSingle;

    public static WorkflowBean getReviewMap(String id) {
        return reviewMap.get(id);
    }

    public static void putReviewMap(String id, WorkflowBean workflowBean) {
        reviewMap.put(id, workflowBean);
    }

    public static void removeReviewMap(String id) {
        reviewMap.remove(id);
    }


    public ResultBean handleOrder(List<Entrust> entrusts, String approveStatus, String approveReason) {
        entrusts.forEach(entrust -> {
            int entrustStatus = ((EntrustInterface) entrust).getEntrustStatus();
            // 公平订单撤销时不会发起撤审,不会作废复核,内部先撤单
            if (entrustStatus == EntrustStatus.CANCELED.value) {
                entrust.setErrorMsg("委托已撤，不允许下单");
                entrust.setCancelVolume(entrust.getEntrustVolume());
                investRiskManagement.riskRelease(entrust);
            } else if (entrustStatus == EntrustStatus.PENDING_REVIEW.value) {
                TradeMsgReceiver tradeMsgReceiver = msgReceiverManager.getMsgReceiverByInstruction(entrust.getInstructionId());
                tradeMsgReceiver.addMsg(MsgType.EXAMINE_REVIEW, new ExamineOrder(approveStatus, approveReason, entrust, Constants.PROCESS_TYPE_ENTRUST));
            } else {
                entrust.setErrorMsg("指令状态不是待审批");
            }
        });
        return new ResultBean(ErrorCode.SUCCESS, entrusts);
    }

    public ResultBean handleBatch(Order order, String approveStatus, String approveReason) {
        if (order.getOrderStatus() != OrderStatus.PENDING_REVIEW.value) {
            order.setErrorMsg("订单状态不是待审批");
            return new ResultBean(ErrorCode.INSTRUCTIONS_STATUS_ERROR, order);
        }
        // 复核通过
        if (ApproveType.EXAMINE_APPROVAL.value.equals(approveStatus)) {
            tradeHelper.handleOrder(order, null);
        } else {
            //复核拒绝
            order.setErrorMsg(approveReason);
            order.setOrderStatus(OrderStatus.NOT_REVIEW.value);
        }
        return new ResultBean(ErrorCode.SUCCESS, order);
    }

    public ResultBean handleNormal(EntrustInterface entrust, String approveStatus, String approveReason, int type) {
        int status = entrust.getEntrustStatus();
        if (status == EntrustStatus.CANCELED.value) {
            entrust.setErrorMsg("委托已撤，不允许下单");
            entrust.setCancelVolume(entrust.getEntrustVolume());
            investRiskManagement.riskRelease(entrust);
            return new ResultBean(ErrorCode.SUCCESS, entrust);
        }
        if (status == EntrustStatus.PENDING_REVIEW.value) {
            TradeMsgReceiver tradeMsgReceiver = msgReceiverManager.getMsgReceiverByInstruction(entrust.getInstructionId());
            tradeMsgReceiver.addMsg(MsgType.EXAMINE_REVIEW, new ExamineOrder(approveStatus, approveReason, entrust, type));
            return new ResultBean(ErrorCode.SUCCESS, entrust);
        } else {
            entrust.setErrorMsg("指令状态不是待审批");
            return new ResultBean(ErrorCode.INSTRUCTIONS_STATUS_ERROR, entrust);
        }
    }

    /**
     * 处理复核审批结果
     *
     * @param
     * @return
     */
    public ResultBean handleExamine(String eventId, String approveStatus, String approveReason) {
        EntrustInterface entrust = null;
        List<Entrust> entrusts = null;
        Order order = null;
        int type = 0;

        if (eventId.startsWith(IDPrefix.NORMAL.value)) {
            entrust = entrustService.findById(eventId);
            type = Constants.PROCESS_TYPE_ENTRUST;
        } else if (eventId.startsWith(IDPrefix.BULK.value)) {
            entrust = integratedEntrustService.findById(eventId);
            type = Constants.PROCESS_TYPE_INTEGRATED;
        } else if (eventId.startsWith(IDPrefix.ORDER.value)) {
            order = orderService.findById(eventId);
            entrusts = entrustService.queryEntrustByOrderId(eventId);
            if (Objects.nonNull(order) && BusinessFlag.BATCH.value.equals(order.getBusinessFlag())) {
                type = Constants.PROCESS_TYPE_BATCH_ORDER;
            } else {
                type = Constants.PROCESS_TYPE_FIRE_ORDER;
            }
        }

        if (Objects.isNull(entrust) && Objects.isNull(entrusts) && Objects.isNull(order)) {
            return new ResultBean(ErrorCode.INSTRUCTIONS_NOT_EXIST, eventId);
        }

        if (ApproveType.EXAMINE_REFUSE.value.equals(approveStatus) && Strings.isNullOrEmpty(approveReason)) {
            entrust.setErrorMsg("拒绝原因不可以为空");
            return new ResultBean(ErrorCode.REQ_PARAMETER_ERROR, entrust);
        }

        // 根据类型分别处理委托
        if (type == Constants.PROCESS_TYPE_FIRE_ORDER) {
            // 处理公平交易
            return handleOrder(entrusts, approveStatus, approveReason);
        } else if (type == Constants.PROCESS_TYPE_BATCH_ORDER) {
            // 处理批量交易
            return handleBatch(order, approveStatus, approveReason);
        } else {
            return handleNormal(entrust, approveStatus, approveReason, type);
        }
    }

    public boolean startWorkflow(TrdExchangeEntrustDto dto) {
        if (isSingle) {
            return simpleRestTemplate.postForObject(ServiceApplication.TRADE_SERVICE_NAME + TradeServiceURL.startReviewEntrustWorkflow, dto, Boolean.class);
        } else {
            return restTemplate.postForObject(ServiceApplication.TRADE_SERVICE_NAME + TradeServiceURL.startReviewEntrustWorkflow, dto, Boolean.class);
        }
    }

    public boolean startWorkflow(TrdExchangeIntegratedEntrustDto dto) {
        if (isSingle) {
            return simpleRestTemplate.postForObject(ServiceApplication.TRADE_SERVICE_NAME + TradeServiceURL.startReviewIntegratedEntrustWorkflow, dto, Boolean.class);
        } else {
            return restTemplate.postForObject(ServiceApplication.TRADE_SERVICE_NAME + TradeServiceURL.startReviewIntegratedEntrustWorkflow, dto, Boolean.class);
        }
    }
}
