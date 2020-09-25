package com.joyintech.tams.invest.product.productplanning.assetclassmanagement.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.product.productplanning.assetclassmanagement.dto.AssetClassManagement001Dto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

@Service
public class AssetClassManagement002Service extends CommonExportService<AssetClassManagement001Dto>
		implements BaseProcessService<AssetClassManagement001Dto> {

	/**
	 * 新建
	 * 
	 * @author dongzhikang
	 * @param params
	 * @return
	 * @throws Exception
	 */
	@Override
	public void doComplete(AssetClassManagement001Dto entity, OperationType type) {

		if (StringUtil.equals(type.toString(), "NEW")) {
			restTemplate.postForObject(ServiceApplication.PRODUCT_SERVICE_NAME + "/AssetClassManagement/doCompeleteAdd",
					entity, Integer.class);
		}
		if (StringUtil.equals(type.toString(), "MODIFY")) {
			restTemplate.postForObject(
					ServiceApplication.PRODUCT_SERVICE_NAME + "/AssetClassManagement/doCompeleteUpdate", entity,
					Integer.class);
		}
	}

	/**
	 * 
	 * 新建页面，根据主键查询返回数据
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public AssetClassManagement001Dto selectByPrimaryKey(Map<String, Object> param) {
		return restTemplate.postForObject(
				ServiceApplication.PRODUCT_SERVICE_NAME + "/AssetClassManagement/selectByPrimaryKey", param,
				AssetClassManagement001Dto.class);
	}

}
