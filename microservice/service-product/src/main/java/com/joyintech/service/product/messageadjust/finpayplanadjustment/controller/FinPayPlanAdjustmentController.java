/*
 * 文件名：FinPayPlanAdjustmentController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwenbin
 * 修改时间：2018年10月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.product.messageadjust.finpayplanadjustment.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.product.common.dto.BussFinProductFeeInfoDto;
import com.joyintech.service.product.messageadjust.finpayplanadjustment.dto.FinPayPlanAdjustmentDto;
import com.joyintech.service.product.messageadjust.finpayplanadjustment.dto.FinPayRuleAdjustmentDto;
import com.joyintech.service.product.messageadjust.finpayplanadjustment.service.FinPayPlanAdjustmentService;
import com.joyintech.service.product.messageadjust.finpayplanadjustment.service.FinPayRuleAdjustmentService;
import com.joyintech.tams.common.base.entity.FinProduct;

import net.bytebuddy.asm.Advice.Return;

/**
 * 信息调整管理_付费计划调整
 * 〈功能详细描述〉
 * @author huwenbin
 * @version 1.0 2018年10月18日
 * @see FinPayPlanAdjustmentController
 * @since 1.0
 */
@RestController("backFinPayPlanAdjustmentController_dtx")
@RequestMapping("/FinPayPlanAdjustment")
public class FinPayPlanAdjustmentController {
    
    /**
     * 付费计划调整service
     */
    @Autowired
    private FinPayPlanAdjustmentService finPayPlanAdjustmentService;
    
    @Autowired
    private FinPayRuleAdjustmentService finPayRuleAdjustmentService;
    /**
     * 
     * 产品代码下拉框
     * <功能详细描述>
     * @param param Map<String, Object>
     * @return List<SelectOptionDto>
     * @see
     * @since 1.0
     */
    @PostMapping("/getFinprodId")
    public List<SelectOptionDto> getFinprodId(@RequestBody Map<String, Object> param){
        return finPayPlanAdjustmentService.getFinprodId(param);
    }
    
    /**
     * 
     * 产品款型下拉框
     * <功能详细描述>
     * @param param Map<String, Object>
     * @return List<SelectOptionDto>
     * @see
     * @since 1.0
     */
    @PostMapping("/getProdType")
    public List<SelectOptionDto> getProdType(@RequestBody Map<String, Object> param){
        return finPayPlanAdjustmentService.getProdType(param);
    }
    
    /**
     * 
     * 根据产品代码获得费用名称
     * <功能详细描述>
     * @param param Map<String, Object>
     * @return List<SelectOptionDto>
     * @see
     * @since 1.0
     */
    @PostMapping("/getFeeName")
    public List<SelectOptionDto> getFeeName(@RequestBody Map<String, Object> param){
        return finPayPlanAdjustmentService.getFeeName(param);
    }
    
    /**
     * 
     * 根据产品代码和费用名称，联动带出产品对应费用的付费计划
     * <功能详细描述>
     * @param param Map<String, Object>
     * @param page int
     * @param length int
     * @return
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @PostMapping("selectForPage")
    public List<FinPayPlanAdjustmentDto> selectForPage(@RequestBody Map<String, Object> param)
            throws Exception{
        return finPayPlanAdjustmentService.selectForPage(param);
    }
    
    /**
     * 
     * 获取到期日
     * <功能详细描述>
     * @param param Map<String, Object>
     * @return FinPayPlanAdjustmentDto
     * @see
     * @since 1.0
     */
    @PostMapping("/getMdate")
    public FinPayPlanAdjustmentDto getMdate(@RequestBody Map<String, Object> param){
        return finPayPlanAdjustmentService.getMdate(param);
    }
    
    /**
     * 
     * 工作流完成操作
     * <功能详细描述>
     * @param map 业务实体
     * @throws InvocationTargetException 
     * @throws IllegalAccessException 
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("/doComplete")
    public void doComplete(@RequestBody FinPayPlanAdjustmentDto dto) throws IllegalAccessException, InvocationTargetException {
        finPayPlanAdjustmentService.doComplete(dto);
    }
    
    /**
     * 
     * 获取所有的付费计划
     * <功能详细描述>
     * @param param Map<String, Object>
     * @return List<FinPayPlanAdjustmentDto>
     * @see
     * @since 1.0
     */
    @PostMapping("/selectForList")
    public List<FinPayPlanAdjustmentDto> selectForList(@RequestBody Map<String, Object> param){
        return finPayPlanAdjustmentService.selectForList(param);
    }
    
    /**
     * 
     * 产品规则调整产品代码下拉框 yzg 20191126
     * <功能详细描述>
     * @param param Map<String, Object>
     * @return List<SelectOptionDto>
     * @see
     * @since 1.0
     */
    @PostMapping("getSelectFinprodId/{page}/{length}")
    public Page<SelectOptionDto> getSelectFinprodId(@RequestBody Map<String, Object> param,@PathVariable("page") int page, @PathVariable("length") int length){
        return finPayPlanAdjustmentService.getSelectFinprodId(page,length,param);
    }
    
    /**
     * 根据金融产品代码查询费用列表信息
     * 
     * @param finprodId 金融产品代码
     * @return 费用列表信息
     */
    @PostMapping("queryFeeInfoByFinprodId")
    public List<FinPayRuleAdjustmentDto> queryFeeInfoByFinprodId(@RequestBody Map<String, Object> param) {
        return finPayRuleAdjustmentService.queryFeeInfoByFinprodId(param);
    }
    
    /**
     * 产品款型下拉框
     * @param param
     * @param page
     * @param length
     * @return
     */
    @PostMapping("getSelectProdStyle/{page}/{length}")
    public Page<SelectOptionDto> getSelectProdStyle(@RequestBody Map<String, Object> param,@PathVariable("page") int page, @PathVariable("length") int length) {
        return finPayRuleAdjustmentService.getSelectProdStyle(page,length,param);
    }
    
    /**
     * 费用规则调整提交
     * @param finPayRuleAdjustmentDto
     * @throws Exception 
     */
    @DTXTransaction
    @Transactional
    @PostMapping("insertFinProductFeeInfo")
    public void insertFinProductFeeInfo(@RequestBody FinPayRuleAdjustmentDto finPayRuleAdjustmentDto) throws Exception{
    	finPayRuleAdjustmentService.insertFinProductFeeInfo(finPayRuleAdjustmentDto);
    }
    
    @DTXTransaction
    @Transactional
    @PostMapping("deleteFinProductFeeInfo")
    public void deleteFinProductFeeInfo(@RequestBody FinPayRuleAdjustmentDto finPayRuleAdjustmentDto) {
    	finPayRuleAdjustmentService.deleteFinProdutFeeInfo(finPayRuleAdjustmentDto);
    }
    
    @GetMapping("selectFinProductByFinprodId")
    public FinProduct selectFinProductByFinprodId(String finprodId) {
    	return finPayRuleAdjustmentService.selectFinProductByFinprodId(finprodId);
    }
    
    /**
     * 根据金融产品代码查询分层产品子产品
     * @param finprodId
     * @return
     */
    @GetMapping("selectLayProductByFinproductId")
    public List<FinProduct> selectLayProductByFinproductId(String finprodId){
    	return finPayRuleAdjustmentService.selectLayProductByFinproductId(finprodId);
    }
}
