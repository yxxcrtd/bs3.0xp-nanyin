/*
 * 文件名：MstWorkflowCommonMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：王波
 * 修改时间：2018年12月6日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.common.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.tams.valuation.common.dto.WflValidInputParameter;

/**
 * 工作流共通Mapper
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author 王波
 * @version 1.0 2018年12月6日
 * @see MstWorkflowCommonMapper
 * @since 1.0
 */
@Repository("valuationMstWorkflowCommonMapper")
public interface MstWorkflowCommonMapper {

    /**
     * 
     * 根据查询参数查询留痕表数据
     * <功能详细描述>
     * @param wflValidInputParameter 函数输入参数
     * @return 结果
     * @see
     * @since 1.0
     */
    List<String> getBusinessData(WflValidInputParameter wflValidInputParameter);
    
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
