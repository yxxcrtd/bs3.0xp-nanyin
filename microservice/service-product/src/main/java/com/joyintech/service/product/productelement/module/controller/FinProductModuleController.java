/*
 * 文件名：FinProductModuleController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：何阳阳
 * 修改时间：2018年9月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.product.productelement.module.controller;

import java.util.List;
import java.util.Map;

import com.joyintech.framework.annotation.DTXTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.util.StringUtil;
import com.joyintech.tams.common.base.entity.PrdValParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.product.messageadjust.bmquotation.dto.FinProductBmQuotationDto;
import com.joyintech.service.product.productelement.module.dto.FinProductModuleInfoDto;
import com.joyintech.service.product.productelement.module.dto.FinProductPayPlanDto;
import com.joyintech.service.product.productelement.module.service.FinProductModuleService;

/**
 * 产品模板服务
 * @author 何阳阳
 * @version 1.0 2018年9月17日
 * @see FinProductModuleController
 * @since 1.0
 */
@RestController("backFinProductModuleController_dtx")
@RequestMapping("/FinProductModule")
public class FinProductModuleController {

	/**
	 * FinProductModuleService
	 */
	@Autowired
	private FinProductModuleService finProductModuleService;
	
	/**
	 * 理财产品估值参数获取
	 * @param finProductId 金融产品代码
	 * @return PrdValParam
	 */
	@GetMapping("prdValParam")
	public PrdValParam getPrdValParam(String finProductId) {
		if(StringUtil.isNotEmpty(finProductId)) {
			// TODO
		}
		return null;
	}
	
	/**
	 * 金融产品付息计划获取
	 * @return 付息计划
	 */
	@GetMapping("selectPrdPayPlan")
	public FinProductPayPlanDto selectFinProdPayPlan(String cashId, String finprodId) {
		return finProductModuleService.selectFinProdPayPlan(cashId, finprodId);
	}
	
	/**
	 * 金融产品付息计划添加
	 * @return 付息计划
	 */
	@Transactional
	@DTXTransaction
	@PutMapping("insertPrdPayPlan")
	public boolean insertFinProdPayPlan(@RequestBody FinProductPayPlanDto prdPayPlan) {
		finProductModuleService.insertFinProdPayPlan(prdPayPlan);
		return true;
	}
	
	/**
	 * 金融产品付息计划添加
	 * @return 付息计划
	 */
	@Transactional
	@DTXTransaction
	@PutMapping("updatePrdPayPlan")
	public boolean updateFinProdPayPlan(@RequestBody FinProductPayPlanDto prdPayPlan) {
		finProductModuleService.updateFinProdPayPlan(prdPayPlan);
		return true;
	}
	
	/**
	 * 金融产品付息计划添加
	 * @return 付息计划
	 */
	@Transactional
	@DTXTransaction
	@DeleteMapping("deletePrdPayPlan")
	public boolean deleteFinProdPayPlan(String cashId, String finprodId) {
		finProductModuleService.deleteFinProdPayPlan(cashId, finprodId);
		return true;
	}
	/**
	 * 模板名称的联想下拉框
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param param
	 * @return
	 * @see
	 * @since 1.0
	 */
	@PostMapping("/getTempletNameList/{page}/{length}")
	public Page<SelectOptionDto> getTempletNameList(@RequestBody Map<String, Object> map,
	                                                @PathVariable("page") int page, @PathVariable("length") int length) {
	    return finProductModuleService.getTempletNameList(page,length,map);
	}
	/**
     * 画面查询方法
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("/selectForPage/{page}/{length}")
    public Page<FinProductModuleInfoDto> selectForPage(@RequestBody Map<String, Object> param,
            @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        return finProductModuleService.selectForPage(page, length, param);
    }
    
    /**
     * 根据模板ID查询模板
     * @param templateId
     * @return
     */
    @GetMapping("/selectByTemplateId")
    public FinProductModuleInfoDto selectByTemplateId(String templateId) {
    	return finProductModuleService.selectByTemplateId(templateId);
    }
    @GetMapping("/getCashId")
    public String getCashId(String finprodId){
        return finProductModuleService.getCashId(finprodId);
    }
    /**
     * 删除
     * 
     * @param dto
     *            页面选中的数据
     * @param br
     *            br
     * @return 结果
     * @throws Exception
     *             异常
     */
	@Transactional
	@DTXTransaction
    @PostMapping("delete")
    public int delete(@RequestBody FinProductBmQuotationDto dto) throws Exception {
        return finProductModuleService.delete(dto);
    }
	
	/**
	 * 模板信息获取
	 * @param map Map<String, Object>
	 * @return PrdValParam
	 */
    @PostMapping("queryModuleInfoByFinprodId")
	public FinProductModuleInfoDto queryModuleInfoByFinprodId(@RequestBody Map<String, Object> map) {
		return finProductModuleService.queryModuleInfoByFinprodId(map);
	}
	
	/**
	 * 模板信息获取
	 * @param map Map<String, Object>
	 * @return PrdValParam
	 */
    @PostMapping("queryModuleInfoByFinprodName")
	public FinProductModuleInfoDto queryModuleInfoByFinprodName(@RequestBody Map<String, Object> map) {
		return finProductModuleService.queryModuleInfoByFinprodName(map);
	}
}
