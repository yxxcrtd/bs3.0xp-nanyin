/*
 * 文件名：BondLend001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：niuqianyu
 * 修改时间：2018年12月11日
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
import com.joyintech.tams.valuation.trademanage.investtradequery.dto.BondLendDto;
import com.joyintech.tams.valuation.trademanage.investtradequery.service.BondLend001Service;
import com.joyintech.tams.constant.DictCode;

/**
 * 债券借贷
 * 
 * @author niuqianyu
 * @version 1.0 2018年12月11日
 * @see BondLend001Controller
 * @since 1.0
 */
@Controller
@PageId("ValBondLend001")
@RequestMapping("/valuation/trademanage/investtradequery/bondlend/ValBondLend001")
public class BondLend001Controller extends BaseController {

    @Autowired
    private BondLend001Service bondLend001Service;

    /**
     * 加载页面 {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        String ps = this.getParam("ps");
        String tradeId = this.getParam("tradeId");
        model.addAttribute("ps", ps);
        model.addAttribute("tradeId", tradeId);
        this.setSelectOptionToModel(model, DictCode.币种);
        this.setSelectOptionToModel(model, DictCode.交易方向);
        this.setSelectOptionToModel(model, DictCode.交易对手类型);
        this.setSelectOptionToModel(model, DictCode.是否);
        this.setSelectOptionToModel(model, DictCode.期限品种_回购);
        this.setSelectOptionToModel(model, DictCode.登记托管机构);
        String prodId = this.getParam("prodId");
        Map<String, Object> params = new HashMap<>();
        params.put("tradeId", tradeId);
        params.put("prodId", prodId);
        BondLendDto entity = bondLend001Service.findByTradeId(params);
        model.addAttribute("entity", entity);
        return super.init(model);
    }

}
