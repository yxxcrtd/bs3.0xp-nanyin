/*
 * 文件名：FinPayPlanController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年11月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.asset.common.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.service.asset.common.dto.FinPayPlanDto;
import com.joyintech.service.asset.common.service.FinPayPlanService;

/**
 * 支付计划维护
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年11月23日
 * @see FinPayPlanController
 * @since 1.0
 */
@RestController("FinPayPlanController_dtx")
@RequestMapping("/FinPayPlan")
public class FinPayPlanController {

    @Autowired
    private FinPayPlanService finPayPlanService;
    
    /**
     * 插入
     * <功能详细描述>
     * @param dto
     * @throws Exception
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("insert")
    public void insert(@RequestBody FinPayPlanDto dto) throws Exception {
        finPayPlanService.insert(dto);
    }
    
    /**
     * 获取基本信息--修改/详细
     * @param secId
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @PostMapping("getBasicInfo")
    public FinPayPlanDto getBasicInfo(@RequestBody Map<String,Object> paramMap) throws Exception {
        return finPayPlanService.getBasicInfo(paramMap);
    }
    
    /**
     * 获取基本信息--修改/详细
     * @param secId
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @PostMapping("isNotIng")
    public boolean isNotIng(@RequestBody Map<String,Object> paramMap) throws Exception {
        return finPayPlanService.isNotIng(paramMap);
    }
}

