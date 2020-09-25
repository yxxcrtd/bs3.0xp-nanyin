/*
 * 文件名：ValPlannedassetquery002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月7日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.service.valuation.assets.plan.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.service.valuation.assets.fund.dto.ValAssetsFundQuery001Dto;
import com.joyintech.service.valuation.assets.plan.dto.ValPlannedassetquery001Dto;
import com.joyintech.service.valuation.assets.plan.service.ValPlannedassetquery002Service;
import com.joyintech.tams.constant.DictCode;

/**
 * 模板示例<br>
 * 计划类资产查询
 * 
 * @author zhangzhao
 * @version 2.0 2018年7月19日
 * @see ValPlannedassetquery002Controller
 * @since 2.0
 */
@RestController(value="serviceValPlannedassetquery002Controller")
@RequestMapping("/ValPlannedassetquery002")
public class ValPlannedassetquery002Controller extends BaseController {
    
    /**
     * ValPlannedassetquery002Service
     */
    @Autowired
    private ValPlannedassetquery002Service valPlannedassetquery002Service;

    @PostMapping("/querybonus")
    public List<ValPlannedassetquery001Dto> querybonus(@RequestBody Map<String, Object> param){
    	return valPlannedassetquery002Service.querybonus(param);
    }
    
    @PostMapping("/queryintpay")
    public List<ValPlannedassetquery001Dto> queryintpay(@RequestBody Map<String, Object> param){
    	return valPlannedassetquery002Service.queryintpay(param);
    }
    
    @PostMapping("/queryintrate")
    public List<ValPlannedassetquery001Dto> queryintrate(@RequestBody Map<String, Object> param){
    	return valPlannedassetquery002Service.queryintrate(param);
    }
    
    @PostMapping("/queryrepay")
    public List<ValPlannedassetquery001Dto> queryrepay(@RequestBody Map<String, Object> param){
    	return valPlannedassetquery002Service.queryrepay(param);
    }
    
    
    @PostMapping("queryBondasetByfinprodId")
    public ValPlannedassetquery001Dto queryBondasetByfinprodId(@RequestBody Map<String, Object> param){
    	return valPlannedassetquery002Service.queryBondasetByfinprodId(param);
    }
    
    @PostMapping("queryIntInfo")
    public ValPlannedassetquery001Dto queryIntInfo(@RequestBody Map<String, Object> param){
    	return valPlannedassetquery002Service.queryIntInfo(param);
    }

}
