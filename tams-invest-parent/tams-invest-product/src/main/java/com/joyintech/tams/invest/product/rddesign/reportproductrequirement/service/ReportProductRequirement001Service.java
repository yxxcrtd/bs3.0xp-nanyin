package com.joyintech.tams.invest.product.rddesign.reportproductrequirement.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.product.rddesign.reportproductrequirement.dto.ReportProductRequirement001Dto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 产品需求收集service
 * 
 * @author yaozhenguo
 * @version 1.0 2019年9月27日
 * @see ColProductRequirement001Dto
 * @since 1.0
 */
@Service
public class ReportProductRequirement001Service extends CommonExportService<ReportProductRequirement001Dto>
		implements BaseProcessService<ReportProductRequirement001Dto> {

	/**
	 * 产品需求收集页面查询
	 * 
	 * @param map
	 * @return
	 */
	public Page<ReportProductRequirement001Dto> selectForPage(Map<String, Object> map) {
		Page<ReportProductRequirement001Dto> pageDto = restTemplate.postForPage(
				ServiceApplication.PRODUCT_SERVICE_NAME + "/ReportProductRequirement/findPage", map,
				ReportProductRequirement001Dto.class);
		return pageDto;
	}



	@Override
	protected void buildExportParam(ExportParam exportParam) {
		exportParam.config("/com/joyintech/tams/invest/product/rddesign/reportproductrequirement/conf/export_conf.xml")
				.dataSource(DataSourceType.MASERT);
	}
	
	@Override
	public Page<ReportProductRequirement001Dto> selectPageData(Map<String, Object> selectParam) {
		return selectForPage(selectParam);
	}



	@Override
	public void doComplete(ReportProductRequirement001Dto entity, OperationType type) {
		// TODO Auto-generated method stub
		
	}

	
}
