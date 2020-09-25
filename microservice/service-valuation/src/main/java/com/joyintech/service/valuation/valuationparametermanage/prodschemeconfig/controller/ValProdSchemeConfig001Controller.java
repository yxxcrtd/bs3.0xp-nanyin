/*
 * 文件名：ValProdSchemeConfig001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：weipeng
 * 修改时间：2018年11月20日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.service.valuation.valuationparametermanage.prodschemeconfig.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.valuation.accounadjustmanage.assetbook.service.ValAssetBook001Service;
import com.joyintech.service.valuation.valuationparametermanage.prodschemeconfig.dto.ValProdSchemeConfig001Dto;
import com.joyintech.service.valuation.valuationparametermanage.prodschemeconfig.service.ValProdSchemeConfig001Service;

/**
 * 产品对账方案
 * 
 * @author weipeng
 * @version 1.0 2018年11月20日
 * @see ValProdSchemeConfig001Controller
 * @since 1.0
 */
@RestController(value="serviceValProdSchemeConfig001Controller")
@RequestMapping("/ValProdSchemeConfig001")
public class ValProdSchemeConfig001Controller extends BaseController {

	@Autowired
	private ValProdSchemeConfig001Service valProdSchemeConfig001Service;
	@Autowired
	private ValAssetBook001Service valAssetBook001Service;

	@PostMapping("/getSchemeInfoList")
	public List<SelectOptionDto> getSchemeInfoList(@RequestBody Map<String, Object> params) {
		return valProdSchemeConfig001Service.getSchemeInfoList(params);
	}

	/**
	 * 记账凭证数据取得
	 *
	 * @return 记账凭证数据
	 * @throws Exception
	 *             异常
	 * @see
	 */
	@PostMapping("/findPage/{page}/{length}")
	public Page<ValProdSchemeConfig001Dto> findPage(@RequestBody Map<String, Object> param,
			@PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
		return valProdSchemeConfig001Service.findPage(page, length, param);

	}

	/**
	 * 导出信息excel
	 * 
	 * @return excel
	 * @throws Exception
	 *             异常
	 * @see
	 * @since 1.0
	 */
	@PostMapping("/selectAll")
	public List<ValProdSchemeConfig001Dto> selectAll(@RequestBody Map<String, Object> params) throws Exception {
		return valProdSchemeConfig001Service.selectAll(params);
	}

}
