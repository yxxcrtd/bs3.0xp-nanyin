package com.joyintech.tams.invest.product.valuation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.product.valuation.dto.FinProductValuation001Dto;
import com.joyintech.tams.invest.product.valuation.dto.FinProductValuationDetail001Dto;
import com.joyintech.tams.invest.product.valuation.service.FinProductValuation002Service;;

@Controller
@PageId("FinProductValuation002")
@RequestMapping("/invest/product/valuation/FinProductValuation002")
public class FinProductValuation002Controller extends BaseController {

	@Autowired
	private FinProductValuation002Service finProductValuation002Service;

	@Override
	public String init(Model model) {
		setSelectOptionToModel(model, DictCode.币种);
		model.addAttribute("valueid", getParam("valueid"));
		return super.init(model);
	}

	/**
	 * 初始化资产信息
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/initAssetInfo")
	public String initAssetInfo() {
		return "product/valuation/FinProductValuationAssetInfo";
	}

	/**
	 * 初始化负债信息
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/initDebtsInfo")
	public String initDebtsInfo() {
		return "product/valuation/FinProductValuationDebtsInfo";
	}

	/**
	 * 初始化费用信息
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/initFeeInfo")
	public String initFeeInfo() {
		return "product/valuation/FinProductValuationFeeInfo";
	}

	/**
	 * 估值结果
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/initResultInfo")
	public String initResultInfo(Model model) {
		FinProductValuation001Dto dto = finProductValuation002Service.selectResultInfo(getParam("valueid"));
		model.addAttribute(GlobalConstant.ENTITY, dto);
		return "product/valuation/FinProductValuationResultInfo";
	}

	/**
	 * 查询资产信息
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/selectAssetInfo")
	public List<FinProductValuationDetail001Dto> selectAssetInfo() {
		return finProductValuation002Service.selectValuationDetail(getParam("pvalueid"), "ASSET");
	}

	/**
	 * 查询负债信息
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/selectDebtsInfo")
	public List<FinProductValuationDetail001Dto> selectDebtsInfo() {
		return finProductValuation002Service.selectValuationDetail(getParam("pvalueid"), "DEBTS");
	}

	/**
	 * 查询费用信息
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/selectFeeInfo")
	public List<FinProductValuationDetail001Dto> selectFeeInfo() {
		return finProductValuation002Service.selectValuationDetail(getParam("pvalueid"), "FEE");
	}
}
