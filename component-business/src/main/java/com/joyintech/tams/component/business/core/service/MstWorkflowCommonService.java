/*
 * 文件名：MstWorkflowCommonService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Administrator
 * 修改时间：2018年9月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.business.core.service;

import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.tams.component.business.core.dto.WflSpecialValidInputParameter;
import com.joyintech.tams.component.business.core.dto.WflValidInputParameter;

/**
 * 工作流共通服务
 * 〈功能详细描述〉
 * @author Administrator
 * @version 1.0 2018年9月18日
 * @see MstWorkflowCommonService
 * @since 1.0
 */
public interface MstWorkflowCommonService {
    /**
     * 
     * 检验业务数据是否存在流程中数据（以创建时间最大的数据的数据状态判断）
     * <功能详细描述>
     * @param wflValidInputParameter 函数输入参数
     * @return 是否
     * @see
     * @since 1.0
     */
    boolean isInWflProcess(WflValidInputParameter wflValidInputParameter);
    /**
     * 检验业务数据是否存在流程中数据（以创建时间最大的数据的数据状态判断） <功能详细描述>
     *
     * @param wflSpecialValidInputParameter 函数输入参数
     * @return 是否
     * @see
     * @since 1.0
     */
    boolean isInWflProcess(WflSpecialValidInputParameter wflSpecialValidInputParameter);
    /**
     *
     * 判断是走正常流程新建的交易，还是未走流程直接入库的交易
     * 如果留痕表中有数据则是正常交易，否则是反向生成的，未走流程的交易
     * @param wflValidInputParameter 函数输入参数
     * @return 是否
     * @see
     * @since 1.0
     */
    boolean isProcessData(WflValidInputParameter wflValidInputParameter);
    
    /**
     * 
     * 获取数据状态（以创建时间最大的数据的数据状态判断）
     * <功能详细描述>
     * @param wflValidInputParameter 函数输入参数
     * @return 数据状态
     * @see
     * @since 1.0
     */
    String getDataStatusFromProcessMark(WflValidInputParameter wflValidInputParameter);

    /**
     *
     * 获取数据状态（以创建时间最大的数据的数据状态判断）
     * <功能详细描述>
     * @param wflValidInputParameter 函数输入参数
     * @return 数据状态
     * @see
     * @since 1.0
     */
    ProcessMark getDataEntityFromProcessMark(WflValidInputParameter wflValidInputParameter);

    /**
     * 
     * 通过工作流Id获取entity
     * <功能详细描述>
     * @param workFlowId 工作流代码
     * @param beanName entityName
     * @param clazz entity的class
     * @return
     * @see
     * @since 1.0
     */
    <T> T getBusinessEntityByWorkFlowId(String workFlowId,String beanName, Class<T> clazz);
}
