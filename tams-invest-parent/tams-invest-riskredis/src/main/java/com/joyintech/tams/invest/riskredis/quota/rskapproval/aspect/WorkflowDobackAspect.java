/*
 * 文件名：WorkflowDobackAspect.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2019年10月16日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.riskredis.quota.rskapproval.aspect;

import java.util.HashMap;
import java.util.Map;

import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.invest.riskredis.quota.rskapproval.service.RskApprovalService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.JsonNode;
import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.common.client.ClientUtil;
import com.joyintech.tams.invest.common.constantrds.BussinessConstantRds;
import com.joyintech.workflow.engine.WorkflowContext;
import com.joyintech.workflow.engine.entity.FlowEntity;
import com.joyintech.workflow.engine.exception.WorkFlowException;

/**
 * 监视工作流退回
 * 
 * @author zhangrui1
 * @version 1.0 2019年10月16日
 * @see WorkflowDobackAspect
 * @since 1.0
 */
@Component
@Aspect
public class WorkflowDobackAspect extends BaseService {

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Pointcut("execution(public * com.joyintech.workflow.engine.service.ProcessService.rollbackProcess(..))")
    protected void doBack() {
        // just point
    }

    @Pointcut("execution(public * com.joyintech.workflow.engine.controller.WorkFlowController.doCancel(..))")
    protected void doCancel() {
        // just point
    }

    @Pointcut("execution(public * com.joyintech.workflow.engine.controller.WorkFlowController.doAbandon(..))")
    protected void doAbandon() {
        // just point
    }

    @Pointcut("execution(public * com.joyintech.workflow.engine.controller.WorkFlowController.revoke(..))")
    protected void revoke() {
        // just point
    }

    @DTXTransaction
    @Transactional(rollbackFor=Exception.class)
    @After("doBack()")
    public void afterDoBack(JoinPoint joinPoint) throws WorkFlowException {
        Object[] args = joinPoint.getArgs();
        String processInstanceId = (String)args[0];
        String operatorUserId = (String)args[1];
        String rejectReason = (String)args[2];
        String selectActivitiId = (String)args[3];
        String selectActivitiName = (String)args[4];
        logger.info(
            "流程退回:[processInstanceId:{},operatorUserId:{},rejectReason:{},selectActivitiId:{},selectActivitiName:{}]",
            processInstanceId, operatorUserId, rejectReason, selectActivitiId, selectActivitiName);
        ProcessMark mark = null;
        try {
            FlowEntity entity = WorkflowContext.getProcessService().getRuntimeEntity(
                processInstanceId);
            String wkfId = entity.getWorkflowId();
            mark = WorkflowContext.getProcessMarkService().getBusinessInfo(wkfId);
        }
        catch (WorkFlowException e) {
            logger.error("流程取值异常", e);
            throw e;
        }
        if (mark == null) {
            logger.info("流程数据不存在");
            return;
        }
        String pageId = mark.getPageId();
        // 精确委托
        if (StringUtil.equals(pageId, "RskApproval001")) {
            send(mark, rejectReason, "RSK007");
        }
        // 交易
        else if (StringUtil.equals(pageId, "RskApproval002")) {
            send(mark, rejectReason, "RSK007");
        }
        // 模糊委托
        else if (StringUtil.equals(pageId, "RskApproval003")) {
            send(mark, rejectReason, "RSK008");
        }
    }

    private void send(ProcessMark mark, String rejectReason, String rsk) {
        // 委托/交易代码
        String eventId = mark.getBusinessDataPk1();
        String data = mark.getBusinessData();
        JsonNode node = JsonUtils.toJsonNode(data);
        // 系统代码
        String sysCode = findValue(node, "sysCode");
        // 系统代码拼接画面ID
        sysCode = sysCode + GlobalConstant.UNDER_LINE + mark.getPageId();
        // 发送报文
        send(eventId, rejectReason, rsk, sysCode);
    }

    protected void send(String code, String rejectReason, String rsk, String sysCode) {
        Map<String, Object> data = new HashMap<>();
        data.put("sysCode", StringUtil.contains(sysCode, GlobalConstant.UNDER_LINE) ?
        		StringUtil.substring(sysCode, 0, StringUtil.indexOf(sysCode, GlobalConstant.UNDER_LINE))
        		: sysCode);
        String eventId = StringUtil.contains(code, GlobalConstant.COLON) ? 
        		StringUtil.substring(code, StringUtil.indexOf(code, GlobalConstant.COLON) + 1)
        		: code;
        // 交易系统
        if (StringUtil.equals((String) data.get("sysCode"), CacheUtil.getSysParam("tradeSys"))) {
            data.put(BussinessConstantRds.EVENT_ID, eventId);
            data.put(BussinessConstantRds.STATUS, "0");
        }
        else {
        	data.put(BussinessConstantRds.ENTRUSTCODE, eventId);
            data.put(BussinessConstantRds.APPROVEMSG, rejectReason);
            data.put(BussinessConstantRds.FLOWSTATUS, "03");
            data.put(BussinessConstantRds.INTERFCODE, rsk);
        }
        ClientUtil.sendRequest(CacheUtil.getSysParam(RskApprovalService.APPROVAL_SYS + sysCode), data,restTemplate);
    }

    private String findValue(JsonNode node, String key) {
        if (node != null) {
            JsonNode value = node.findValue(key);
            if (value != null) {
                return value.asText();
            }
        }
        return StringUtil.EMPTY;
    }

    @After("doAbandon()")
    public void afterDoAbandon(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        String taskId = (String)args[0];
        logger.info("放弃任务:[taskId:{}]", taskId);
    }

    @After("revoke()")
    public void afterRevoke(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        String processInstanceId = (String)args[0];
        logger.info("流程撤回:[processInstanceId:{}]", processInstanceId);
    }

    @After("doCancel()")
    public void afterDoCancel(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        String workflowId = (String)args[0];
        String processInstanceId = (String)args[1];
        String comment = (String)args[2];
        logger.info("流程作废:[processInstanceId:{},workflowId:{},comment:{}]", processInstanceId,
            workflowId, comment);
    }
}
