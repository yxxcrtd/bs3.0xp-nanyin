/*
 * 文件名：BusinessService.java
 * 版权：Copyright 2018-2019 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：资产管理系统V2.0
 * 修改时间：2018年1月11日
 * 修改内容：新建
 * 系统名称：资产管理系统V2.0
 */

package com.joyintech.workflow.engine.service;

import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.constant.DictCodeList;
import com.joyintech.framework.constant.GlobalConstant;
//import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.security.User;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.business.common.util.UserUtil;
import com.joyintech.workflow.engine.LocalWorkFlowParameter;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.constant.WflConstants;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.entity.FlowEntity;
import com.joyintech.workflow.engine.entity.WorkflowEntity;
import com.joyintech.workflow.engine.exception.WorkFlowException;
import com.joyintech.workflow.engine.mail.SendMailUtil;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 该接口提供一个方法，统一更新业务表
 *
 * @param <T> 业务实体
 * @version 2018年1月11日
 * @see BusinessService
 * @since
 */
public interface BusinessService<T extends WorkflowEntity> {
    
    /**
     * 开始工作流
     *
     * @param entities 业务Entity
     * @param params 参数
     * @throws WorkFlowException 异常
     * @return 启动结果Message
     */
    default String start(WorkFlowParameter<T> params, @SuppressWarnings("unchecked") T... entities)
        throws WorkFlowException {
        // HttpServletRequest
        HttpServletRequest request = RequestUtil.getCurrentRequest();
        // 下一节点处理人（只有提交时才需要该参数）
        String users = StringUtils.defaultString(request.getParameter(WflConstants.USER_KEY), LocalWorkFlowParameter.getUsers());
        // 下一节点处理角色（只有提交时才需要该参数）
        String roles = StringUtils.defaultString(request.getParameter(WflConstants.ROLE_KEY), LocalWorkFlowParameter.getRoles());
        // 获取当前用户
        User user = UserUtil.getUser();//SecurityUtil.getLoginUser();
        // 操作类型
        String operType = StringUtils.defaultString(
            request.getParameter(WflConstants.OPER_TYPE), LocalWorkFlowParameter.getOperType());
        OperationType operationType = OperationType.valueOf(operType.toUpperCase());
        // 业务service
        String serviceName = params.getServiceName();
        if(StringUtils.isEmpty(serviceName)) {
            serviceName = WorkFlowUtil.getServiceName(getClass());
            params.setServiceName(serviceName);
        }
        // submitJson方式下，elementId没有值，这种时候需要重新设置
        if (StringUtil.isEmpty(params.getElementId())) {
            params.setElementId(StringUtils.defaultString(request.getParameter(GlobalConstant.ELEMENT_ID),
                LocalWorkFlowParameter.getElementId()));
        }
        // 是否为提交
        boolean isSubmit = StringUtils.equals("1",
            StringUtils.defaultString(request.getParameter(WflConstants.SUBMIT_KEY), LocalWorkFlowParameter.getSubmit()));
        // 启动流程
        WorkFlowUtil.startProcess(params, users, roles, user.getUserName(), operationType, isSubmit, entities);
        
        // 提交的时候，系统参数表中是否需要发送邮件为是的时候，发送邮件
        if (isSubmit && StringUtil.equals(CacheUtil.getSysParam(GlobalConstant.IS_MAIL_SEND_WORKFLOW), DictCodeList.是否_是.getValue())) {
            String from = StringUtil.isNotEmpty(user.getMailAddr()) ? user.getMailAddr()
                : CacheUtil.getSysParam(GlobalConstant.DEFAULT_FROM_WORKFLOW);
            List<String> toUserMailList = new ArrayList<String>();
            String[] toUserArray = StringUtil.split(users, GlobalConstant.COMMA);
            for (String userName : toUserArray) {
                toUserMailList.add(CacheUtil.getAppParamCache(GlobalConstant.SYS_USER_MAIL_KEY, userName));
            }
            String[] strArrMail = new String[toUserMailList.size()];
            String subject = "请在待办事项中尽快审批来自" + user.getRealName() + "的申请。";
            String content = "请在待办事项中尽快审批来自" + user.getRealName() + "的申请。";
            for (String to : toUserMailList.toArray(strArrMail)) {
                SendMailUtil sendEmail = new SendMailUtil(from, to, subject, content);
                sendEmail.send();
            }
        }
        
        return SpringContextUtil.getTextValue("COMMON_I_0005");
    }
    
    /**
     * 插入业务表数据
     *
     * @param entity 业务Entity
     * @param type 操作类型
     * @throws WorkFlowException 异常
     */
    void doInsert(T entity, OperationType type);

    /**
     * 更新业务表的状态
     *
     * @param entity 业务实体
     */
    void doUpdate(T entity);

    /**
     * 物理删除业务数据（在流程作废时使用）
     *
     * @param workflowId 流程ID
     */
    void doDelete(String workflowId);

    /**
     * 工作流完成回调方法
     *
     * @param entity 业务实体
     * @param type 操作类型
     */
    void doComplete(T entity, OperationType type);

    /**
     * 工作流退回回调方法
     *
     * @param entity 业务实体
     */
    void doBack(T entity);

    /**
     * 工作流提交之前的自定义验证（单条）
     * @param entity 业务实体
     * @param isMutiApproval 是否来自批量审批
     * @return 验证消息
     */
    String checkForSubmit(FlowEntity entity, boolean isMutiApproval);

    /**
     * 工作流提交之前的自定义验证（批量）
     * @param flowEntityList 业务实体List
     * @param isMutiApproval 是否来自批量审批
     * @return 验证消息
     */
    String checkForSubmitMulti(List<FlowEntity> flowEntityList, boolean isMutiApproval);

    /**
    * 生成流程标题
    * @param entity 流程实体
    * @return 流程标题
    */
    String generateTitle(T entity, OperationType type);

    /**
     * 获取业务参数
     *
     * @param entity 业务实体
     * @param taskId 任务代码
     * @return 参数
     * @throws WorkFlowException 工作流异常
     */
    Map<String, Object> getRuntimeVariables(ProcessMark entity, String taskId) throws WorkFlowException;

    /**
     * 工作流强制撤回回调方法
     *
     * @param entity 业务实体
     */
    void doRevoke(T entity);
    
    /**
     * 工作流提交之前的自定义验证
     * @param entity 业务实体
     * @param isMutiApproval 是否来自批量审批
     * @return 验证消息
     */
    String checkForSubmitMessage(FlowEntity entity, boolean isMutiApproval);
}
