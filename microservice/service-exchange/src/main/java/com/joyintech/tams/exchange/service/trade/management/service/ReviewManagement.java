package com.joyintech.tams.exchange.service.trade.management.service;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.exchange.common.bean.*;
import com.joyintech.tams.exchange.common.constant.*;
import com.joyintech.tams.exchange.common.constant.joyin.DictCodeListExt3;
import com.joyintech.tams.exchange.common.db.domain.Entrust;
import com.joyintech.tams.exchange.common.db.domain.EntrustInterface;
import com.joyintech.tams.exchange.common.db.domain.Instrument;
import com.joyintech.tams.exchange.common.db.domain.Order;
import com.joyintech.tams.exchange.common.utils.JsonUtils;
import com.joyintech.tams.exchange.datacommon.db.service.EntrustService;
import com.joyintech.tams.exchange.datacommon.db.service.InstrumentService;
import com.joyintech.tams.exchange.datacommon.db.service.IntegratedEntrustService;
import com.joyintech.tams.exchange.datacommon.db.service.OrderService;
import com.joyintech.tams.exchange.service.trade.processor.TradeHelper;
import com.joyintech.tams.exchange.service.trade.processor.constants.Constants;
import com.joyintech.tams.exchange.service.trade.processor.msg.MsgReceiverManager;
import com.joyintech.tams.exchange.service.trade.processor.msg.MsgType;
import com.joyintech.tams.exchange.service.trade.processor.msg.TradeMsgReceiver;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @ClassName ApprovalManagement
 * @Description TODO
 * @Author swwan
 * @Date 2020/7/29 17:16
 * @Version 1.0
 **/
@Service("ReviewManagement_dtx")
public class ReviewManagement {
    private static final Logger logger = LoggerFactory.getLogger(ReviewManagement.class);

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

    // 记录已经登录的账号
    private static final Map<String, WorkflowBean> reviewMap = Maps.newConcurrentMap();

    public static WorkflowBean getReviewMap(String id) {
        return reviewMap.get(id);
    }

    public static void putReviewMap(String id, WorkflowBean workflowBean) {
        reviewMap.put(id, workflowBean);
    }

    public static void removeReviewMap(String id) {
        reviewMap.remove(id);
    }

    @DTXTransaction(
            propagation = Propagation.REQUIRES_NEW
    )
    @Transactional(
            rollbackFor = {Exception.class}
    )
    public boolean startWorkflow(TrdExchangeDto dto) {
        ProcessParameter<TrdExchangeDto> workFlowPatameter = getWorkFlowPatameter(dto);
        try {
            WorkFlowUtil.startProcess(workFlowPatameter.getWorkFlowParameter(), dto.getUsers(), "", dto.getUserId(),
                    OperationType.NEW, true, dto);
            logger.info("start review workflow success.");
            return true;
        }
        catch (Exception e) {
            logger.error("start review workflow failed with error : [{}]",e.getMessage());
            return false;
        }
    }



    /**
     * 设置流程参数
     *
     * @param dto
     * @return
     */
    public ProcessParameter<TrdExchangeDto> getWorkFlowPatameter(TrdExchangeDto dto) {
        dto.setWorkflowId(null);
        dto.setOperateType("submit");

        ProcessParameter<TrdExchangeDto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<TrdExchangeDto> workFlowParameter = new WorkFlowParameter<>();
        processParameter.setWorkFlowParameter(workFlowParameter);
        // 画面ID
        processParameter.setPageId("TrdExchange002");
        // 画面元素ID
        workFlowParameter.setElementId(dto.getElementId());
        processParameter.setOperationType(OperationType.NEW);
        workFlowParameter.setBusinessCode(dto.getId());
        // 处理Service名
        workFlowParameter.setServiceName("TrdExchangePage002Service");
        workFlowParameter.setPageId("TrdExchange002");
        workFlowParameter.setBusinessType(DictCodeListExt3.共通业务类型_场内交易下单.getValue());
        // 组合名称 资金账号 证券代码  证券名称  委托方向  委托价格 委托数量  委托金额  申报类型  清算速度  净价价格  全价价格 到期收益率  本方交易员 对手交易商 对手交易员  交易对手 约定号
        workFlowParameter.setComment(
                entity -> DictCodeListExt3.共通业务类型_场内交易下单.getCodeName()
                        + (StringUtil.isNotEmpty(entity.getStockCode()) ?
                        (GlobalConstant.SPLIT + entity. getStockName()
                                + GlobalConstant.VERTICAL_LINE + entity.getStockCode()) : "")
                        + (StringUtil.isNotEmpty(entity.getEntrustVolume()) ?
                        (GlobalConstant.SPLIT + "委托数量"
                                + GlobalConstant.OPEN_PARENTHESIS + entity.getEntrustVolume() + GlobalConstant.CLOSE_PARENTHESIS): "")
                        + (StringUtil.isNotEmpty(entity.getEntrustPrice()) ?
                        (GlobalConstant.SPLIT + "委托价格"
                                + GlobalConstant.OPEN_PARENTHESIS + entity.getEntrustPrice() + GlobalConstant.CLOSE_PARENTHESIS): ""));
        return processParameter;
    }

    public int getEntrustType(String eventId, EntrustInterface entrust, List<Entrust> entrusts) {
        int type = 0;
        if (eventId.startsWith(IDPrefix.NORMAL.value)) {
            entrust = entrustService.findById(eventId);
            type = Constants.PROCESS_TYPE_ENTRUST;
        } else if (eventId.startsWith(IDPrefix.BULK.value)) {
            entrust = integratedEntrustService.findById(eventId);
            type = Constants.PROCESS_TYPE_INTEGRATED;
        } else if (eventId.startsWith(IDPrefix.ORDER.value)) {//处理公平交易
            entrusts = entrustService.queryEntrustByOrderId(eventId);
            type = Constants.PROCESS_TYPE_FIRE_ORDER;
        }
        return type;
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

    @DTXTransaction(propagation = Propagation.REQUIRES_NEW)
    @Transactional(rollbackFor = {Exception.class})
    public boolean startWorkflow(TrdExchangeEntrustDto dto) {
        logger.info("start review workflow [{}]", JsonUtils.getJsonFromObject(dto));
        ProcessParameter<TrdExchangeEntrustDto> workFlowPatameter = getWorkFlowPatameter(dto);
        try {
            WorkFlowUtil.startProcess(workFlowPatameter.getWorkFlowParameter(), dto.getUsers(), dto.getRoles()
                    , Objects.nonNull(dto.getOrderPlaceBean()) ? dto.getOrderPlaceBean().getUserId() : dto.getEntrustList().get(0).getUserId(),
                    OperationType.NEW, true, dto);
            logger.info("start review workflow success.");
            return true;
        }
        catch (Exception e) {
            logger.warn("start review workflow failed with error : ", e);
            return false;
        }
    }

    /**
     * 设置流程参数
     *
     * @param dto
     * @return
     */
    public ProcessParameter<TrdExchangeEntrustDto> getWorkFlowPatameter(TrdExchangeEntrustDto dto) {
        dto.setWorkflowId(null);
        dto.setOperateType("submit");

        ProcessParameter<TrdExchangeEntrustDto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<TrdExchangeEntrustDto> workFlowParameter = new WorkFlowParameter<>();
        processParameter.setWorkFlowParameter(workFlowParameter);
        // 画面ID
        processParameter.setPageId("TrdExchange002");
        // 画面元素ID
        workFlowParameter.setElementId(dto.getElementId());
        processParameter.setOperationType(OperationType.NEW);

        // 处理Service名
        workFlowParameter.setServiceName("TrdExchangePage002Service");
        workFlowParameter.setPageId("TrdExchange002");
        workFlowParameter.setBusinessType(DictCodeListExt3.共通业务类型_场内交易下单.getValue());
        // 组合名称 资金账号 证券代码  证券名称  委托方向  委托价格 委托数量  委托金额  申报类型  清算速度  净价价格  全价价格 到期收益率  本方交易员 对手交易商 对手交易员  交易对手 约定号
        if (Objects.nonNull(dto.getOrderPlaceBean())) {
            // 批量
            workFlowParameter.setBusinessCode(dto.getEventId());
            workFlowParameter.setComment(
                    entity -> (StringUtil.isNotEmpty(entity.getOrderPlaceBean().getStockCode()) ?
                            getStockNameFromInstrument(entity.getOrderPlaceBean().getStockCode())
                                    + GlobalConstant.VERTICAL_LINE + (entity.getOrderPlaceBean().getStockCode()) : "")
                            + GlobalConstant.SPLIT + "订单编号"
                            + GlobalConstant.OPEN_PARENTHESIS + entity.getEventId() + GlobalConstant.CLOSE_PARENTHESIS
                            + (StringUtil.isNotEmpty(entity.getOrderPlaceBean().getEntrustPrice()) ?
                            (GlobalConstant.SPLIT + "委托价格"
                                    + GlobalConstant.OPEN_PARENTHESIS + entity.getOrderPlaceBean().getEntrustPrice() + GlobalConstant.CLOSE_PARENTHESIS): "")
                            + (StringUtil.isNotEmpty(entity.getOrderPlaceBean().getEntrustVolume()) ?
                            (GlobalConstant.SPLIT + "委托数量"
                                    + GlobalConstant.OPEN_PARENTHESIS + entity.getOrderPlaceBean().getEntrustVolume() + GlobalConstant.CLOSE_PARENTHESIS): ""));
        }  else if (dto.getEntrustList().size() == ExchangeConstant.ONE) {
            // 非公平
            workFlowParameter.setBusinessCode(dto.getEntrustList().get(0).getId());
            workFlowParameter.setComment(
                    entity -> (StringUtil.isNotEmpty(entity.getEntrustList().get(0).getStockCode()) ?
                            (entity.getEntrustList().get(0).getStockName()
                                    + GlobalConstant.VERTICAL_LINE + entity.getEntrustList().get(0).getStockCode()) : "")
                            + (StringUtil.isNotEmpty(entity.getEntrustList().get(0).getId()) ?
                            (GlobalConstant.SPLIT + "委托编号"
                                    + GlobalConstant.OPEN_PARENTHESIS + entity.getEntrustList().get(0).getId() + GlobalConstant.CLOSE_PARENTHESIS): "")
                            + (StringUtil.isNotEmpty(entity.getEntrustList().get(0).getParentOrderId()) ?
                            (GlobalConstant.SPLIT + "订单编号"
                                    + GlobalConstant.OPEN_PARENTHESIS + entity.getEntrustList().get(0).getParentOrderId() + GlobalConstant.CLOSE_PARENTHESIS): "")
                            + (StringUtil.isNotEmpty(entity.getEntrustList().get(0).getEntrustVolume()) ?
                            (GlobalConstant.SPLIT + "委托数量"
                                    + GlobalConstant.OPEN_PARENTHESIS + entity.getEntrustList().get(0).getEntrustVolume() + GlobalConstant.CLOSE_PARENTHESIS): "")
                            + (StringUtil.isNotEmpty(entity.getEntrustList().get(0).getEntrustPrice()) ?
                            (GlobalConstant.SPLIT + "委托价格"
                                    + GlobalConstant.OPEN_PARENTHESIS + entity.getEntrustList().get(0).getEntrustPrice() + GlobalConstant.CLOSE_PARENTHESIS): ""));
        } else {
            // 公平
            workFlowParameter.setBusinessCode(dto.getEntrustList().get(0).getParentOrderId());
            workFlowParameter.setComment(
                    entity -> (StringUtil.isNotEmpty(entity.getEntrustList().get(0).getStockCode()) ?
                            (entity.getEntrustList().get(0).getStockName()
                                    + GlobalConstant.VERTICAL_LINE + entity.getEntrustList().get(0).getStockCode()) : "")
                            + (StringUtil.isNotEmpty(entity.getEntrustList().get(0).getParentOrderId()) ?
                            (GlobalConstant.SPLIT + "订单编号"
                                    + GlobalConstant.OPEN_PARENTHESIS + entity.getEntrustList().get(0).getParentOrderId() + GlobalConstant.CLOSE_PARENTHESIS): "")
                            + (StringUtil.isNotEmpty(entity.getEntrustList().get(0).getEntrustPrice()) ?
                            (GlobalConstant.SPLIT + "委托价格"
                                    + GlobalConstant.OPEN_PARENTHESIS + entity.getEntrustList().get(0).getEntrustPrice() + GlobalConstant.CLOSE_PARENTHESIS): ""));
        }

        return processParameter;
    }

    @DTXTransaction(propagation = Propagation.REQUIRES_NEW)
    @Transactional(rollbackFor = {Exception.class})
    public boolean startWorkflow(TrdExchangeIntegratedEntrustDto dto) {
        logger.info("start review workflow [{}]", JsonUtils.getJsonFromObject(dto));
        ProcessParameter<TrdExchangeIntegratedEntrustDto> workFlowPatameter = getWorkFlowPatameter(dto);
        try {
            WorkFlowUtil.startProcess(workFlowPatameter.getWorkFlowParameter(), dto.getUsers(), dto.getRoles(), dto.getEntrustList().get(0).getUserId(),
                    OperationType.NEW, true, dto);
            logger.info("start review workflow success.");
            return true;
        }
        catch (Exception e) {
            logger.error("start review workflow failed with error : [{}]",e.getMessage());
            return false;
        }
    }

    public ProcessParameter<TrdExchangeIntegratedEntrustDto> getWorkFlowPatameter(TrdExchangeIntegratedEntrustDto dto) {
        dto.setWorkflowId(null);
        dto.setOperateType("submit");

        ProcessParameter<TrdExchangeIntegratedEntrustDto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<TrdExchangeIntegratedEntrustDto> workFlowParameter = new WorkFlowParameter<>();
        processParameter.setWorkFlowParameter(workFlowParameter);
        // 画面ID
        processParameter.setPageId("TrdExchange003");
        // 画面元素ID
        workFlowParameter.setElementId(dto.getElementId());
        processParameter.setOperationType(OperationType.NEW);
        workFlowParameter.setBusinessCode(dto.getEntrustList().get(0).getId());
        // 处理Service名
        workFlowParameter.setServiceName("TrdExchangePage003Service");
        workFlowParameter.setPageId("TrdExchange003");
        workFlowParameter.setBusinessType(DictCodeListExt3.共通业务类型_场内交易下单.getValue());
        // 组合名称 资金账号 证券代码  证券名称  委托方向  委托价格 委托数量  委托金额  申报类型  清算速度  净价价格  全价价格 到期收益率  本方交易员 对手交易商 对手交易员  交易对手 约定号
        workFlowParameter.setComment(
                entity -> (StringUtil.isNotEmpty(entity.getEntrustList().get(0).getStockCode()) ?
                        (entity. getEntrustList().get(0).getStockName()
                                + GlobalConstant.VERTICAL_LINE + entity.getEntrustList().get(0).getStockCode()) : "")
                        + (StringUtil.isNotEmpty(entity.getEntrustList().get(0).getId()) ?
                        (GlobalConstant.SPLIT + "委托编号"
                                + GlobalConstant.OPEN_PARENTHESIS + entity.getEntrustList().get(0).getId() + GlobalConstant.CLOSE_PARENTHESIS): "")
                        + (StringUtil.isNotEmpty(entity.getEntrustList().get(0).getParentOrderId()) ?
                        (GlobalConstant.SPLIT + "订单编号"
                                + GlobalConstant.OPEN_PARENTHESIS + entity.getEntrustList().get(0).getParentOrderId() + GlobalConstant.CLOSE_PARENTHESIS): "")
                        + (StringUtil.isNotEmpty(entity.getEntrustList().get(0).getEntrustVolume()) ?
                        (GlobalConstant.SPLIT + "委托数量"
                                + GlobalConstant.OPEN_PARENTHESIS + entity.getEntrustList().get(0).getEntrustVolume() + GlobalConstant.CLOSE_PARENTHESIS): "")
                        + (StringUtil.isNotEmpty(entity.getEntrustList().get(0).getEntrustPrice()) ?
                        (GlobalConstant.SPLIT + "委托价格"
                                + GlobalConstant.OPEN_PARENTHESIS + entity.getEntrustList().get(0).getEntrustPrice() + GlobalConstant.CLOSE_PARENTHESIS): ""));
        return processParameter;
    }

    private String getStockNameFromInstrument(String stockCode) {
        Instrument instrument = instrumentService.queryByInnerCode(stockCode);
        if (Objects.nonNull(instrument)) {
            return instrument.getStockName();
        }
        logger.warn("未找到相关合约信息 {}", stockCode);
        return stockCode;
    }
}
