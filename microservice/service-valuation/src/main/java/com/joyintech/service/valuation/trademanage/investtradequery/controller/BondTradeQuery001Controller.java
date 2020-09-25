/*
 * 文件名：BondTradeQuery001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：zhangwei
 * 修改时间：2018年7月27日
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
import com.joyintech.service.valuation.trademanage.investtradequery.dto.BondTradeDto;
import com.joyintech.service.valuation.trademanage.investtradequery.service.BondTradeQuery001Service;

/**
 * 查看债券交易
 * @author zhangwei
 * @version 1.0 2018年7月27日
 * @see BondTradeQuery001Controller
 * @since 1.0
 */
@RestController(value="serviceBondTradeQuery001Controller")
@RequestMapping("/BondTradeQuery001")
public class BondTradeQuery001Controller extends BaseController{

    @Autowired
    private BondTradeQuery001Service bondTradeQuery001Service;
    
//    /**
//     * 查看债券交易页面初始化
//     * {@inheritDoc}
//     */
//    @Override
//    public String init(Model model) {
//        this.setSelectOptionToModel(model, DictCode.交易方向);
//        this.setSelectOptionToModel(model, DictCode.交易流通场所);
//        this.setSelectOptionToModel(model, DictCode.币种);
//        this.setSelectOptionToModel(model, DictCode.投资目的);
//        this.setSelectOptionToModel(model, DictCode.交易对手类型);
//   
//        String tradeId = RequestUtil.getParam("tradeId");
//        String prodId = this.getParam("prodId");
//        Map<String, Object> params = new HashMap<>();
//        params.put("tradeId", tradeId);
//        params.put("prodId", prodId);
//        BondTradeDto bondTradeDto = bondTradeQuery001Service.findByTradeId(params);
//        
//        model.addAttribute("entity", bondTradeDto);
//        model.addAttribute("ps", RequestUtil.getParam("ps"));
//        return super.init(model);
//    }
    
    @PostMapping("/findByTradeId")
    public BondTradeDto findByTradeId(@RequestBody Map<String, Object> param) throws Exception {
        return bondTradeQuery001Service.findByTradeId(param);
    }
    
    
    
}
