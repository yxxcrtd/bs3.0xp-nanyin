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
import com.joyintech.tams.valuation.trademanage.investtradequery.dto.OverduePenaltyDto;
import com.joyintech.tams.valuation.trademanage.investtradequery.service.ValOverduePenalty001Service;
import com.joyintech.tams.constant.DictCode;

/**
 * 查看逾期交易
 * @author tanxuan
 * @version 1.0 2019年12月23日
 * @see ValOverduePenalty001Controller
 * @since 1.0
 */
@Controller
@PageId("ValOverduePenalty001")
@RequestMapping("/valuation/trademanage/investtradequery/overduepenalty/ValOverduePenalty001")
public class ValOverduePenalty001Controller extends BaseController{

    @Autowired
    private ValOverduePenalty001Service valOverduePenalty001Service;

    /**
     * 查看逾期交易页面初始化
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
    	
        String tradeId = RequestUtil.getParam("tradeId");  
        String prodId = this.getParam("prodId");
        Map<String, Object> params = new HashMap<>();
        params.put("tradeId", tradeId);
        params.put("prodId", prodId);
        OverduePenaltyDto dto = valOverduePenalty001Service.findByTradeId(params);   
        model.addAttribute("entity", dto);
        
    	super.setSelectOptionToModel(model, DictCode.交易方向);
    	super.setSelectOptionToModel(model, DictCode.币种);
    	super.setSelectOptionToModel(model, DictCode.交易流通场所);
    	super.setSelectOptionToModel(model, DictCode.投资目的);
    	super.setSelectOptionToModel(model, DictCode.交易状态);
    	
        return super.init(model);
    }

}
