package com.joyintech.tams.invest.product.productplanning.tacticconfig.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.product.productplanning.tacticconfig.dto.TacticConfig001Dto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
/**
 * yaozhenguo 
 * @author Administrator
 *	20191228 战术配置优化
 */
@Service
public class TacticConfig001Service extends CommonExportService<TacticConfig001Dto>
		implements BaseProcessService<TacticConfig001Dto> {

	@Override
	public void doComplete(TacticConfig001Dto entity, OperationType type) {
		if(type == OperationType.DELETE) {
			restTemplate.postForObject(
					ServiceApplication.PRODUCT_SERVICE_NAME + "/TacticConfig/deleteTacticConfig", entity,
					Integer.class);
		}

	}

	public Page<TacticConfig001Dto> selectForPage(Map<String, Object> param) {
		return restTemplate.postForPage(ServiceApplication.PRODUCT_SERVICE_NAME + "/TacticConfig/selectForPage",
				param, TacticConfig001Dto.class);
	}
	
	@Override
	protected void buildExportParam(ExportParam exportParam) {
		exportParam.config("/com/joyintech/tams/invest/product/productplanning/tacticconfig/conf/export_conf.xml")
		.dataSource(DataSourceType.MASERT);
	}
	
	@Override
	public Page<TacticConfig001Dto> selectPageData(Map<String, Object> selectParam) {
		return selectForPage(selectParam);
	}

}
