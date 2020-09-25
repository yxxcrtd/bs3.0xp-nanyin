package com.joyintech.tams.invest.product.rddesign.colproductrequirement.service;

import java.util.Date;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.product.rddesign.colproductrequirement.dto.ColProductRequirement002Dto;
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
public class ColProductRequirement002Service extends CommonExportService<ColProductRequirement002Dto>
		implements BaseProcessService<ColProductRequirement002Dto> {

	/**
	 * 产品需求收集页面查询
	 * 
	 * @param map
	 * @return
	 */
	public ColProductRequirement002Dto queryRequirementByRequireId(String requirementId) {
		ColProductRequirement002Dto entity = restTemplate.getForObject(
				ServiceApplication.PRODUCT_SERVICE_NAME
						+ "/ColProductRequirement/queryRequirementByRequireId?requirementId=" + requirementId,
				ColProductRequirement002Dto.class);
		return entity;
	}

	@Override
	public void doComplete(ColProductRequirement002Dto entity, OperationType type) {
		if (type == OperationType.NEW) {
			entity.setDataStatus( DictCodeListExt1.有效状态_有效.getValue());
			entity.setCreateUser(SecurityUtil.getLoginUser().getUserName());
			entity.setCreateTime(new Date());
			entity.setUpdateUser(SecurityUtil.getLoginUser().getUserName());
			entity.setUpdateTime(new Date());
			entity.setReviewYesno(DictCodeListExt1.是否_否.getValue());
			restTemplate.postForObject(
					ServiceApplication.PRODUCT_SERVICE_NAME + "/ColProductRequirement/insertColProductRequirement", entity,
					Integer.class);
		}
		else if (type == OperationType.MODIFY) {
			entity.setUpdateUser(SecurityUtil.getLoginUser().getUserName());
			entity.setUpdateTime(new Date());
			restTemplate.postForObject(
					ServiceApplication.PRODUCT_SERVICE_NAME + "/ColProductRequirement/updateColProductRequirement", entity,
					Integer.class);
		}

	}

}
