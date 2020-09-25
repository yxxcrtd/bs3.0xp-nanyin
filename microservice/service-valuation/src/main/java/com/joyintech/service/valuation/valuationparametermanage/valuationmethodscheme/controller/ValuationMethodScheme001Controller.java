/*
 * 文件名：ValuationMethodScheme001.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：2018年8月27日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.valuation.valuationparametermanage.valuationmethodscheme.controller;

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
import com.joyintech.service.valuation.valuationparametermanage.valuationmethodscheme.dto.ValuationMethodScheme001Dto;
import com.joyintech.service.valuation.valuationparametermanage.valuationmethodscheme.service.ValuationMethodScheme001Service;

/**
 * 估值方法方案-主界面
 * 
 * @author zhangwei
 * @version 1.0 2018年8月27日
 * @see ValuationMethodScheme001Controller
 * @since 1.0
 */
@RestController(value="serviceValuationMethodScheme001Controller")
@RequestMapping("/ValuationMethodScheme001")
public class ValuationMethodScheme001Controller extends BaseController {

	@Autowired
	private ValuationMethodScheme001Service valuationMethodScheme001Service;

	/**
	 * 获取估值方案 - 分页
	 * 
	 * @return
	 * @throws Exception
	 */
	@PostMapping("findSchemeByPage/{page}/{length}")
	public Page<ValuationMethodScheme001Dto> findScheme(@RequestBody Map<String, Object> parameters,
			@PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
		return valuationMethodScheme001Service.findSchemeByPage(page, length, parameters);
	}

	/**
	 * 获取估值方案详情 - 分页
	 * 
	 * @return
	 * @throws Exception
	 */
	@PostMapping("findSchemeDetailByPage/{page}/{length}")
	public Page<ValuationMethodScheme001Dto> findSchemeDetail(@RequestBody Map<String, Object> parameters,
			@PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
		return valuationMethodScheme001Service.findSchemeDetailByPage(page, length, parameters);
	}

	/**
	 * 获取估值方案详情 - 分页：特殊资产估值
	 * 
	 * @return
	 * @throws Exception
	 */
	@PostMapping("findSpecialAssetsByPage/{page}/{length}")
	public Page<ValuationMethodScheme001Dto> findSpecialScheme(@RequestBody Map<String, Object> parameters,
			@PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
		return valuationMethodScheme001Service.findSpecialAssetsByPage(page, length, parameters);
	}

	/**
	 * 
	 * 导出：资产分类估值
	 * 
	 * @return
	 * @throws Exception
	 */
	@PostMapping("findSchemeDetail")
	public List<ValuationMethodScheme001Dto> findSchemeDetail(@RequestBody Map<String, Object> parameters)
			throws Exception {
		return valuationMethodScheme001Service.findSchemeDetail(parameters);
	}

	/**
	 * 
	 * 导出：特殊资产估值
	 * 
	 * @return
	 * @throws Exception
	 */
	@PostMapping("findSpecialAssets")
	public List<ValuationMethodScheme001Dto> findSpecialAssets(@RequestBody Map<String, Object> parameters)
			throws Exception {
		return valuationMethodScheme001Service.findSpecialAssets(parameters);
	}

	/**
	 * 下拉框 ： 产品代码
	 * 
	 * @return
	 * @throws Exception
	 */
	@PostMapping("findFinprod")
	public List<SelectOptionDto> findFinprod(@RequestBody Map<String, Object> parameters) throws Exception {
		return valuationMethodScheme001Service.findFinprod(parameters);
	}

	/**
	 * 联动：证券品种 - 交易流通场所 获得交易市场
	 * 
	 * @return
	 * @throws Exception
	 */
	@PostMapping("getLinkSelectOption")
	public List<SelectOptionDto> getLinkSelectOption(@RequestBody Map<String, Object> param) {
		return valuationMethodScheme001Service.getLinkSelectOption(param);
	}

	/**
	 * 新增/修改验证：基础验证、唯一性
	 * 
	 * @param dto
	 * @param bindingResult
	 * @return
	 * @throws Exception
	 */
	@PostMapping("findScheme")
	public List<SelectOptionDto> findScheme(@RequestBody Map<String, Object> parameters) throws Exception {
		return valuationMethodScheme001Service.findScheme(parameters);
	}
	
	/**
	 * 新增/修改验证：基础验证、唯一性
	 * 
	 * @param dto
	 * @param bindingResult
	 * @return
	 * @throws Exception
	 */
	@PostMapping("getIsdefault")
	public boolean getIsdefault() {
		return valuationMethodScheme001Service.getIsdefault();
	}
	
}
