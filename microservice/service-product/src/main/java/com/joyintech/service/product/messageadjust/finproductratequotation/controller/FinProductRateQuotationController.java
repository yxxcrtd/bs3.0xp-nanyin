/*
 * 文件名：FinProductRateQuotationController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwenbin
 * 修改时间：2018年10月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.product.messageadjust.finproductratequotation.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.annotation.DTXTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.product.messageadjust.finproductratequotation.dto.FinProductRateQuotationDto;
import com.joyintech.service.product.messageadjust.finproductratequotation.service.FinProductRateQuotationService;

/**
 * 信息调整管理_产品费率报价管理Controller
 * 〈功能详细描述〉
 * @author huwenbin
 * @version 1.0 2018年10月11日
 * @see FinProductRateQuotationController
 * @since 1.0
 */
@RestController("backFinProductRateQuotationController_dtx")
@RequestMapping("/FinProductRateQuotation")
public class FinProductRateQuotationController {
    
    @Autowired
    private FinProductRateQuotationService finProductRateQuotationService;
    
    /**
     * 产品代码联想框 
     * @param param
     * @return
     */
    @PostMapping("getFinprodId")
    public List<SelectOptionDto> getFinprodId(@RequestBody Map<String, Object> param) {
        return finProductRateQuotationService.getFinprodId(param);
    }
    /**
     * 
     * 产品费率报价管理-查询
     * <功能详细描述>
     * @param param Map<String, Object>
     * @param page int
     * @param length int
     * @return Page<FinProductRateQuotationDto>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
     
    @PostMapping("selectForPage/{page}/{length}")
    public Page<FinProductRateQuotationDto> selectForPage(@RequestBody Map<String, Object> param, @PathVariable("page") int page, @PathVariable("length") int length)
            throws Exception{
        return finProductRateQuotationService.selectForPage(page, length, param);
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
    public void doComplete(@RequestBody FinProductRateQuotationDto dto) throws IllegalAccessException, InvocationTargetException {
        finProductRateQuotationService.doComplete(dto);
    }
    
    /**
     * 
     * 新建页面产品代码下拉框
     * <功能详细描述>
     * @param param Map<String, Object>
     * @return List<SelectOptionDto>
     * @see
     * @since 1.0
     */
    @PostMapping("getSelectFinprodId")
    public List<SelectOptionDto> getSelectFinprodId(@RequestBody Map<String, Object> param){
        return finProductRateQuotationService.getSelectFinprodId(param);
    }
    /**
     * 
     *  新建页面产品代码联动适用款型下拉框
     * <功能详细描述>
     * @param param Map<String, Object>
     * @return List<SelectOptionDto>
     * @see
     * @since 1.0
     */
    @PostMapping("getSelectProdStyleByFinprodId")
    public List<SelectOptionDto> getSelectProdStyleByFinprodId(@RequestBody Map<String, Object> param){
        return finProductRateQuotationService.getSelectProdStyleByFinprodId(param);
    }
    
    /**
     * 
     * 新建页面画面联动
     * <功能详细描述>
     * @param param Map<String, Object>
     * @param page int
     * @param length int
     * @return Page<FinProductRateQuotationDto>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @PostMapping("selectOther/{page}/{length}")
    public Page<FinProductRateQuotationDto> selectOther(@RequestBody Map<String, Object> param, @PathVariable("page") int page, @PathVariable("length") int length)
            throws Exception{
        return finProductRateQuotationService.selectOther(page, length, param);
    }
    
    /**
     * 
     * 双击查看、修改取得数据源
     * <功能详细描述>
     * @param param Map<String, Object>
     * @return FinProductRateQuotationDto
     * @see
     * @since 1.0
     */
    @PostMapping("selectDetail")
    public FinProductRateQuotationDto selectDetail(@RequestBody Map<String, Object> param){
        return finProductRateQuotationService.selectDetail(param);
    }
    
    /**
     * 
     * 新建check
     * <功能详细描述>
     * @param param Map<String, Object>
     * @return List<FinProductRateQuotationDto>
     * @see
     * @since 1.0
     */
    @PostMapping("selectAlreadyHava")
    public List<FinProductRateQuotationDto> selectAlreadyHava(@RequestBody Map<String, Object> param){
        return finProductRateQuotationService.selectAlreadyHava(param);
    }
    
    /**
     * 
     * 根据金融产品代码拿到起息日、到期日
     * <功能详细描述>
     * @param param Map<String, Object>
     * @return FinProductRateQuotationDto
     * @see
     * @since 1.0
     */
    @PostMapping("getVdateAndMdate")
    public FinProductRateQuotationDto getVdateAndMdate(@RequestBody Map<String, Object> param){
        return finProductRateQuotationService.getVdateAndMdate(param);
    }
}
