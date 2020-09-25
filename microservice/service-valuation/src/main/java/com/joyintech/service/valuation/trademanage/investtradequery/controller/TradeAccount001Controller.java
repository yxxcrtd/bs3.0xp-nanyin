/*
 * 文件名：TradeAccountController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：Administrator
 * 修改时间：2018年8月13日
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
import com.joyintech.service.valuation.trademanage.investtradequery.dto.TradeAccountDto;
import com.joyintech.service.valuation.trademanage.investtradequery.service.TradeAccount001Service;

/**
 * 交易账户收划款查询
 * 
 * @author Administrator
 * @version 1.0 2018年8月13日
 * @see TradeAccount001Controller
 * @since 1.0
 */
@RestController(value="serviceTradeAccount001Controller")
@RequestMapping("/TradeAccount001")
public class TradeAccount001Controller extends BaseController {

    @Autowired
    private TradeAccount001Service tradeAccount001Service;
//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    public String init(Model model) {
//        String tradeId=this.getParam("tradeId");
//        String prodId = this.getParam("prodId");
//        this.setSelectOptionToModel(model, DictCode.币种);
//        this.setSelectOptionToModel(model, DictCode.指令类型);
//        TradeAccountDto entity = tradeAccount001Service.queryTradeAccount(tradeId,prodId);
//        model.addAttribute("entity", entity);
//        return super.init(model);
//    }

    @PostMapping("/queryTradeAccount")
    public TradeAccountDto queryTradeAccount(@RequestBody Map<String, Object> param) throws Exception {
        String tradeId = (String) param.get("tradeId");
        String prodId = (String) param.get("prodId");
        return tradeAccount001Service.queryTradeAccount(tradeId, prodId);
    }
}
