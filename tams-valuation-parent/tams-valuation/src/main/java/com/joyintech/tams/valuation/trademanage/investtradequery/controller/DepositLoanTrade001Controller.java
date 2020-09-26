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
import com.joyintech.tams.valuation.trademanage.investtradequery.dto.DepositLoanTradeDto;
import com.joyintech.tams.valuation.trademanage.investtradequery.service.DepositLoanTrade001Service;
import com.joyintech.tams.constant.DictCode;

/**
 * 查看存借款交易-controller
 * 
 * @author zhangzhao
 * @version 1.0 2018年7月24日
 * @see DepositLoanTrade001Controller
 * @since 1.0
 */
@Controller
@PageId("DepositLoanTrade001")
@RequestMapping("/valuation/trademanage/investtradequery/posittrade/DepositLoanTrade001")
public class DepositLoanTrade001Controller extends BaseController{
	
@Autowired
    private DepositLoanTrade001Service depositLoanTrade001Service;
	
	/**
     * 加载页面 {@inheritDoc}
     */
	@Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.交易方向);
        this.setSelectOptionToModel(model, DictCode.币种);
        this.setSelectOptionToModel(model, DictCode.交易对手类型);
        
        String tradeId = RequestUtil.getParam("tradeId");
        String prodId = this.getParam("prodId");
        Map<String, Object> params = new HashMap<>();
        params.put("tradeId", tradeId);
        params.put("prodId", prodId);
        DepositLoanTradeDto depositLoanTradeDto = depositLoanTrade001Service.findByTradeId(params);
        model.addAttribute("entity", depositLoanTradeDto);
        return super.init(model);
    }
	
}
