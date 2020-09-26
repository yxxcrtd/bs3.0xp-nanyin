/*
 * 文件名：ValuationMethodScheme002Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：2018年8月28日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.valuationparametermanage.valuationmethodscheme.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.valuationparametermanage.valuationmethodscheme.dto.ValuationMethodScheme002Dto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * 估值方案
 * @author zhangwei
 * @version 1.0 2018年8月28日
 * @see ValuationMethodScheme002Service
 * @since 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ValuationMethodScheme002Service extends CommonExportService<ValuationMethodScheme002Dto> implements BaseProcessService<ValuationMethodScheme002Dto>{
	
	/**
	 * 流程审核完成 {@inheritDoc}
	 */
	@Override
	public void doComplete(ValuationMethodScheme002Dto entity, OperationType type) {
		String action = StringUtil.EMPTY;
	   	 if (OperationType.DELETE.equals(type)) {
	            action = "delete";
	        }
	        else if (OperationType.NEW.equals(type)) {
	            action = "insert";
//	            if (StringUtils.isNotEmpty(entity.getOldSchemeId())){
//	            	ValuationMethodScheme003Dto copy002 = new ValuationMethodScheme003Dto();
//	            	copy002.setSchemeId(entity.getOldSchemeId());
//	      	   	  restTemplate.postForObject(
//	  	   	            ServiceApplication.VALUATION_SERVICE_NAME + "/ValuationMethodScheme003/copy", copy002,
//	  	   	            Integer.class);
//	            }
	        }
	        else if(OperationType.MODIFY.equals(type)){
	            action = "update";
	        }
	   	  restTemplate.postForObject(
	   	            ServiceApplication.VALUATION_SERVICE_NAME + "/ValuationMethodScheme002/" + action, entity,
	   	            Integer.class);
	}
	
	/**
	 * 通过方案代码查询 方案
	 * @param schemeId
	 * @return
	 */
	public ValuationMethodScheme002Dto findBySchemeId(String schemeId) {
		return restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME
                + "/ValuationMethodScheme002/findBySchemeId",
                schemeId, ValuationMethodScheme002Dto.class);
	}

	/**
	 * 通过 查询账套关联表 中 的估值方案;
	 *
	 * @return
	 */
	public int countBokBookSetPlan(String schId) {
		return restTemplate.getForObject(
				ServiceApplication.VALUATION_SERVICE_NAME + "/ValuationMethodScheme002/countBokBookSetPlan?schId="+schId,
				Integer.class);
	}
}
