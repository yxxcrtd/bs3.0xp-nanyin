/*
 * 文件名：WorkFlowCommonServiceMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：dingzhipeng
 * 修改时间：2018年8月26日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.base.core.impl.dao;

import java.util.List;

import com.joyintech.tams.component.base.core.dto.WflSpecialValidInputParameter;
import org.springframework.stereotype.Repository;

import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.tams.component.base.core.dto.WflValidInputParameter;


/**
 * 工作流共通Service
 * mstworkflowcommonservice
 * 〈功能详细描述〉
 * @author dingzhipeng
 * @version 1.0 2018年8月26日
 * @see WorkFlowCommonServiceMapper
 * @since 1.0
 */
@Repository
public interface MstWorkflowCommonServiceMapperImpl {
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

	/**
	 * add by peijiajun 根据查询参数查询留痕表数据 <功能详细描述>
	 * @param wflValidInputParameter 函数输入参数
	 * @return 结果
	 * @see
	 * @since 1.0
	 */
	List<ProcessMark> getBusinessDataEntity(WflValidInputParameter wflValidInputParameter);

	/**
	 * 根据查询参数查询留痕表数据 <功能详细描述>
	 *
	 * @param wflSpecialValidInputParameter 函数输入参数
	 * @return 结果
	 * @see
	 * @since 1.0
	 */
	List<String> getSpecialBusinessData(WflSpecialValidInputParameter wflSpecialValidInputParameter);
}
