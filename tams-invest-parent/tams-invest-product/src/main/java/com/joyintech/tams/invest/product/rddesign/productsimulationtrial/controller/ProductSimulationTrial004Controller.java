package com.joyintech.tams.invest.product.rddesign.productsimulationtrial.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.select.SelectDto;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.component.business.prod.BussBenchMarketInfoService;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.product.rddesign.productsimulationtrial.dto.ProductSimulationTrial002Dto;
import com.joyintech.tams.invest.product.rddesign.productsimulationtrial.dto.ProductSimulationTrial003Dto;
import com.joyintech.tams.invest.product.rddesign.productsimulationtrial.dto.ProductSimulationTrial004Dto;
import com.joyintech.tams.invest.product.rddesign.productsimulationtrial.service.ProductSimulationTrial004Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 模拟交易controller
 * 
 * @author jiaobo
 * @version 1.0 2019年11月2日
 * @see ProductSimulationTrial002Dto
 * @since 1.0
 */
@Controller
@PageId(value = "ProductSimulationTrial004")
@RequestMapping("/invest/product/rddesign/productsimulationtrial/ProductSimulationTrial004")
public class ProductSimulationTrial004Controller extends BaseController {

	@Autowired
	private ProductSimulationTrial004Service productSimulationTrial004Service;

	@Autowired
	private BussBenchMarketInfoService bussBenchMarketInfoService;

	@Override
	public String init(Model model) {
		setSelectOptionToModel(model,DictCode.币种, DictCode.资产大类,DictCode.利率类型,DictCode.行情类型,DictCode.资产风险等级2);
		model.addAttribute("requirementUser", SecurityUtil.getLoginUser().getUid());
		return super.init(model);
	}

	/**
	 * 页面查询
	 *
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/findPage")
	public List<ProductSimulationTrial003Dto> findPage() {
	    List<ProductSimulationTrial003Dto> page =
				productSimulationTrial004Service.findPage(RequestUtil.getPageParam());
		return page;
	}

	/**
	 * check
	 *
	 * @param productSimulationTrial004Dto br 验证信息
	 * @return check结果
	 * @see
	 * @since 1.0
	 */
	@RequestMapping("check")
	@ResponseBody
	public AjaxResult check(@Valid @RequestBody ProductSimulationTrial004Dto productSimulationTrial004Dto, BindingResult br) {
	/*	return commonCheck(result, productSimulationTrial004Dto.getOperateType());*/
		AjaxResult result = new AjaxResult();
		if (br != null && br.hasErrors()) {
			return new AjaxResult(br);
		}

		return result;
	}
	
	/**
	 * 浮动利率时获取浮动利率下拉框
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param param
	 * @return
	 * @see
	 * @since 1.0
	 */
	@ResponseBody
	@RequestMapping("/getIndexIdSource")
	public Page<SelectOptionDto> getIndexIdSource(@RequestParam Map<String, Object> param){
        return productSimulationTrial004Service.getIndexIdSource(param); 
	}

}
