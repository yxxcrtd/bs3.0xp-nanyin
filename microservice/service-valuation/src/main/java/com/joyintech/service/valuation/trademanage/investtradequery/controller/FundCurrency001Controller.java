/*
 * 文件名：FundCurrency001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：Administrator
 * 修改时间：2018年7月28日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.service.valuation.trademanage.investtradequery.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.base.BaseController;
import com.joyintech.service.valuation.trademanage.investtradequery.dto.FundCurrencyDto;
import com.joyintech.service.valuation.trademanage.investtradequery.service.FundCurrency001Service;

/**
 * 基金类资产Controller
 * 
 * @author Administrator
 * @version 1.0 2018年7月28日
 * @see FundCurrency001Controller
 * @since 1.0
 */
@RestController(value="serviceFundCurrency001Controller")
@RequestMapping("/FundCurrency001")
public class FundCurrency001Controller extends BaseController {

    @Autowired
    private FundCurrency001Service fundCurrency001Service;

//    /**
//     * 加载页面 {@inheritDoc}
//     */
//    @Override
//    public String init(Model model) {
//        String ps=this.getParam("ps");
//        String tradeId=this.getParam("tradeId");
//        String busiType=this.getParam("busiType");
//        model.addAttribute("ps", ps);
//        model.addAttribute("tradeId", tradeId);
//        model.addAttribute("busiType", busiType);
//        this.setSelectOptionToModel(model, DictCode.币种);
//        this.setSelectOptionToModel(model, DictCode.交易对手类型);
//        this.setSelectOptionToModel(model, DictCode.交易方向);
//        String prodId = this.getParam("prodId");
//        Map<String, Object> params = new HashMap<>();
//        params.put("tradeId", tradeId);
//        params.put("prodId", prodId);
//        FundCurrencyDto entity = fundCurrency001Service.queryFundCurrencyList(params);
//        model.addAttribute("entity", entity);
//        return super.init(model);
//    }
    
    @PostMapping("/queryFundCurrencyList")
    public FundCurrencyDto queryFundCurrencyList(@RequestBody Map<String, Object> param) throws Exception {
        return fundCurrency001Service.queryFundCurrencyList(param);
    }
}
