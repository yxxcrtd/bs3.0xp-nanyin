/*
 * 文件名：CurrentDeposit001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：Administrator
 * 修改时间：2018年8月2日
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.valuation.trademanage.investtradequery.dto.CurrentDepositDto;
import com.joyintech.tams.valuation.trademanage.investtradequery.dto.InvestTradeDto;
import com.joyintech.tams.valuation.trademanage.investtradequery.service.CurrentDeposit001Service;

/**
 * 双击查看活期存款交易
 * @author Administrator
 * @version 1.0 2018年8月2日
 * @see CurrentDeposit001Controller
 * @since 1.0
 */
@Controller
@PageId("CurrentDeposit001")
@RequestMapping("/valuation/trademanage/investtradequery/currentdeposit/CurrentDeposit001")
public class CurrentDeposit001Controller extends BaseController{

    @Autowired
    private CurrentDeposit001Service currentDeposit001Service;
    
    /**
     * 查看活期存款交易页面初始化
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.交易方向);
        this.setSelectOptionToModel(model, DictCode.币种);
        this.setSelectOptionToModel(model, DictCode.交易对手类型);
        String tradeId=this.getParam("tradeId");
        String prodId = this.getParam("prodId");
        Map<String, Object> params = new HashMap<>();
        params.put("tradeId", tradeId);
        params.put("prodId", prodId);
        InvestTradeDto entity = currentDeposit001Service.selectByTradeId(params);
        model.addAttribute("entity", entity);
        return super.init(model);
    }
    
    /**
     * 查看计提明细信息
     * 
     * @return page
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("findPage")
    @ResponseBody
    public Page<CurrentDepositDto> findPage()  throws Exception {
        String tradeId=this.getParam("tradeId");
        return currentDeposit001Service.selectByTradeId3(tradeId);
    }
    
}
