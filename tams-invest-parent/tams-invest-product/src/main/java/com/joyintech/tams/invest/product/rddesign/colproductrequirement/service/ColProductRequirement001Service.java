package com.joyintech.tams.invest.product.rddesign.colproductrequirement.service;

import java.util.Date;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.product.rddesign.colproductrequirement.dto.ColProductRequirement001Dto;
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
public class ColProductRequirement001Service extends CommonExportService<ColProductRequirement001Dto>
		implements BaseProcessService<ColProductRequirement001Dto> {

	/**
	 * 产品需求收集页面查询
	 * 
	 * @param map
	 * @return
	 */
	public Page<ColProductRequirement001Dto> selectForPage(Map<String, Object> map) {
		Page<ColProductRequirement001Dto> pageDto = restTemplate.postForPage(
				ServiceApplication.PRODUCT_SERVICE_NAME + "/ColProductRequirement/findPage", map,
				ColProductRequirement001Dto.class);
		return pageDto;
	}

	@Override
	public void doComplete(ColProductRequirement001Dto entity, OperationType type) {
		if (type == OperationType.DELETE) {
			String dataStatus = DictCodeListExt1.有效状态_撤销.getValue();
			entity.setDataStatus(dataStatus);
			restTemplate.postForObject(
					ServiceApplication.PRODUCT_SERVICE_NAME + "/ColProductRequirement/deleteByRequirementId",
					entity,
					Integer.class);
		}

	}

	@Override
	protected void buildExportParam(ExportParam exportParam) {
		exportParam.config("/com/joyintech/tams/invest/product/rddesign/colproductrequirement/conf/export_conf.xml")
				.dataSource(DataSourceType.MASERT);
	}
	
	@Override
	public Page<ColProductRequirement001Dto> selectPageData(Map<String, Object> selectParam) {
		return selectForPage(selectParam);
	}
	
	/**
	 * 发起初评
	 * @param colProductRequirement001Dto
	 */
	public void reviewStart(ColProductRequirement001Dto colProductRequirement001Dto) {
		colProductRequirement001Dto.setReviewYesno(DictCodeListExt1.是否_是.getValue());
		restTemplate.postForObject(
				ServiceApplication.PRODUCT_SERVICE_NAME + "/ColProductRequirement/reviewStart",
				colProductRequirement001Dto,
				Integer.class);
	}
}
