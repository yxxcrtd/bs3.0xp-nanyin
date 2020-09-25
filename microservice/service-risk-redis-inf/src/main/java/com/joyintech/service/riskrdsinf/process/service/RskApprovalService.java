/*
 * 文件名：RskApprovalService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2019年9月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.riskrdsinf.process.service;

import java.util.Arrays;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.constant.DictCodeList;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.security.AuthorizationService;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.security.User;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.web.PlatformDate;
import com.joyintech.service.riskrdsinf.cache.InterfCacheUtil;
import com.joyintech.service.riskrdsinf.core.constant.InfDictCodeList;
import com.joyintech.service.riskrdsinf.core.exception.InterfException;
import com.joyintech.service.riskrdsinf.process.dao.RskApprovalMapper;
import com.joyintech.service.riskrdsinf.process.entity.RskApprovalDto;
import com.joyintech.tams.invest.common.CommonUtil;
import com.joyintech.workflow.engine.TaskAuthentication;
import com.joyintech.workflow.engine.WorkflowContext;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.exception.WorkFlowException;
import com.joyintech.workflow.engine.service.BaseProcessService;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 风险审批Service
 * 
 * @author zhangrui1
 * @version 1.0 2019年9月23日
 * @see RskApprovalService
 * @since 1.0
 */
public abstract class RskApprovalService<V extends RskApprovalDto> extends BaseService<V> implements BaseProcessService<V> {

    /**
     * 日志
     */
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private RskApprovalMapper approvalMapper;

    @Autowired(required = false)
    private AuthorizationService authorizationService;
    
    /**
     * PlatformDate
     */
    @Autowired(required = false)
    private PlatformDate platformDateService;

    /**
     * 平台日期
     */
    private Date sysDate;

    /**
     * {@inheritDoc}
     */
    @Override
    public void doComplete(V entity, OperationType type) {
        // nothing to do
    }

    public void startProcess(V dto, String loginUser)
        throws WorkFlowException {
        logger.info("启动审批流程 处理开始...");
        // 存在流程中的数据
        if (isInWorkflow(dto)) {
        	throw new WorkFlowException(new InterfException("审批已存在"));
        }
        WorkFlowParameter<V> params = new WorkFlowParameter<>();
        // 画面ID
        params.setPageId(dto.getPageId());
        // 画面元素ID
        params.setElementId(dto.getElementId());
        // 处理Service名
        params.setServiceName(getServiceName());
        //设置任务开始日期
        params.setCrtDate(sysDate);
        // 修改时为画面所选记录的委托编号。
        setParams(params, dto);
        // 审批用户
        String users = InterfCacheUtil.getSysParam("rskApprovalUser");
        // 审批角色
        String role = InterfCacheUtil.getSysParam("rskApprovalRole");
        // 调用工作流事件
        WorkFlowUtil.startProcess(params, users, role, loginUser, OperationType.NEW, true, dto);
        logger.info("启动审批流程 处理结束...");
    }
    
    @SuppressWarnings("unchecked")
	public void startProcessDtx(V dto, String loginUser)
            throws WorkFlowException {
            logger.info("启动审批流程 处理开始...");
            ProcessParameter<V> processParameter = new ProcessParameter<>();
            processParameter.setOperationType(OperationType.NEW);
            processParameter.setPageId(dto.getPageId());
            // 存在流程中的数据
            if (isInWorkflow(dto)) {
            	throw new WorkFlowException(new InterfException("审批已存在"));
            }
            WorkFlowParameter<V> params = new WorkFlowParameter<>();
            // 画面ID
            params.setPageId(dto.getPageId());
            // 画面元素ID
            params.setElementId(dto.getElementId());
            // 处理Service名
            params.setServiceName(getServiceName());
            //设置任务开始日期
            params.setCrtDate(sysDate);
            // 修改时为画面所选记录的委托编号。
            setParams(params, dto);
            // 审批用户
            String users = InterfCacheUtil.getSysParam("rskApprovalUser");
            // 审批角色
           // String role = InterfCacheUtil.getSysParam("rskApprovalRole");
            TaskAuthentication.setCandidateUser(Arrays.asList(loginUser));
            User user = authorizationService.getUser(users);
            // 将用户信息保存到session中
            SecurityUtil.addUserToSession(user);
            processParameter.setWorkFlowParameter(params);
            dto.setWorkflowParameter("new|||0|"+dto.getPageId()+"|"+dto.getElementId()+"|true");
            // 启动工作流
            start(processParameter, dto);
            logger.info("启动审批流程 处理结束...");
        }

    /**
     * 判断启动的预占/实占是否在流程中
     * 
     * @param dto 预占实占数据
     * @return true：存在流程中数据
     */
    public boolean isInWorkflow(V dto){
    	ProcessMark mark = WorkflowContext.getProcessMarkService().getParameterByAnnotation(dto);
    	String flowId = approvalMapper.getLastWorkflowId(mark);
    	// 不存在流程数据
    	if (StringUtil.isEmpty(flowId)) {
    		return false;
    	}
    	ProcessMark entity = WorkflowContext.getProcessMarkService().getBusinessInfo(flowId);
    	// 存在新建中的数据
    	return entity != null && (CommonUtil.isType(entity.getStatus(), DictCodeList.数据状态_新建中));
    }

    /**
     * 设置参数
     * 
     * @param params 参数
     * @param dto 数据
     * @see
     * @since 1.0
     */
    protected abstract void setParams(WorkFlowParameter<V> params, V dto);

    /**
     * 获取 logger
     * 
     * @return logger.
     */
    public Logger getLogger() {
        return logger;
    }

    protected String getServiceName() {
        Service src = this.getClass().getAnnotation(Service.class);
        if (src != null) {
            return src.value();
        }
        return StringUtil.EMPTY;
    }

    /**
     * 取得 平台日期
     * 
     * @return 平台日期
     */
    public Date getSysDate(){
        return sysDate;
    }

    /**
     * 设置 平台日期
     */
    @DataSource(DataSourceType.MASERT)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void setSysDate(){
    	sysDate = platformDateService.getSysDate();
    }

    /**
     * 取得 业务类型
     * 
     * @param assetType 资产大类
     * @param ps 交易方向
     * @param eventType 0:精确委托、1:投资交易、2:交易拆分、3:模糊委托
     * @return 业务类型
     * @see
     * @since 1.0
     */
    protected String getBusinessType(String assetType, String ps, String eventType) {
        StringBuilder builder = new StringBuilder("RSK_APR_");
        if (CommonUtil.isType(eventType, InfDictCodeList.事件类型_投资委托)
            || CommonUtil.isType(eventType, InfDictCodeList.事件类型_投资交易)
            || CommonUtil.isType(eventType, InfDictCodeList.事件类型_交易拆分)) {
            builder.append(assetType).append(GlobalConstant.UNDER_LINE);
        }
        builder.append(eventType);
        return builder.toString();
    }
}
