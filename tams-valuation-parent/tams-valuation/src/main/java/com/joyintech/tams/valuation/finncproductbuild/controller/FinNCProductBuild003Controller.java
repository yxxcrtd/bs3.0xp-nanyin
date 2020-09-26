/*
 * 文件名：FinNCProductBuild003Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：tanxuan
 * 修改时间：2020年02月26日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.finncproductbuild.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.valuation.finncproductbuild.dto.ValProductInfoFeeDto;
import com.joyintech.tams.valuation.finncproductbuild.service.FinNCProductBuild003Service;

/**
 * 
 * 费用调整
 * @author tanxuan
 * @version 1.0 2020年02月26日
 * @see FinNCProductBuild003Controller
 * @since 1.0
 */
@Controller
@PageId("FinNCProductBuild003")
@RequestMapping("/valuation/finncproductbuild/FinNCProductBuild003")
public class FinNCProductBuild003Controller extends BaseController{
	
    @Autowired
    private FinNCProductBuild003Service finNCProductBuild003Service;
	@Override
	public String init(Model model) {
		String finprodId = getParam("finprodId");
		String finprodType = getParam("finprodType");
		String cashType2 = getParam("cashType2");
		String basis = getParam("basis");
		String baseType = getParam("baseType");
		String baseDateType = getParam("baseDateType");
		ValProductInfoFeeDto dto = new ValProductInfoFeeDto();
		dto.setFinprodId(finprodId);
		dto.setFinprodType(finprodType);
		dto.setCashType2(cashType2);
		dto.setBasis(basis);
		dto.setBaseType(baseType);
		dto.setBaseDateType(baseDateType);
		model.addAttribute("entity", dto);
		super.setSelectOptionToModel(model, DictCode.现金流二级类型);
		super.setSelectOptionToModel(model, DictCode.计息基础);
		super.setSelectOptionToModel(model, DictCode.基数类型);
		super.setSelectOptionToModel(model, DictCode.基数日期类型);
		super.setSelectOptionToModel(model, DictCode.金融产品类型);
		return super.init(model);
	}
	
	/**
	 * 费率调整保存
	 * 
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = PAGE_SAVE, method = RequestMethod.POST)
	public AjaxResult save(@RequestBody ValProductInfoFeeDto dto, BindingResult bindingResult)
			throws Exception {
		finNCProductBuild003Service.adjustRate(dto);
		return new AjaxResult();
	}
	
	@ResponseBody
	@RequestMapping("check")
	public AjaxResult check(@RequestBody @Valid ValProductInfoFeeDto dto, BindingResult bindingResult)
			throws Exception {
		String operateType = dto.getOperateType();
		return commonCheck(bindingResult, operateType);
	}

}
