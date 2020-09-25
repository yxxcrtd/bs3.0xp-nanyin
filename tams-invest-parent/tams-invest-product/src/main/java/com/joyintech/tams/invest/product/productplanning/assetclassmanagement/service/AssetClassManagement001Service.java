/*
 * 文件名：AssetClassManagement001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangqidong
 * 修改时间：2019年12月10日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.product.productplanning.assetclassmanagement.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.product.productplanning.assetclassmanagement.dto.AssetClassManagement001Dto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author wangqidong
 * @version 1.0 2019年12月10日
 * @see AssetClassManagement001Service
 * @since 1.0
 */
@Service
public class AssetClassManagement001Service extends CommonExportService<AssetClassManagement001Dto>
		implements BaseProcessService<AssetClassManagement001Dto> {

	/**
	 * <一句话功能简述> <功能详细描述>
	 * 
	 * @param parameters
	 * @return
	 * @see
	 * @since 1.0
	 */
	public Page<AssetClassManagement001Dto> selectForPage(Map<String, Object> parameters) {
		/*
		 * 判断二级资产类型是否为空， 获取值
		 * 
		 */
		if (StringUtil.isNotEmpty(parameters.get("customAssetTypeTwo"))) {
			parameters.put("customAssetTypeTwo",
					StringUtil.splitToList(null, parameters.get("customAssetTypeTwo").toString()));
		}

		/*
		 * 判断三级资产类型是否为空， 获取值
		 * 
		 */
		if (StringUtil.isNotEmpty(parameters.get("customAssetTypeThree"))) {
			parameters.put("customAssetTypeThree",
					StringUtil.splitToList(null, parameters.get("customAssetTypeThree").toString()));
		}

		/*
		 * 判断四级资产类型是否为空， 获取值
		 * 
		 */
		if (StringUtil.isNotEmpty(parameters.get("customAssetTypeFour"))) {
			parameters.put("customAssetTypeFour",
					StringUtil.splitToList(null, parameters.get("customAssetTypeFour").toString()));
		}

		/*
		 * 判断五级资产类型是否为空， 获取值
		 * 
		 */
		if (StringUtil.isNotEmpty(parameters.get("customAssetTypeFive"))) {
			parameters.put("customAssetTypeFive",
					StringUtil.splitToList(null, parameters.get("customAssetTypeFive").toString()));
		}

		/*
		 * 判断 资产大类是否为空， 获取值
		 * 
		 */
		if (StringUtil.isNotEmpty(parameters.get("assetType"))) {
			parameters.put("assetType", StringUtil.splitToList(null, parameters.get("assetType").toString()));
		}

		/*
		 * 判断是否非标是否为空， 获取值
		 * 
		 */
		if (StringUtil.isNotEmpty(parameters.get("pinanAssetType"))) {
			parameters.put("pinanAssetType", StringUtil.splitToList(null, parameters.get("pinanAssetType").toString()));
		}

		return restTemplate.postForPage(ServiceApplication.PRODUCT_SERVICE_NAME + "/AssetClassManagement/selectForPage",
				parameters, AssetClassManagement001Dto.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void buildExportParam(ExportParam exportParam) {
		exportParam
				.config("/com/joyintech/tams/invest/product/productplanning/assetclassmanagement/conf/export_conf.xml")
				.dataSource(DataSourceType.MASERT);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void doComplete(AssetClassManagement001Dto entity, OperationType type) {
		entity.setOperateType(type.toString());
		entity.setUpdateUser(LoginUserUtil.getUserName());
		restTemplate.postForObject(ServiceApplication.PRODUCT_SERVICE_NAME + "/AssetClassManagement/doComplete", entity,
				Integer.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Page<AssetClassManagement001Dto> selectPageData(Map<String, Object> selectParam) {
		return selectForPage(selectParam);
	}

}
