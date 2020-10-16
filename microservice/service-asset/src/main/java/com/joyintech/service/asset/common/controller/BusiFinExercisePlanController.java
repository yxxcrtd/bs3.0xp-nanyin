/*
 * 文件名：BusiFinExercisePlanController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年10月12日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.asset.common.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.tams.common.base.entity.FinExercisePlan;
import com.joyintech.service.asset.common.service.BusiFinExercisePlanService;

/**
 * 行权计划
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年10月12日
 * @see BusiFinExercisePlanController
 * @since 1.0
 */
@RestController("backBusiFinExercisePlanController")
@RequestMapping("/BusiFinExercisePlan")
public class BusiFinExercisePlanController {
    
    @Autowired
    private BusiFinExercisePlanService busiFinExercisePlanService;
    /**
     * 初始化行权计划
     * <功能详细描述>
     * @param finprodId
     * @param cashType
     * @return
     * @see
     * @since 1.0
     */
    @GetMapping("getfinExercisePlanByProductId")
    public List<FinExercisePlan> getFinCashPlanByProductId(String finprodId,String psT03,String cashType2,String execType01,String execType02){
        return busiFinExercisePlanService.getfinExercisePlanByProductId(finprodId, psT03, cashType2, execType01, execType02);
    }
    
}
