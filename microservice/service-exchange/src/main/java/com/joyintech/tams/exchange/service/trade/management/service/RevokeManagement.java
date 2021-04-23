package com.joyintech.tams.exchange.service.trade.management.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.exchange.common.bean.*;
import com.joyintech.tams.exchange.common.constant.ApproveType;
import com.joyintech.tams.exchange.common.constant.EntrustStatus;
import com.joyintech.tams.exchange.common.constant.ErrorCode;
import com.joyintech.tams.exchange.common.constant.IDPrefix;
import com.joyintech.tams.exchange.common.constant.joyin.DictCodeList;
import com.joyintech.tams.exchange.common.db.domain.Entrust;
import com.joyintech.tams.exchange.common.db.domain.EntrustInterface;
import com.joyintech.tams.exchange.common.db.domain.IntegratedEntrust;
import com.joyintech.tams.exchange.common.microservice.ServiceApplication;
import com.joyintech.tams.exchange.common.microservice.TradeServiceURL;
import com.joyintech.tams.exchange.datacommon.db.service.EntrustService;
import com.joyintech.tams.exchange.datacommon.db.service.IntegratedEntrustService;
import com.joyintech.tams.exchange.datacommon.db.service.TrdExchangeWorkflowService;
import com.joyintech.tams.exchange.service.trade.processor.business.common.EntrustCancelProcessor;
import com.joyintech.tams.exchange.service.trade.processor.constants.Constants;
import com.joyintech.tams.exchange.service.trade.processor.msg.MsgReceiverManager;
import com.joyintech.tams.exchange.service.trade.processor.msg.MsgType;
import com.joyintech.tams.exchange.service.trade.processor.msg.TradeMsgReceiver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RevokeManagement extends BaseService {
    private static final Logger logger = LoggerFactory.getLogger(RevokeManagement.class);
    // 记录已经登录的账号
    private static final Map<String, WorkflowBean> revokeMap = Maps.newConcurrentMap();
    @Autowired
    private EntrustService entrustService;
    @Autowired
    private IntegratedEntrustService integratedEntrustService;
    @Autowired
    private MsgReceiverManager msgReceiverManager;
    @Autowired
    private TrdExchangeWorkflowService trdExchangeWorkflowService;
    @Autowired
    private EntrustCancelProcessor entrustCancelProcessor;
    /**
     * 是否单体服务
     */
    @Value("${application.micorservice.contextPath.single:false}")
    private boolean isSingle;

    public static WorkflowBean getRevokeMap(String id) {
        return revokeMap.get(id);
    }

    public static void putRevokeMap(String id, WorkflowBean workflowBean) {
        revokeMap.put(id, workflowBean);
    }

    public static void removeRevokeMap(String id) {
        revokeMap.remove(id);
    }

    private ResultBean handleNormal(EntrustInterface entrust, String approveStatus, String approveReason, int type, EntrustInterface cancelEntrust) {
        TradeMsgReceiver tradeMsgReceiver = msgReceiverManager.getMsgReceiverByInstruction(entrust.getInstructionId());
        tradeMsgReceiver.addMsg(MsgType.EXAMINE_REVOKE, new ExamineOrder(approveStatus, approveReason, cancelEntrust, type));
        return new ResultBean(ErrorCode.SUCCESS, entrust);
    }

    /**
     * 处理撤审审批结果
     *
     * @param
     * @return
     */
    public ResultBean handleExamine(String eventId, String approveStatus, String approveReason) {
        EntrustInterface cancelEntrust = null;
        if (eventId.startsWith(IDPrefix.NORMAL.value)) {
            cancelEntrust = entrustService.findById(eventId);
        } else if (eventId.startsWith(IDPrefix.BULK.value)) {
            cancelEntrust = integratedEntrustService.findById(eventId);
        }
        if (Objects.isNull(cancelEntrust)) {
            return new ResultBean(ErrorCode.INSTRUCTIONS_NOT_EXIST, eventId);
        }
        String entrustId = cancelEntrust.getOrigEntrustId();
        EntrustInterface entrust = null;
        List<Entrust> entrusts = null;
        int type = 0;
        if (entrustId.startsWith(IDPrefix.NORMAL.value)) {
            entrust = entrustService.findById(entrustId);
            type = Constants.PROCESS_TYPE_ENTRUST;
        } else if (entrustId.startsWith(IDPrefix.BULK.value)) {
            entrust = integratedEntrustService.findById(entrustId);
            type = Constants.PROCESS_TYPE_INTEGRATED;
        } else if (entrustId.startsWith(IDPrefix.ORDER.value)) {//处理公平交易
            entrusts = entrustService.queryEntrustByOrderId(entrustId);
            type = Constants.PROCESS_TYPE_FIRE_ORDER;
        }
        if (Objects.isNull(entrust) && Objects.isNull(entrusts)) {
            return new ResultBean(ErrorCode.INSTRUCTIONS_NOT_EXIST, eventId);
        }
        if (ApproveType.EXAMINE_APPROVAL.value.equals(approveStatus) && EntrustStatus.isFinished(entrust.getEntrustStatus())) {
            cancelEntrust.setEntrustStatus(EntrustStatus.SYSTEM_INVALID.value);
            cancelEntrust.setErrorMsg("委托已完成，不允许撤销");
            if (EntrustInterface.isIntegratedEntrust(entrustId)) {
                integratedEntrustService.asyncUpdate((IntegratedEntrust) cancelEntrust);
            } else {
                entrustService.asyncUpdate((Entrust) cancelEntrust);
            }
            return new ResultBean(ErrorCode.SUCCESS, entrust);
        }
        if (ApproveType.EXAMINE_REFUSE.value.equals(approveStatus) && Strings.isNullOrEmpty(approveReason)) {
            //todo 此处原委托状态没回滚
            cancelEntrust.setEntrustStatus(EntrustStatus.SYSTEM_INVALID.value);
            cancelEntrust.setErrorMsg("拒绝原因不可以为空");
            if (EntrustInterface.isIntegratedEntrust(entrustId)) {
                integratedEntrustService.asyncUpdate((IntegratedEntrust) cancelEntrust);
            } else {
                entrustService.asyncUpdate((Entrust) cancelEntrust);
            }
            return new ResultBean(ErrorCode.REQ_PARAMETER_ERROR, entrust);
        }
        return handleNormal(entrust, approveStatus, approveReason, type, cancelEntrust);
        //        // 根据类型分别处理委托
        //        if (type == Constants.PROCESS_TYPE_FIRE_ORDER) {// 公平
        //            return handleOrder(entrusts,approveStatus,approveReason);
        //        } else {
        //            return handleNormal(entrust,approveStatus,approveReason,type);
        //        }
    }

    public boolean startWorkflow(TrdExchangeEntrustDto dto) {
        if (isSingle) {
            return simpleRestTemplate.postForObject(ServiceApplication.TRADE_SERVICE_NAME + TradeServiceURL.startRevokeEntrustWorkflow, dto, Boolean.class);
        } else {
            return restTemplate.postForObject(ServiceApplication.TRADE_SERVICE_NAME + TradeServiceURL.startRevokeEntrustWorkflow, dto, Boolean.class);
        }
    }

    public boolean startWorkflow(TrdExchangeIntegratedEntrustDto dto) {
        if (isSingle) {
            return simpleRestTemplate.postForObject(ServiceApplication.TRADE_SERVICE_NAME + TradeServiceURL.startRevokeIntegratedEntrustWorkflow, dto, Boolean.class);
        } else {
            return restTemplate.postForObject(ServiceApplication.TRADE_SERVICE_NAME + TradeServiceURL.startRevokeIntegratedEntrustWorkflow, dto, Boolean.class);
        }
    }

    public List<WorkFlowDto> selectWorkflow(String entrustId) {
        Map<String, Object> param = new HashMap<>();
        param.put("state", DictCodeList.流程状态_流程中.getValue());
        param.put("entrustId", entrustId);
        return trdExchangeWorkflowService.selectWorkflow(param);
    }

    // 流程作废
    public boolean doRevoke(String entrustId) {
        if (isSingle) {
            return simpleRestTemplate.postForObject(ServiceApplication.TRADE_SERVICE_NAME + TradeServiceURL.doRevokeWorkflow, entrustId, Boolean.class);
        } else {
            return restTemplate.postForObject(ServiceApplication.TRADE_SERVICE_NAME + TradeServiceURL.doRevokeWorkflow, entrustId, Boolean.class);
        }
    }

    public void handleRevokeWorkflow(EntrustInterface entrust, WorkflowBean workflow, EntrustInterface cancelEntrust) {
        logger.debug("handleRevokeWorkflow entrust [{}], workflow [{}]", JSONUtil.toJsonStr(entrust), JSONUtil.toJsonStr(workflow));
        String entrustId = entrust.getId();
        boolean processResult = false;
        if (EntrustInterface.isIntegratedEntrust(entrustId)) {
            // 设置撤销数量
            IntegratedEntrust entrustCopy = new IntegratedEntrust();
            BeanUtil.copyProperties(entrust, entrustCopy);
            entrustCopy.setCancelVolume(entrustCopy.getEntrustVolume() - entrustCopy.getTradedVolume());
            TrdExchangeIntegratedEntrustDto dto = BeanUtil.toBean(workflow, TrdExchangeIntegratedEntrustDto.class);
            dto.setEntrustList(Arrays.asList(entrustCopy));
            dto.setEventId(cancelEntrust.getId());
            processResult = startWorkflow(dto);
        } else {
            Entrust entrustCopy = new Entrust();
            BeanUtil.copyProperties(entrust, entrustCopy);
            entrustCopy.setCancelVolume(entrustCopy.getEntrustVolume() - entrustCopy.getTradedVolume());
            TrdExchangeEntrustDto dto = BeanUtil.toBean(workflow, TrdExchangeEntrustDto.class);
            dto.setEntrustList(Arrays.asList(entrustCopy));
            dto.setEventId(cancelEntrust.getId());
            processResult = startWorkflow(dto);
        }
        removeRevokeMap(entrustId);
        if (processResult) {
            // 修改委托状态
            entrustCancelProcessor.setCancelStatus(entrust);
            cancelEntrust.setEntrustStatus(EntrustStatus.PENDING_REVOKE.value);
            logger.info("handleRevokeWorkflow start workflow [{}] success,cancelId:{}", entrustId, cancelEntrust.getId());
        } else {
            cancelEntrust.setEntrustStatus(EntrustStatus.SYSTEM_INVALID.value);
            cancelEntrust.setErrorMsg("撤审任务启动失败");
            logger.warn("handleRevokeWorkflow start workflow [{}] failed", entrustId);
        }
    }
}
