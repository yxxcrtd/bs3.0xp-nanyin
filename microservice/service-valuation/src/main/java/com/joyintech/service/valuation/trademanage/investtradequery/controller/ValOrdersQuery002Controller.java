/*
 * 文件名：BondTradeQuery001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：zhangwei
 * 修改时间：2018年7月27日
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
import com.joyintech.service.valuation.trademanage.investtradequery.dto.ValOrdersDto;
import com.joyintech.service.valuation.trademanage.investtradequery.service.ValOrdersQuery002Service;

/**
 * 查看结算指令列表
 * @author zhangwei
 * @version 1.0 2018年7月27日
 * @see ValOrdersQuery001Controller2
 * @since 1.0
 */
@RestController(value="serviceValOrdersQuery002Controller")
@RequestMapping("/ValOrdersQuery002")
public class ValOrdersQuery002Controller extends BaseController{

    
    @Autowired
    private ValOrdersQuery002Service valOrdersQuery002Service;
    
//    /**
//     * 查看结算指令列表页面初始化
//     * {@inheritDoc}
//     */
//    @Override
//    public String init(Model model) {
//        model.addAttribute("tradeId", RequestUtil.getParam("tradeId"));
//        model.addAttribute("busiType", RequestUtil.getParam("busiType"));// 业务类型
//        model.addAttribute("feeType", RequestUtil.getParam("feeType"));
//        return super.init(model);
//    }
    
    /**
     * 根据tradeId判断，是否含有交易指令
     * @return 对应指令数
     * @see
     * @since 1.0
     */
    @PostMapping("/countOrders")
    public int countOrders(@RequestBody Map<String, Object> param) {
        return valOrdersQuery002Service.countOrders(param);
    }
    
    
    
    /**
     * 根据交易id获取交易指令列表(分页)
     * 
     * @return page
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @PostMapping("/findPage/{page}/{length}")
    public Page<ValOrdersDto> findPage(@RequestBody Map<String, Object> param,
                                       @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        return valOrdersQuery002Service.findBycondition(page, length, param);
    }
    
    /**
     * 根据交易id获取交易指令列表(分页)
     * 
     * @return page
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @PostMapping("findFeePage/{page}/{length}")
    public Page<ValOrdersDto> findFeePage(@RequestBody Map<String, Object> param,
                                          @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        return valOrdersQuery002Service.selectFeeBycondition(page, length, param);
    }
    
    /**
     * 根据交易id获取存款类资产交易的结算指令列表(分页)
     * 
     * @return page
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @PostMapping("findDepPage/{page}/{length}")
    public Page<ValOrdersDto> findDepPage(@RequestBody Map<String, Object> param,
                                          @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        return valOrdersQuery002Service.findDepBycondition(page, length, param);
    }
    
    
    
    
}
