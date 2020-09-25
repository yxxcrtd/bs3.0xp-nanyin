/*
 * 文件名：MstWorkflowCommonService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：dingzhipeng
 * 修改时间：2018年8月26日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.base.core;

import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.tams.component.base.core.dto.WflSpecialValidInputParameter;
import com.joyintech.tams.component.base.core.dto.WflValidInputParameter;

/**
 * 工作流共通Service
 * 〈功能详细描述〉
 * @author dingzhipeng
 * @version 1.0 2018年8月26日
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
     *
     * 检验业务数据是否存在流程中数据（以创建时间最大的数据的数据状态判断）
     * <功能详细描述>
     * @param wflSpecialValidInputParameter 函数输入参数
     * @return 是否
     * @see
     * @since 1.0
     */
    boolean isInWflProcess(WflSpecialValidInputParameter wflSpecialValidInputParameter);

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
     * 根据工作流代码获取对应的业务实体
     * <功能详细描述>
     * @param workFlowId 工作流代码
     * @return
     * @see
     * @since 1.0
     */
     String getBusinessEntityByWorkFlowId(String workFlowId,String beanName);
}
