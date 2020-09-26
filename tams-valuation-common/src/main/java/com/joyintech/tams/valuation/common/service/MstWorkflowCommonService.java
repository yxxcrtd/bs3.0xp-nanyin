/*
 * 文件名：MstWorkflowCommonService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：王波
 * 修改时间：2018年12月5日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.common.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.valuation.common.dao.MstWorkflowCommonMapper;
import com.joyintech.tams.valuation.common.dto.WflValidInputParameter;

/**
 * 工作流共通Service
 * 
 * @author 王波
 * @version 1.0 2018年12月6日
 * @see MstWorkflowCommonService
 * @since 1.0
 */
@Component("valMstWorkflowCommonService")
public class MstWorkflowCommonService {

    /**
     * 工作流CommonServicemapper
     */
    @Autowired(required = false)
    private MstWorkflowCommonMapper mstWorkflowCommonMapper;
    
    @DataSource(DataSourceType.OTHER)
    @Transactional(propagation=Propagation.REQUIRES_NEW)
    public boolean isInWflProcess(WflValidInputParameter wflValidInputParameter) {
        //查询流程中数据"01"
        wflValidInputParameter.setSeachMode("01");
        List<String>result = getBusinessData(wflValidInputParameter);
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
    public String getDataStatusFromProcessMark(WflValidInputParameter wflValidInputParameter){
      //查询数据状态"02"
        wflValidInputParameter.setSeachMode("02");
        List<String>result = getBusinessData(wflValidInputParameter);
        //为空则不展示
        if(StringUtil.isEmptyList(result)){
            return null;
        }
        return result.get(0);
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
    @Transactional(propagation=Propagation.MANDATORY)
    public List<String>getBusinessData(WflValidInputParameter wflValidInputParameter){
        return mstWorkflowCommonMapper.getBusinessData(wflValidInputParameter);
    }
    
    @DataSource(DataSourceType.OTHER)
    @Transactional(propagation=Propagation.MANDATORY)
    public String getBusinessEntityByWorkFlowId(String workFlowId,String beanName) {
        ProcessMark entity = mstWorkflowCommonMapper.getBusinessInfo(workFlowId);
        return entity.getBusinessData();
    }
    
    /**
	 * {@inheritDoc}  add by zhangquan 20200115
	 */
	@SuppressWarnings("unchecked")
    @Transactional(propagation=Propagation.REQUIRES_NEW)
	public <T> T getBusinessEntityByWorkFlowId(String workflowId, String beanName, Class<T> clazz) {
		Map<String,Object> map= new HashMap<>();
		map.put("workflowId", workflowId);
		map.put("beanName", beanName);
		// 获取业务entity
        String temp = getBusinessEntityByWorkFlowId(workflowId, beanName);
        Map<String,Object> all = JsonUtils.json2Object(temp,Map.class);
        try {
            if (all.get(beanName) != null){
                return (T)JsonUtils.map2entity((Map<String,Object>)all.get(beanName), clazz);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
	}
}
