/*
 * 文件名：ShadowPriceScheme002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年11月22日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.valuation.valuationparametermanage.shadowpricescheme.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.base.BaseController;
import com.joyintech.service.valuation.valuationparametermanage.shadowpricescheme.dto.ShadowPriceScheme001Dto;
import com.joyintech.service.valuation.valuationparametermanage.shadowpricescheme.dto.ShadowPriceScheme002Dto;
import com.joyintech.service.valuation.valuationparametermanage.shadowpricescheme.service.ShadowPriceScheme001Service;
import com.joyintech.service.valuation.valuationparametermanage.shadowpricescheme.service.ShadowPriceScheme002Service;
import com.joyintech.workflow.engine.constant.OperationType;

@RestController(value="serviceShadowPriceScheme002Controller")
@RequestMapping("/ShadowPriceScheme002")
public class ShadowPriceScheme002Controller extends BaseController {

	@Autowired
	private ShadowPriceScheme002Service ShadowPriceScheme002Service;
	

	@PostMapping("insert")
	public void insert(@RequestBody ShadowPriceScheme002Dto dto) throws Exception {
		ShadowPriceScheme002Service.doComplete(dto, OperationType.NEW);
	}

	/**
	 * 修改
	 * 
	 * @param dto
	 * @throws Exception
	 * @see
	 * @since 1.0
	 */
	@PostMapping("update")
	public void update(@RequestBody ShadowPriceScheme002Dto dto) throws Exception {
		ShadowPriceScheme002Service.doComplete(dto, OperationType.MODIFY);
	}

	/**
	 * 删除
	 * 
	 * @param dto
	 * @throws Exception
	 * @see
	 * @since 1.0
	 */
	@PostMapping("delete")
	public void delete(@RequestBody ShadowPriceScheme002Dto dto) throws Exception {
		ShadowPriceScheme002Service.doComplete(dto, OperationType.DELETE);
	}

}
