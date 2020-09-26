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



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.valuation.trademanage.investtradequery.dto.ValOrdersDto;
import com.joyintech.tams.valuation.trademanage.investtradequery.service.ValOrdersQuery003Service;

/**
 * 查看结算指令列表
 * @author zhangwei
 * @version 1.0 2018年7月27日
 * @see ValOrdersQuery001Controller2
 * @since 1.0
 */
@Controller
@PageId("ValOrdersQuery003")
@RequestMapping("/valuation/trademanage/investtradequery/ValOrdersQuery003")
public class ValOrdersQuery003Controller extends BaseController{

    
    @Autowired
    private ValOrdersQuery003Service valOrdersQuery003Service;
    
    /**
     * 查看结算详情页面初始化
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.收付方向);
        this.setSelectOptionToModel(model, DictCode.币种);
        this.setSelectOptionToModel(model, DictCode.指令类型);
        this.setSelectOptionToModel(model, DictCode.金融产品类型);
        
        ValOrdersDto valOrdersDto = valOrdersQuery003Service.findByOrderId(RequestUtil.getParam("orderId"),RequestUtil.getParam("dealType"));        
        
        model.addAttribute("entity", valOrdersDto);
        model.addAttribute("busiType", RequestUtil.getParam("busiType"));// 业务类型
        return super.init(model);
    }
    
    
    
    
    
}
