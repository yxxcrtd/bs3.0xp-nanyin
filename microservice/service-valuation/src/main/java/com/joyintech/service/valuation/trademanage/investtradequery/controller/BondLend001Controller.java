/*
 * 文件名：BondLend001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：niuqianyu
 * 修改时间：2018年12月11日
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
import com.joyintech.service.valuation.trademanage.investtradequery.dto.BondLendDto;
import com.joyintech.service.valuation.trademanage.investtradequery.service.BondLend001Service;

/**
 * 债券借贷
 * 
 * @author niuqianyu
 * @version 1.0 2018年12月11日
 * @see BondLend001Controller
 * @since 1.0
 */
@RestController(value="serviceBondLend001Controller")
@RequestMapping("/ValBondLend001")
public class BondLend001Controller extends BaseController {

    @Autowired
    private BondLend001Service bondLend001Service;

//    /**
//     * 加载页面 {@inheritDoc}
//     */
//    @Override
//    public String init(Model model) {
//        String ps = this.getParam("ps");
//        String tradeId = this.getParam("tradeId");
//        model.addAttribute("ps", ps);
//        model.addAttribute("tradeId", tradeId);
//        this.setSelectOptionToModel(model, DictCode.币种);
//        this.setSelectOptionToModel(model, DictCode.交易方向);
//        this.setSelectOptionToModel(model, DictCode.交易对手类型);
//        this.setSelectOptionToModel(model, DictCode.期限品种_回购);
//        String prodId = this.getParam("prodId");
//        Map<String, Object> params = new HashMap<>();
//        params.put("tradeId", tradeId);
//        params.put("prodId", prodId);
//        BondLendDto entity = bondLend001Service.findByTradeId(params);
//        model.addAttribute("entity", entity);
//        return super.init(model);
//    }
        
    @PostMapping("/findByTradeId")
    public BondLendDto findByTradeId(@RequestBody Map<String, Object> param) throws Exception {
        return bondLend001Service.findByTradeId(param);
    }
}
