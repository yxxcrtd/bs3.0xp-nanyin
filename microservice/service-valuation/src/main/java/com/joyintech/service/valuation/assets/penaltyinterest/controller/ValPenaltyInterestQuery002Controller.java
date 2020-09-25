/*
 * 文件名：ValPenaltyInterestQuery002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：wangqidong
 * 修改时间：2018年10月22日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.service.valuation.assets.penaltyinterest.controller;

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
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.service.valuation.assets.posit.dto.DateTableDto;
import com.joyintech.service.valuation.assets.posit.dto.ValDespositAssetQuery002Dto;
import com.joyintech.service.valuation.assets.penaltyinterest.dto.ValPenaltyInterestQuery001Dto;
import com.joyintech.service.valuation.assets.penaltyinterest.service.ValPenaltyInterestQuery002Service;
import com.joyintech.tams.constant.DictCode;

/**
 * 查看回购类资产详细页面
 * 
 * @author wangqidong
 * @version 1.0 2018年10月22日
 * @see ValPenaltyInterestQuery002Controller
 * @since 1.0
 */
@RestController(value="serviceValPenaltyInterestQuery002Controller")
@RequestMapping("/ValPenaltyInterestQuery002")
public class ValPenaltyInterestQuery002Controller extends BaseController {

    /**
     * valPenaltyInterestQuery002Service
     */
    @Autowired
    private ValPenaltyInterestQuery002Service valPenaltyInterestQuery002Service;

   

    /**
     * 
     * 详情页表单数据的获取-抵押品信息
     * @return List<ValPenaltyInterestQuery001Dto>
     * @see
     * @since 1.0
     */
    @PostMapping("/getCalendarInfoList")
    public List<SelectOptionDto> getCalendarInfoList() {
    	  return valPenaltyInterestQuery002Service.getCalendarInfoList();
    }
    
    
    @PostMapping("/queryCollateralinfo")
    public List<ValPenaltyInterestQuery001Dto> queryCollateralinfo(@RequestBody Map<String, Object> param) {
    	  return valPenaltyInterestQuery002Service.queryCollateralinfo(param);
    }
    
    @PostMapping("/queryFenaltyInterestInfo")
    public List<ValPenaltyInterestQuery001Dto> queryFenaltyInterestInfo(@RequestBody Map<String, Object> param) {
    	  return valPenaltyInterestQuery002Service.queryFenaltyInterestInfo(param);
    }
    
    @PostMapping("/queryRepassetByfinprodId")
    public ValPenaltyInterestQuery001Dto queryRepassetByfinprodId(@RequestBody Map<String, Object> param){
//        return valAssetsFundQuery001Service.findPage(RequestUtil.getPageParam());
    	 return valPenaltyInterestQuery002Service.queryRepassetByfinprodId(param);
    
    }
}
