package com.joyintech.service.product.rddesgin.productSimulationTrial.controller;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.product.rddesgin.productSimulationTrial.dto.ProductSimulationTrialDto;
import com.joyintech.service.product.rddesgin.productSimulationTrial.service.ProductSimulationTrialService;
import com.joyintech.tams.common.base.entity.MstIntRateInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.annotation.PageId;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 产品模拟试算controller
 * 
 * @author jiaobo
 * @version 1.0 2019年10月29日
 * @see ProductSimulationTrialDto
 * @since 1.0
 */
@RestController("productSimulationTrialController_dtx")
@PageId("ProductSiulationTrial001")
@RequestMapping("/ProductSimulationTrial")
public class ProductSimulationTrialController {

	@Autowired
	private ProductSimulationTrialService productSimulationTrialService;
	
	/**
	 * 通过流水号获取产品模拟试算结果
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param paramMap
	 * @return
	 * @see
	 * @since 1.0
	 */
	@PostMapping("getProductSimulationTrialBySeqNo")
	public ProductSimulationTrialDto getProductSimulationTrialBySeqNo(@RequestBody Map<String, Object> paramMap) {
        return productSimulationTrialService.getProductSimulationTrialBySeqNo(paramMap);
	}

	/**
	 * 查询模拟试算交易
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param page
	 * @param length
	 * @param paramMap
	 * @return
	 * @see
	 * @since 1.0
	 */
	@PostMapping("/findTablePage/{page}/{length}")
	public Page<ProductSimulationTrialDto> findTablePage(@PathVariable int page,@PathVariable int length,
                                                @RequestBody Map<String, Object> paramMap) {
        return productSimulationTrialService.findTablePage(page,length,paramMap);
    }

	
	/**
	 * 产品设计系列编号下拉框
	 * @param paramMap 过滤参数
	 * @return 下拉框列表
	 * @throws Exception
	 */
	@PostMapping("queryDesignIdInfo/{page}/{length}")
	public Page<SelectOptionDto> queryProdIdInfo(@PathVariable int page,@PathVariable int length,
	                                             @RequestBody Map<String, Object> paramMap) {
		return productSimulationTrialService.queryDesignIdInfo(page,length,paramMap);
	}

	/**
	 * 分页查询
	 *
	 * @return Page<ProductSimulationTrialDto>泛型的page实体类
	 * @throws Exception 异常
	 * @see
	 * @since 1.0
	 */
	@RequestMapping("findPage/{page}/{length}")
	public Page<ProductSimulationTrialDto> queryForPage(@RequestBody Map<String, Object> pageParam,
														@PathVariable("page") int page,
														@PathVariable("length") int length)
			throws Exception {
		return productSimulationTrialService.findPage(page, length, pageParam);
	}
	
	/**
	 * 
	 * 待试算产品的产品规模查询
	 * <功能详细描述>
	 * @param params
	 * @return
	 * @see
	 * @since 1.0
	 */
	@PostMapping("getPrdScale")
	public BigDecimal getPrdScale(@RequestBody Map<String, Object> params) {
        return productSimulationTrialService.getPrdScale(params);
	}
	
	/**
	 * 利率查询
	 * <功能详细描述>
	 * @param param
	 * @return
	 * @see
	 * @since 1.0
	 */
	@PostMapping("queryMstIntRateInfo")
    public List<MstIntRateInfo> queryMstIntRateInfo(@RequestBody Map<String,Object> param) {
        return productSimulationTrialService.queryMstIntRateInfo(param); 
    }
	
	@Transactional
	@DTXTransaction
	@PostMapping("deleteMockTrade")
	public boolean deleteMockTrade(@RequestBody Map<String,Object> param) {
	    return productSimulationTrialService.deleteMockTrade(param);
	}
	
	/**
	 * 获取新的批次号
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @return
	 * @see
	 * @since 1.0
	 */
	@PostMapping("getNewBacthId")
	public String getNewBacthId(@RequestBody Map<String,Object> param){
	    return productSimulationTrialService.getNewBacthId(param);
	}
	
	/**
	 * 浮动利率时获取浮动利率下拉框
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param page
	 * @param length
	 * @param param
	 * @return
	 * @see
	 * @since 1.0
	 */
	@PostMapping("/getIndexIdSource/{page}/{length}")
	public Page<SelectOptionDto> getIndexIdSource(@PathVariable int page,@PathVariable int length,
	                                              @RequestBody Map<String, Object> param) {
	    return productSimulationTrialService.getIndexIdSource(page,length,param);
	}
}
