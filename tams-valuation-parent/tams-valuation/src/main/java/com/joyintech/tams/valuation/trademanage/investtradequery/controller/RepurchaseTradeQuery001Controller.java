/*
 * 文件名：BondTradeQuery001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：zhangwei
 * 修改时间：2018年7月27日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.trademanage.investtradequery.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.valuation.trademanage.investtradequery.dto.RepurchaseTradeDto;
import com.joyintech.tams.valuation.trademanage.investtradequery.service.RepurchaseTradeQuery001Service;
import com.joyintech.tams.constant.DictCode;

/**
 * 查看回购交易
 * @author zhangwei
 * @version 1.0 2018年7月27日
 * @see RepurchaseTradeQuery001Controller2
 * @since 1.0
 */
@Controller
@PageId("RepurchaseTradeQuery001")
@RequestMapping("/valuation/trademanage/investtradequery/repurchasetrade/RepurchaseTradeQuery001")
public class RepurchaseTradeQuery001Controller extends BaseController{

    @Autowired
    private RepurchaseTradeQuery001Service repurchaseTradeQuery001Service;
    
    
    
    /**
     * 查看回购交易页面初始化
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {

        this.setSelectOptionToModel(model, DictCode.币种);
        this.setSelectOptionToModel(model, DictCode.交易方向);
        this.setSelectOptionToModel(model, DictCode.期限品种_回购);
        
        String tradeId = RequestUtil.getParam("tradeId");  
        String prodId = this.getParam("prodId");
        Map<String, Object> params = new HashMap<>();
        params.put("tradeId", tradeId);
        params.put("prodId", prodId);
        RepurchaseTradeDto repurchaseTradeQueryDto = repurchaseTradeQuery001Service.findByTradeId(params);   
        model.addAttribute("entity", repurchaseTradeQueryDto);
        return super.init(model);
    }
    

    
    
    
}
