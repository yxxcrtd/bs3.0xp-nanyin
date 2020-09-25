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

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.service.valuation.trademanage.investtradequery.dto.InvestTradeDto;
import com.joyintech.service.valuation.trademanage.investtradequery.service.InvestTradeQuery001Service;

/**
 * 投资交易查询-controller
 * 
 * @author yangjian
 * @version 1.0 2018年7月24日
 * @see ValPlanAssets001Controller
 * @since 1.0
 */
@RestController(value="serviceValPlanAssetsDeal001Controller")
@RequestMapping("/ValPlanAssetsDeal001")
public class ValPlanAssetsDeal001Controller extends BaseController {

    @Autowired
    private InvestTradeQuery001Service investTradeQuery001Service;

//    /**
//     * 加载页面 {@inheritDoc}
//     */
//    @Override
//    public String init(Model model) {
//        String ps=this.getParam("ps");
//        String tradeId=this.getParam("tradeId");
//        model.addAttribute("ps", ps);
//        model.addAttribute("tradeId", tradeId);
//        // 业务类别下拉
//        this.setSelectOptionToModel(model, DictCode.交易方向);
//        // 核算主体类别
//        this.setSelectOptionToModel(model, DictCode.币种);
//        Map<String,Object> map=new HashMap<String,Object>();
//        map.put("paTradeId", tradeId);
//        String prodId = this.getParam("prodId");
//        map.put("prodId", prodId);
//        InvestTradeDto dto=investTradeQuery001Service.queryFinProductDealBytradeId(map);
//        model.addAttribute("entity", dto);
//        return super.init(model);
//    }

    @PostMapping("/queryFinProductDealBytradeId")
    public InvestTradeDto queryFinProductDealBytradeId(@RequestBody Map<String, Object> param) throws Exception {
        return investTradeQuery001Service.queryFinProductDealBytradeId(param);
    }
    
    /**
     * 分页数据查询
     * 
     * @return page
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @PostMapping("/findPage/{page}/{length}")
    public Page<InvestTradeDto> findPage(@RequestBody Map<String, Object> param,
                                         @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        return investTradeQuery001Service.findPage(page, length, param);
    }

}
