/*
 * 文件名：InvestTradeQuery001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：yangjian
 * 修改时间：2018年7月24日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.service.valuation.trademanage.investtradequery.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.service.valuation.trademanage.investtradequery.dto.InvestTradeDto;
import com.joyintech.service.valuation.trademanage.investtradequery.service.CurrentDeposit001Service;
import com.joyintech.service.valuation.trademanage.investtradequery.service.InvestTradeQuery001Service;
import com.joyintech.tams.constant.DictCode;

/**
 * 投资交易查询-controller
 * 
 * @author yangjian
 * @version 1.0 2018年7月24日
 * @see InvestTradeQuery001Controller
 * @since 1.0
 */
@RestController(value="serviceInvestTradeQuery001Controller")
@RequestMapping("/InvestTradeQuery001")
public class InvestTradeQuery001Controller extends BaseController {

    @Autowired
    private InvestTradeQuery001Service investTradeQuery001Service;

    /**
     * 活期存款交易currentDeposit001Service
     */
    @Autowired
    private CurrentDeposit001Service currentDeposit001Service;

    /**
     * 分页数据查询
     * 
     * @return page
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @PostMapping("/findPage/{page}/{length}")
    public Page<InvestTradeDto> findPage(@RequestBody Map<String, Object> param, @PathVariable("page") int page,
                                         @PathVariable("length") int length)
        throws Exception {
        return investTradeQuery001Service.findPage(page, length, param);
    }

    /**
     * 不分页数据查询
     * 
     * @return page
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @SuppressWarnings("unchecked")
    @PostMapping("/findList")
    public List<InvestTradeDto> findList(@RequestBody Map<String, Object> param)
        throws Exception {
        return investTradeQuery001Service.findList(param);
    }

    @PostMapping("/queryFinProductDealBytradeId")
    public InvestTradeDto queryFinProductDealBytradeId(@RequestBody Map<String, Object> param) {
        return investTradeQuery001Service.queryFinProductDealBytradeId(param);
    }

    /**
     * 活期存款收息交易（点击资金账户名）查看
     * 
     * @param tradeId
     * @param model
     * @return
     * @see
     * @since 1.0
     */
    @RequestMapping("selectByAccountName")
    public String openBatchGroupDetail(String tradeId, Model model) {
        this.setSelectOptionToModel(model, DictCode.交易方向);
        this.setSelectOptionToModel(model, DictCode.币种);
        this.setSelectOptionToModel(model, DictCode.交易对手类型);
        Map<String, Object> params = new HashMap<>();
        params.put("tradeId", tradeId);
        InvestTradeDto entity = currentDeposit001Service.selectByTradeId(params);
        model.addAttribute("entity", entity);
        return "/valuation/trademanage/investtradequery/currentdeposit/CurrentDeposit002";
    }

}
