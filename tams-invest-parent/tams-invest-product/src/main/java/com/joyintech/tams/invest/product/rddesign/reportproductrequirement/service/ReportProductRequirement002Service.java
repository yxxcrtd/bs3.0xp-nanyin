package com.joyintech.tams.invest.product.rddesign.reportproductrequirement.service;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.product.rddesign.reportproductrequirement.dto.ReportProductRequirement002Dto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 产品需求收集service
 * 
 * @author yaozhenguo
 * @version 1.0 2018年8月21日
 * @see ColProductRequirement001Dto
 * @since 1.0
 */
@Service
public class ReportProductRequirement002Service extends CommonExportService<ReportProductRequirement002Dto>
		implements BaseProcessService<ReportProductRequirement002Dto> {

	/**
	 * 产品需求收集页面查询
	 * 
	 * @param map
	 * @return
	 */
	public ReportProductRequirement002Dto queryRequirementByRequireId(String requirementId) {
		ReportProductRequirement002Dto entity = restTemplate.getForObject(
				ServiceApplication.PRODUCT_SERVICE_NAME
						+ "/ReportProductRequirement/queryRequirementByRequireId?requirementId=" + requirementId,
				ReportProductRequirement002Dto.class);
		return entity;
	}

	@Override
	public void doComplete(ReportProductRequirement002Dto entity, OperationType type) {
		if (type == OperationType.MODIFY) {
			int count = restTemplate.getForObject(ServiceApplication.PRODUCT_SERVICE_NAME
					+ "/ReportProductRequirement/countByRequirementId?requirementId=" + entity.getRequirementId(),
					Integer.class);
			if (count > 0) {
				entity.setUpdateUser(SecurityUtil.getLoginUser().getUserName());
				entity.setUpdateTime(new Date());
				restTemplate.postForObject(ServiceApplication.PRODUCT_SERVICE_NAME
						+ "/ReportProductRequirement/updateReportStatus", entity, Integer.class);
			}else {
				entity.setCreateUser(SecurityUtil.getLoginUser().getUserName());
				entity.setCreateTime(new Date());
				entity.setUpdateUser(SecurityUtil.getLoginUser().getUserName());
				entity.setUpdateTime(new Date());
				restTemplate.postForObject(ServiceApplication.PRODUCT_SERVICE_NAME
						+ "/ReportProductRequirement/insertColProductRequirementAdd", entity, Integer.class);
			}

		}

	}

}
