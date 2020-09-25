package com.joyintech.service.product.productelement.reportregister.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.product.productelement.reportregister.dto.ProductReportRegisterDto;
import com.joyintech.service.product.productelement.reportregister.service.ProductReportRegisterService;

@RestController("backProductReportRegisterController_dtx")
@RequestMapping("/productReportRegister")
public class ProductReportRegisterController {
	
	@Autowired
	private ProductReportRegisterService productReportRegisterService;
	
	@PostMapping("selectForPage/{page}/{length}")
	public Page<ProductReportRegisterDto> selectForPage(@RequestBody Map<String, Object> map ,@PathVariable("page") int page, @PathVariable("length") int length){
		return productReportRegisterService.selectForPage(map,page,length);
	}
	
	/**
	 * 产品代码的下拉框
	 * 
	 * @return 查询结果集
	 * @throws Exception
	 *             异常
	 */
	@PostMapping("queryProdIdInfo")
	public List<SelectOptionDto> queryProdIdInfo(@RequestBody Map<String, Object> param) {
		return productReportRegisterService.queryProdIdInfo(param);
	}
	
	
	/**
	 * 行内标识码的下拉框
	 * 
	 * @return 查询结果集
	 * @throws Exception
	 *             异常
	 */
	@PostMapping("queryFinprodInsId")
	public List<SelectOptionDto> queryFinprodInsId(@RequestBody Map<String, Object> param) {
		return productReportRegisterService.queryFinprodInsId(param);
	}
	
	/**
	 * 根据产品ID查询
	 * @param repFinprodId
	 * @return
	 */
	@RequestMapping("selectByPrimaryKey")
	public ProductReportRegisterDto selectByPrimaryKey(String repFinprodId) {
		return productReportRegisterService.selectByPrimaryKey(repFinprodId);
	}
	
	/**
	 * 新建插入
	 * @param productReportRegisterDto
	 */
	@DTXTransaction
	@Transactional
	@PostMapping("insertSelective")
	public void insertSelective(@RequestBody ProductReportRegisterDto productReportRegisterDto) {
		productReportRegisterService.insertSelective(productReportRegisterDto);
		return;
	}
	
	/**
	 * 编辑更新
	 * @param productReportRegisterDto
	 */
	@DTXTransaction
	@Transactional
	@PostMapping("updateByPrimaryKeySelective")
	public void updateByPrimaryKeySelective(@RequestBody ProductReportRegisterDto productReportRegisterDto) {
		productReportRegisterService.updateByPrimaryKeySelective(productReportRegisterDto);
	}
	
	/**
	 * 根据产品Id删除
	 * @param repFinprodId
	 */
	@DTXTransaction
	@Transactional
	@GetMapping("deleteByPrimaryKey")
	public void deleteByPrimaryKey(String repFinprodId) {
		productReportRegisterService.deleteByPrimaryKey(repFinprodId);
	}
	
	/**
	 * 根据产品ID查询是否关联产品
	 * @param repFinprodId
	 * @return
	 */
	@GetMapping("selsectByFinprod")
	public int selsectByFinprod(String repFinprodId) {
		return productReportRegisterService.selsectByFinprod(repFinprodId);
	}
	
	/**
	 * 根据行内识别码查询是否有重复数据
	 * @param repFinprodInsId
	 * @return
	 */
	@GetMapping("selsectByFinprodInsId")
	public int selsectByFinprodInsId(String repFinprodInsId) {
		return productReportRegisterService.selsectByFinprodInsId(repFinprodInsId);
	}
}
