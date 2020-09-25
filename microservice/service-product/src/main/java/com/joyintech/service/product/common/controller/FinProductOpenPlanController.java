/*
 * 文件名：FinProductOpenPlanController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2018年9月29日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.service.product.common.controller;

import java.util.List;

import com.joyintech.framework.annotation.DTXTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.tams.common.base.entity.FinOpenPlan;
import com.joyintech.service.product.common.dto.BussFinProductOpenPlanDto;
import com.joyintech.service.product.common.dto.FinProductOpenPlanDto;
import com.joyintech.service.product.common.service.FinProductOpenPlanService;

/**
 * 开放计划业务组件
 * 
 * @author zhangliang
 * @version 1.0 2018年9月29日
 * @see FinProductOpenPlanController
 * @since 1.0
 */
@RestController("backFinProductOpenPlanController_dtx")
@RequestMapping("/FinProductOpenPlan")
public class FinProductOpenPlanController {

    /**
     * {@linkplain}
     */
    @Autowired
    private FinProductOpenPlanService finProductOpenPlanService;

    /**
     * 插入开放计划信息
     * 
     * @param entity 开放计划信息对象
     * @return 影响行数
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("insertFinProdPayPlan")
    public void insertFinProdPayPlan(@RequestBody FinProductOpenPlanDto productOpenPlanDto) {
        finProductOpenPlanService.insertFinProdPayPlan(productOpenPlanDto);
    }

    /**
     * 更新开放计划信息
     * 
     * @param entity 开放计划信息对象
     * @return 影响行数
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("updateFinProdPayPlan")
    public void updateFinProdPayPlan(@RequestBody FinProductOpenPlanDto productOpenPlanDto) {
        finProductOpenPlanService.updateFinProdPayPlan(productOpenPlanDto);
    }

    /**
     * 根据现金流代码删除开放计划
     * 
     * @param cashId 开放计划信息对象
     * @return 影响行数
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @GetMapping("deleteFinProdPayPlan")
    public void deleteFinProdPayPlan(String cashId) {
        finProductOpenPlanService.deleteFinProdPayPlan(cashId);
    }

    /**
     * 根据现金流代码查询开放计划
     * 
     * @param cashId 开放计划信息对象
     * @return 开放计划
     * @see
     * @since 1.0
     */
    @GetMapping("selectFinProdPayPlan")
    public FinProductOpenPlanDto selectFinProdPayPlan(String cashId) {
        return finProductOpenPlanService.selectFinProdPayPlan(cashId);
    }

    /**
     * 根据金融产品代码查询开放计划信息
     * @param finprodId 金融产品代码
     * @return 开放计划信息
     * @throws Exception 
     */
    @GetMapping("selectOpenPlanInfoByFinprodId")
    public List<BussFinProductOpenPlanDto> selectOpenPlanInfoByFinprodId(String finprodId) throws Exception {
        return finProductOpenPlanService.selectOpenPlanInfoByFinprodId(finprodId);
    }

    /**
     * 根据金融产品代码查询开放计划信息
     * @param finprodId 金融产品代码
     * @return 开放计划信息
     */
    @GetMapping("queryOpenPlanListByFinprodId")
    public List<FinOpenPlan> queryOpenPlanListByFinprodId(String finprodId) {
        return finProductOpenPlanService.queryOpenPlanListByFinprodId(finprodId);
    }
    
}
