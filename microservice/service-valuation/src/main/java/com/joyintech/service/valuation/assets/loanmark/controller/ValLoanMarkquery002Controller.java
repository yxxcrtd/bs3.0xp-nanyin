/*
 * 文件名：ValVoucherQuery001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月7日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.service.valuation.assets.loanmark.controller;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.service.valuation.assets.fund.dto.ValAssetsFundQuery001Dto;
import com.joyintech.service.valuation.assets.loanmark.dto.ValLoanMarkquery001Dto;
import com.joyintech.service.valuation.assets.loanmark.service.ValLoanMarkquery002Service;
import com.joyintech.tams.constant.DictCode;

/**
 * 拆借标的查询
 * @author huwei
 * @version 2.0 2018年10月19日
 * @see ValLoanMarkquery002Controller
 * @since 2.0
 */
@RestController(value="serviceValLoanMarkquery002Controller")
@RequestMapping("/ValLoanMarkquery002")
public class ValLoanMarkquery002Controller extends BaseController {
	
	/**
     * ValLoanMarkquery002Service
     */
    @Autowired
    private ValLoanMarkquery002Service valLoanMarkquery002Service;

    /**
     * 重写init方法，加载页面 
     */
//    @Override
//    public String init(Model model) {
//    	this.setSelectOptionToModel(model, DictCode.币种);
//        this.setSelectOptionToModel(model, DictCode.计息基础);
//        this.setSelectOptionToModel(model, DictCode.本金收益特征);
//    	this.setSelectOptionToModel(model, DictCode.拆借核算分类);
//        this.setSelectOptionToModel(model, DictCode.金融产品类型);
//        this.setSelectOptionToModel(model, DictCode.期限品种_内部拆借);
//        this.setSelectOptionToModel(model, DictCode.交易日历);
//        String finprodId = this.getParam("finprodId");
//        String finprodType = this.getParam("finprodType");
//        ValLoanMarkquery001Dto entity = valLoanMarkquery002Service.queryFundAccountInfo(finprodId,finprodType);
//        model.addAttribute("entity", entity);
//        return super.init(model);
//    }
    
    @PostMapping("/queryFundAccountInfo")
    public ValLoanMarkquery001Dto queryFundAccountInfo(@RequestBody Map<String, Object> param){
//        return valAssetsFundQuery001Service.findPage(RequestUtil.getPageParam());
    	 return valLoanMarkquery002Service.queryFundAccountInfo(param);
    }
    
    
  
}
