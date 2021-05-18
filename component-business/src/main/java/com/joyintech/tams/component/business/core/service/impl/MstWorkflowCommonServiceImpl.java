/*
 * 文件名：MstWorkflowCommonServiceImpl.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Administrator
 * 修改时间：2018年9月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.business.core.service.impl;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.JoyinRestTemplate;
import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.tams.component.business.core.dto.WflSpecialValidInputParameter;
import com.joyintech.tams.component.business.core.dto.WflValidInputParameter;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 工作流共通实现类
 * 〈功能详细描述〉
 * @author Administrator
 * @version 1.0 2018年9月18日
 * @see MstWorkflowCommonServiceImpl
 * @since 1.0
 */
@Service("componentMstWorkflowCommonService")
public class MstWorkflowCommonServiceImpl implements MstWorkflowCommonService{
	
    @Autowired
    private JoyinRestTemplate restTemplate;
    
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isInWflProcess(WflValidInputParameter wflValidInputParameter) {
		// 生成的业务编号
        return restTemplate.postForObject(
            ServiceApplication.COMMON_SERVICE_NAME + "/core/isInWflProcess", wflValidInputParameter,
            Boolean.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isInWflProcess(WflSpecialValidInputParameter wflSpecialValidInputParameter) {
		// 生成的业务编号
		return restTemplate.postForObject(
				ServiceApplication.COMMON_SERVICE_NAME + "/core/isInWflProcessSpe", wflSpecialValidInputParameter,
				Boolean.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isProcessData(WflValidInputParameter wflValidInputParameter) {
		// 生成的业务编号
		return restTemplate.postForObject(
				ServiceApplication.COMMON_SERVICE_NAME + "/core/isProcessData", wflValidInputParameter,
				Boolean.class);
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getDataStatusFromProcessMark(WflValidInputParameter wflValidInputParameter) {
		// 获取数据状态
        return restTemplate.postForObject(
            ServiceApplication.COMMON_SERVICE_NAME + "/core/getDataStatusFromProcessMark", wflValidInputParameter,
            String.class);
	}
	/**
	 * 通过留痕表查询流程中数据,返回list
	 * add by jiangpeng on 20210514 ZGBSSE-388
	 * @param wflValidInputParameter 查询条件
	 * @return list
	 */
	@Override
	public List<ProcessMark> getDataStatusFromProcessMarkList(WflValidInputParameter wflValidInputParameter) {
		// 获取数据状态
		return restTemplate.postForList(
				ServiceApplication.COMMON_SERVICE_NAME + "/core/getDataEntityFromProcessMarkList", wflValidInputParameter,
				ProcessMark.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ProcessMark getDataEntityFromProcessMark(WflValidInputParameter wflValidInputParameter) {
		// 获取数据状态
		return restTemplate.postForObject(
				ServiceApplication.COMMON_SERVICE_NAME + "/core/getDataEntityFromProcessMark", wflValidInputParameter,
				ProcessMark.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
    @Override
	public <T> T getBusinessEntityByWorkFlowId(String workflowId, String beanName, Class<T> clazz) {
		Map<String,Object> map= new HashMap<>();
		map.put("workflowId", workflowId);
		map.put("beanName", beanName);
		// 获取业务entity
        String temp = restTemplate.postForObject(
            ServiceApplication.COMMON_SERVICE_NAME + "/core/getBusinessEntityByWorkFlowId", map,
            String.class);
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
