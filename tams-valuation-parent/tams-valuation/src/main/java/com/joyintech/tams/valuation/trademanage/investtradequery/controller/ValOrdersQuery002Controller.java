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

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.valuation.trademanage.investtradequery.dto.ValOrdersDto;
import com.joyintech.tams.valuation.trademanage.investtradequery.service.ValOrdersQuery002Service;
import com.netflix.discovery.converters.Auto;

/**
 * 查看结算指令列表
 * @author zhangwei
 * @version 1.0 2018年7月27日
 * @see ValOrdersQuery001Controller2
 * @since 1.0
 */
@Controller
@PageId("ValOrdersQuery002")
@RequestMapping("/valuation/trademanage/investtradequery/ValOrdersQuery002")
public class ValOrdersQuery002Controller extends BaseController{

    
    @Autowired
    private ValOrdersQuery002Service valOrdersQuery002Service;
    
    /**
     * 查看结算指令列表页面初始化
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        model.addAttribute("tradeId", RequestUtil.getParam("tradeId"));
        model.addAttribute("busiType", RequestUtil.getParam("busiType"));// 业务类型
        model.addAttribute("feeType", RequestUtil.getParam("feeType"));
        return super.init(model);
    }
    
    /**
     * 根据tradeId判断，是否含有交易指令
     * @return 对应指令数
     * @see
     * @since 1.0
     */
    @RequestMapping("countOrders")
    @ResponseBody
    public int countOrders() {
        return valOrdersQuery002Service.countOrders(RequestUtil.getPageParam());
    }
    
    
    
    /**
     * 根据交易id获取交易指令列表(分页)
     * 
     * @return page
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("findPage")
    @ResponseBody
    public Page<ValOrdersDto> findPage()  throws Exception {
        return valOrdersQuery002Service.findBycondition(RequestUtil.getPageParam());
    }
    
    /**
     * 根据交易id获取交易指令列表(分页)
     * 
     * @return page
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("findFeePage")
    @ResponseBody
    public Page<ValOrdersDto> findFeePage()  throws Exception {
        return valOrdersQuery002Service.selectFeeBycondition(RequestUtil.getPageParam());
    }
    
    /**
     * 根据交易id获取存款类资产交易的结算指令列表(分页)
     * 
     * @return page
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("findDepPage")
    @ResponseBody
    public Page<ValOrdersDto> findDepPage()  throws Exception {
        return valOrdersQuery002Service.findDepBycondition(RequestUtil.getPageParam());
    }
    
    
    
    
}
