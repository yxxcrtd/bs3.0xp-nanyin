/*
 * 文件名：FinProductPayPlanController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2018年9月29日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.service.product.common.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.tams.common.base.entity.FinCashPlan;
import com.joyintech.service.product.common.dto.BussFinProductPayPlanDto;
import com.joyintech.service.product.common.service.FinProductPayPlanService;

/**
 * 付息计划业务组件
 * 
 * @author zhangliang
 * @version 1.0 2018年10月26日
 * @see FinProductPayPlanController
 * @since 1.0
 */
@RestController("backFinProductPayPlanController")
@RequestMapping("/FinProductPayPlan")
public class FinProductPayPlanController {

    /**
     * {@linkplain}
     */
    @Autowired
    private FinProductPayPlanService finProductPayPlanService;
    
    /**
     * 根据金融产品代码查询付息计划信息
     * @param finprodId 金融产品代码
     * @return 付息计划信息
     * @throws Exception 
     */
    @GetMapping("queryPayPlanInfoByFinprodId")
    public BussFinProductPayPlanDto queryPayPlanInfoByFinprodId(String finprodId, String effDate) throws Exception {
        return finProductPayPlanService.queryPayPlanInfoByFinprodId(finprodId, effDate);
    }

    /**
     * 根据金融产品代码查询付息计划信息
     * @param param 金融产品代码
     * @return 付息计划信息
     */
    @PostMapping("queryPayPlanListByFinprodId")
    public List<FinCashPlan> queryPayPlanListByFinprodId(@RequestBody Map<String, Object> param) {
        return finProductPayPlanService.queryPayPlanListByFinprodId(param);
    }
}
