/*
 * 文件名：MstWorkflowCommonMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：王波
 * 修改时间：2018年12月6日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.service.workflow.dao;

import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.service.workflow.dto.CommonBasicDataWflValidInputParameter;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 工作流共通Mapper
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author 王波
 * @version 1.0 2018年12月6日
 * @see CommonBasicDataWorkflowCommonMapper
 * @since 1.0
 */
@Repository("commonBasicDataWorkflowCommonMapper")
public interface CommonBasicDataWorkflowCommonMapper {

    /**
     * 
     * 根据查询参数查询留痕表数据
     * <功能详细描述>
     * @param commonBasicDataWflValidInputParameter 函数输入参数
     * @return 结果
     * @see
     * @since 1.0
     */
    List<String> getBusinessData(CommonBasicDataWflValidInputParameter commonBasicDataWflValidInputParameter);
    
    /**
     * 根据工作流代码获取entity
     * <功能详细描述>
     * @param workFlowId
     * @return
     * @see
     * @since 1.0
     */
    ProcessMark getBusinessInfo(String workFlowId);
}
