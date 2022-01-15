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

import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.valuation.trademanage.bond.dto.TrdBondTrade001Dto;
import com.joyintech.tams.valuation.trademanage.bond.service.TrdBondTrade001Service;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.valuation.trademanage.investtradequery.dto.BondTradeDto;
import com.joyintech.tams.valuation.trademanage.investtradequery.service.BondTradeQuery001Service;

/**
 * 查看债券交易
 * @author zhangwei
 * @version 1.0 2018年7月27日
 * @see BondTradeQuery001Controller
 * @since 1.0
 */
@Controller
@PageId("BondTradeQuery001")
@RequestMapping("/valuation/trademanage/investtradequery/bandtrade/BondTradeQuery001")
public class BondTradeQuery001Controller extends BaseController{

    @Autowired
    private BondTradeQuery001Service bondTradeQuery001Service;

    @Autowired
    private TrdBondTrade001Service trdBondTrade001Service;
    
    /**
     * 查看债券交易页面初始化
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.交易方向);
        this.setSelectOptionToModel(model, DictCode.交易流通场所);
        this.setSelectOptionToModel(model, DictCode.币种);
        this.setSelectOptionToModel(model, DictCode.投资目的);
        this.setSelectOptionToModel(model, DictCode.交易对手类型);

        String workflowId = getParam("workflowId");

        String tradeId = RequestUtil.getParam("tradeId");
        String prodId = this.getParam("prodId");
        Map<String, Object> params = new HashMap<>();

        //来自于代办
        if( StringUtil.isNotEmpty(workflowId)){
            //根据工作流与留痕处理提供的共通方法获取业务实体
            TrdBondTrade001Dto trdBondTrade001Dto = trdBondTrade001Service.getBussinessEntityByWorkflowId(workflowId);
            params.put("tradeId", trdBondTrade001Dto.getTradeId());
            params.put("prodId", trdBondTrade001Dto.getFinprodId());
            model.addAttribute("ps", trdBondTrade001Dto.getPs());
            model.addAttribute("a", trdBondTrade001Dto.getPs());
        }else {
            params.put("tradeId", tradeId);
            params.put("prodId", prodId);
            model.addAttribute("ps", RequestUtil.getParam("ps"));
        }
        BondTradeDto bondTradeDto = bondTradeQuery001Service.findByTradeId(params);
        model.addAttribute("entity", bondTradeDto);
        return super.init(model);
    }
    

    
    
    
}
