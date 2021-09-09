/*
 * 文件名：MstWorkflowCommonService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：dingzhipeng
 * 修改时间：2018年8月26日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.base.core.impl;

import java.util.List;

import com.joyintech.tams.component.base.core.dto.WflSpecialValidInputParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.component.base.core.MstWorkflowCommonService;
import com.joyintech.tams.component.base.core.dto.WflValidInputParameter;
import com.joyintech.tams.component.base.core.impl.dao.MstWorkflowCommonServiceMapperImpl;

/**
 * 工作流共通Service
 * 〈功能详细描述〉
 * @author dingzhipeng
 * @version 1.0 2018年8月26日
 * @see MstWorkflowCommonServiceImpl
 * @since 1.0
 */
@Component
public class MstWorkflowCommonServiceImpl implements MstWorkflowCommonService{
    
    /**
     * 工作流CommonServicemapper
     */
    @Autowired(required = false)
    private MstWorkflowCommonServiceMapperImpl mstWorkflowCommonServiceMapper;
    
    /**
     *  工作流共通Service
     */
    @Autowired(required = false)
    private MstWorkflowCommonServiceImpl mstWorkflowCommonService;
    
    /**
     * 
     * 检验业务数据是否存在流程中数据（以创建时间最大的数据的数据状态判断）
     * <功能详细描述>
     * @param wflValidInputParameter 函数输入参数
     * @return 是否
     * @see
     * @since 1.0
     */
    @DataSource(DataSourceType.OTHER)
    @Transactional(propagation=Propagation.REQUIRES_NEW)
    @Override
    public boolean isInWflProcess(WflValidInputParameter wflValidInputParameter){
        //查询流程中数据"01"
        wflValidInputParameter.setSeachMode("01");
        List<String>result = mstWorkflowCommonService.getBusinessData(wflValidInputParameter);
        return !StringUtil.isEmptyList(result);
    }
    /**
     *
     * 检验业务数据是否存在流程中数据（以创建时间最大的数据的数据状态判断）
     * <功能详细描述>
     * @param wflSpecialValidInputParameter 函数输入参数
     * @return 是否
     * @see
     * @since 1.0
     */
    @DataSource(DataSourceType.OTHER)
    @Transactional(propagation=Propagation.REQUIRES_NEW)
    @Override
    public boolean isInWflProcess(WflSpecialValidInputParameter wflSpecialValidInputParameter){
        // 查询流程中数据"01"
        wflSpecialValidInputParameter.setSeachMode("01");
        List<String> result = mstWorkflowCommonService.getBusinessData(
                wflSpecialValidInputParameter);
        return !StringUtil.isEmptyList(result);
    }

    /**
     * 
     * 获取数据状态（以创建时间最大的数据的数据状态判断）
     * <功能详细描述>
     * @param wflValidInputParameter 函数输入参数
     * @return 数据状态
     * @see
     * @since 1.0
     */
    @Transactional(propagation=Propagation.REQUIRES_NEW)
    @DataSource(DataSourceType.OTHER)
    @Override
    public String getDataStatusFromProcessMark(WflValidInputParameter wflValidInputParameter){
      //查询数据状态"02"
        wflValidInputParameter.setSeachMode("02");
        List<String>result = mstWorkflowCommonService.getBusinessData(wflValidInputParameter);
        //为空则不展示
        if(StringUtil.isEmptyList(result)){
            return null;
        }
        return result.get(0);
    }

    /**
     * -----add jjpei------------ 获取数据状态（以创建时间最大的数据的数据状态判断） <功能详细描述>
     *
     * @param wflValidInputParameter 函数输入参数
     * @return 数据状态
     * @see
     * @since 1.0
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @DataSource(DataSourceType.OTHER)
    @Override
    public ProcessMark getDataEntityFromProcessMark(WflValidInputParameter wflValidInputParameter) {
        // 查询流程中数据"01"
        wflValidInputParameter.setSeachMode("01");
        List<ProcessMark> result = mstWorkflowCommonServiceMapper.getBusinessDataEntity(
                wflValidInputParameter);
        // 为空则不展示
        if (StringUtil.isEmptyList(result)) {
            return null;
        }
        return result.get(0);
    }

    /**
     * 通过留痕表查询流程中数据,返回list
     * add by jiangpeng on 20210514 ZGBSSE-388
     * @param wflValidInputParameter 函数输入参数
     * @return 数据状态
     * @since 1.0
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @DataSource(DataSourceType.OTHER)
    @Override
    public List<ProcessMark> getDataEntityFromProcessMarkList(WflValidInputParameter wflValidInputParameter) {
        // 查询流程中数据"01"
        wflValidInputParameter.setSeachMode("01");
        List<ProcessMark> result = mstWorkflowCommonServiceMapper.getBusinessDataEntity(
                wflValidInputParameter);
        // 为空则不展示
        if (StringUtil.isEmptyList(result)) {
            return null;
        }
        return result;
    }

    /**
     * 
     * 查询工作流数据
     * <功能详细描述>
     * @param wflValidInputParameter
     * @return
     * @see
     * @since 1.0
     */
    @DataSource(DataSourceType.OTHER)
    @Transactional(propagation=Propagation.REQUIRES_NEW)
    public List<String>getBusinessData(WflValidInputParameter wflValidInputParameter){
        return mstWorkflowCommonServiceMapper.getBusinessData(wflValidInputParameter);
    }

    /**
     * 查询工作流数据 <功能详细描述>
     *
     * @param wflSpecialValidInputParameter
     * @return
     * @see
     * @since 1.0
     */
    @DataSource(DataSourceType.OTHER)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<String> getBusinessData(WflSpecialValidInputParameter wflSpecialValidInputParameter) {
        return mstWorkflowCommonServiceMapper.getSpecialBusinessData(
                wflSpecialValidInputParameter);
    }

	/**
	 * {@inheritDoc}
	 */
    @Override
	@DataSource(DataSourceType.OTHER)
    @Transactional(propagation=Propagation.REQUIRES_NEW)
	public String getBusinessEntityByWorkFlowId(String workFlowId,String beanName) {
		ProcessMark entity = mstWorkflowCommonServiceMapper.getBusinessInfo(workFlowId);
		return StringUtil.isNotEmpty(entity)?entity.getBusinessData():null;
	}
}
