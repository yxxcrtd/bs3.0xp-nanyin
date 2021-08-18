/*
 * 文件名：AssetImpairmentManagement001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xutong
 * 修改时间：2021-06-24
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.valuation.lossmodel.assetimpairmentmanagement.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.service.valuation.lossmodel.assetimpairmentmanagement.dao.AssetImpairmentManagement001Mapper;
import com.joyintech.service.valuation.lossmodel.assetimpairmentmanagement.dto.AssetImpairmentManagement001Dto;
import com.joyintech.service.valuation.lossmodel.assetimpairmentmanagement.dto.AssetImpairmentManagement002Dto;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.dto.modelDto.BatchAssetInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 资产减值管理
 * @author xutong
 * @createTime 2021-06-24
 * @see AssetImpairmentManagement001Service
 * @since 1.0
 */
@Service(value="serviceAssetImpairmentManagement001Service")
public class AssetImpairmentManagement001Service extends CommonExportService<AssetImpairmentManagement001Dto>{
	
	@Autowired
	private AssetImpairmentManagement001Mapper assetImpairmentManagement001Mapper;

	public Page<AssetImpairmentManagement001Dto> selectForPage(int page, int length, Map<String, Object> params)
			 throws Exception {
		 return super.queryForPage(page,length,() -> assetImpairmentManagement001Mapper.queryList(params));
	}

	public Page<SelectOptionDto> getBusiNum(int page, int length, Map<String, Object> pageParam) {
		return super.queryForPageForSelectReturn(page,length,()->assetImpairmentManagement001Mapper.getBusiNum(pageParam));
	}

	public AssetImpairmentManagement002Dto findByPrimaryKey(Map<String, Object> pageParam) {
		return assetImpairmentManagement001Mapper.findByPrimaryKey(pageParam);
	}

	public List<AssetImpairmentManagement001Dto> findPageout(Map<String, Object> params)
			throws Exception {
		return assetImpairmentManagement001Mapper.queryList(params);
	}

	/**
	 * 资产代码：下拉框 资产减值管理
	 *
	 * @param
	 * @return 资产代码：下拉框
	 * @throws Exception
	 * @see
	 * @since 1.0
	 */
	public List<SelectOptionDto> getFinprodIdForImpairment(Map<String, Object> param) throws Exception {
		return assetImpairmentManagement001Mapper.getFinprodIdForImpairment(param);
	}

	/**
	 * 产品代码：下拉框 资产减值管理
	 *
	 * @param
	 * @return 产品代码：下拉框
	 * @throws Exception
	 * @see
	 * @since 1.0
	 */
	public List<SelectOptionDto> getProdIdForImpairment(Map<String, Object> param) throws Exception {
		return assetImpairmentManagement001Mapper.getProdIdForImpairment(param);
	}

	/**
	 * 业务编号：下拉框 资产减值管理
	 *
	 * @param
	 * @return 业务编号：下拉框
	 * @throws Exception
	 * @see
	 * @since 1.0
	 */
	public List<SelectOptionDto> getBusiNoForImpairment(Map<String, Object> param) throws Exception {
		String currentDate = (String)param.get("currentDate");
		param.put("currentDate", DateUtil.parseDate(currentDate, "yyyy-MM-dd"));
		return assetImpairmentManagement001Mapper.getBusiNoForImpairment(param);
	}

	public List<BatchAssetInfoDto> getAllAssetInfo(Map<String, Object> params) {
		List<String> finprodIdList = (List<String>)params.get("finprodIdList");
		Boolean isImpairment = (Boolean)params.get("isImpairment");
		Map<String, Object> param = new HashMap<>(5);
		if (isImpairment != null && isImpairment) {
			param.put("execDate",params.get("execDate"));
			return assetImpairmentManagement001Mapper.getAssetInfoImpairment(param);
		} else {
			List<String> finprodTypes = new ArrayList<>(2);
			finprodTypes.add(DictCodeListExt1.金融产品类型_投管用_项目类资产.getValue());
			finprodTypes.add(DictCodeListExt1.金融产品类型_投管用_债券.getValue());
			param.put("finprodTypes", finprodTypes);
			if (!CollectionUtils.isEmpty(finprodIdList)) {
				param.put("finprodIdList", finprodIdList);
			}
			return assetImpairmentManagement001Mapper.getAssetInfo(param);
		}

	}

}
