package com.joyintech.tams.invest.product.rddesign.productsimulationtrial.controller;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.product.rddesign.productsimulationtrial.dto.ProductSimulationTrial002Dto;
import com.joyintech.tams.invest.product.rddesign.productsimulationtrial.service.ProductSimulationTrial005Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.Map;

/**
 * 产品试算历史数据查询controller
 * 
 * @author jiaobo
 * @version 1.0 2019年11月2日
 * @see ProductSimulationTrial002Dto
 * @since 1.0
 */
@Controller
@PageId(value = "ProductSimulationTrial005")
@RequestMapping("/invest/product/rddesign/productsimulationtrial/ProductSimulationTrial005")
public class ProductSimulationTrial005Controller extends BaseController {

	@Autowired
	private ProductSimulationTrial005Service productSimulationTrial005Service;

	@Override
	public String init(Model model) {
		setSelectOptionToModel(model, DictCode.资产一级分类,DictCode.资产三级分类,DictCode.资产四级分类,DictCode.资产五级分类,DictCode.评级结果,DictCode.企业性质);
		model.addAttribute("requirementUser", SecurityUtil.getLoginUser().getUid());
		return super.init(model);
	}

	/**
	 * 查询三级分类多选框
	 *
	 * @return
	 * @see
	 * @since 1.0
	 */
	@ResponseBody
	@RequestMapping("queryMulType3")
	public Page<SelectOptionDto> queryMulType3() {
		Map<String, Object> map = RequestUtil.getPageParam();
		return productSimulationTrial005Service.queryMulType3(map);
	}

	/**
	 * 查询四级分类多选框
	 *
	 * @return
	 * @see
	 * @since 1.0
	 */
	@ResponseBody
	@RequestMapping("queryMulType4")
	public Page<SelectOptionDto> queryMulType4() {
		Map<String, Object> map = RequestUtil.getPageParam();
		return productSimulationTrial005Service.queryMulType4(map);
	}

	/**
	 * 查询五级分类多选框
	 *
	 * @return
	 * @see
	 * @since 1.0
	 */
	@ResponseBody
	@RequestMapping("queryMulType5")
	public Page<SelectOptionDto> queryMulType5() {
		Map<String, Object> map = RequestUtil.getPageParam();
		return productSimulationTrial005Service.queryMulType5(map);
	}



}
