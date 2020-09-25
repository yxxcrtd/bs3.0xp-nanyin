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
import com.joyintech.service.valuation.trademanage.investtradequery.dto.LendTradeDto;
import com.joyintech.service.valuation.trademanage.investtradequery.service.LendTradeQuery001Service;

/**
 * 查看拆借交易
 * @author zhangwei
 * @version 1.0 2018年7月27日
 * @see LendTradeQuery001Controller
 * @since 1.0
 */
@RestController(value="serviceLendTradeQuery001Controller")
@RequestMapping("/LendTradeQuery001")
public class LendTradeQuery001Controller extends BaseController{

    @Autowired
    private LendTradeQuery001Service lendTradeQuery001Service;
    
    
    
//    /**
//     * 查看拆借交易页面初始化
//     * {@inheritDoc}
//     */
//    @Override
//    public String init(Model model) {
//
//    	super.setSelectOptionToModel(model, DictCode.计息基础);
//    	super.setSelectOptionToModel(model, DictCode.本金收益特征);
//    	this.setSelectOptionToModel(model, DictCode.期限品种_内部拆借);
//    	
//        String tradeId = RequestUtil.getParam("tradeId");  
//        String prodId = this.getParam("prodId");
//        Map<String, Object> params = new HashMap<>();
//        params.put("tradeId", tradeId);
//        params.put("prodId", prodId);
//        LendTradeDto lendTradeQueryDto = lendTradeQuery001Service.findByTradeId(params);   
//        model.addAttribute("entity", lendTradeQueryDto);
//        return super.init(model);
//    }
    

    @PostMapping("/findByTradeId")
    public LendTradeDto findByTradeId(@RequestBody Map<String, Object> param) throws Exception {
        return lendTradeQuery001Service.findByTradeId(param);
    }
    
    
}
