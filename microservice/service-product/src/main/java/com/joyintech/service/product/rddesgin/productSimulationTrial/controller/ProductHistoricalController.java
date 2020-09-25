package com.joyintech.service.product.rddesgin.productSimulationTrial.controller;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.product.rddesgin.productSimulationTrial.dto.ProductHistoricalDto;
import com.joyintech.service.product.rddesgin.productSimulationTrial.service.ProductHistoricalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 历史记录查询controller
 * 
 * @author jiaobo
 * @version 1.0 2019年11月5日
 * @see ProductHistoricalDto
 * @since 1.0
 */
@RestController("productHistoricalController_dtx")
@PageId("ProductSiulationTrial005")
@RequestMapping("/ProductHistorical")
public class ProductHistoricalController {

	@Autowired
	private ProductHistoricalService productHistoricalService;

	/**
	 * 分页查询
	 *
	 * @return Page<ProductHistoricalDto>泛型的page实体类
	 * @throws Exception 异常
	 * @see   ProductHistoricalDto
	 * @since 1.0
	 */
	@RequestMapping("findPage")
	public List<ProductHistoricalDto> queryForPage(@RequestBody Map<String, Object> pageParam)
			throws Exception {
		if(pageParam.get("assetTypeOne")!=null){
			return productHistoricalService.findPage(pageParam);
		}
		return null;
	}

	/**
	 * 资产分类3多选下拉框加载
	 * @param pageParam 参数
	 * @return list
	 * @see
	 * @since 1.0
	 */
	@PostMapping("queryMulType3/{page}/{length}")
	public Page<SelectOptionDto> queryMulType3(@RequestBody Map<String, Object> pageParam,
											   @PathVariable("page") int page, @PathVariable("length") int length) {
		return productHistoricalService.queryMulType3(page, length, pageParam);
	}

	/**
	 * 资产分类4多选下拉框加载
	 * @param pageParam 参数
	 * @return list
	 * @see
	 * @since 1.0
	 */
	@PostMapping("queryMulType4/{page}/{length}")
	public Page<SelectOptionDto> queryMulType4(@RequestBody Map<String, Object> pageParam,
											   @PathVariable("page") int page, @PathVariable("length") int length) {
		return productHistoricalService.queryMulType4(page, length, pageParam);
	}

	/**
	 * 资产分类5多选下拉框加载
	 * @param pageParam 参数
	 * @return list
	 * @see
	 * @since 1.0
	 */
	@PostMapping("queryMulType5/{page}/{length}")
	public Page<SelectOptionDto> queryMulType5(@RequestBody Map<String, Object> pageParam,
											   @PathVariable("page") int page, @PathVariable("length") int length) {
		return productHistoricalService.queryMulType5(page, length, pageParam);
	}


}
