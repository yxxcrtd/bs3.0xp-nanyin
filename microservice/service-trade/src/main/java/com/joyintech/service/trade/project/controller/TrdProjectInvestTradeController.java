/*
 * 文件名：TrdDepositInvestTradeController.java 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved. 描述：理财资管管理系统V2.0 修改人：fanshuanghu
 * 修改时间：2018年10月10日 修改内容：新建 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.trade.project.controller;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.trade.project.service.TrdProjectInvestTradeService;
import com.joyintech.tams.invest.common.dto.period.common.BaseTradeInfoDto;
import com.joyintech.tams.invest.common.dto.trade.project.TrdProjectInvestTradeDto;

/**
 * 存款类投资交易查询
 * 
 * @author fanshuanghu
 * @version 1.0 2018年10月10日
 * @see TrdProjectInvestTradeController
 * @since 1.0
 */
@RestController("TrdProjectInvestTradeCtr_dtx")
@RequestMapping("/TrdProjectInvestTrade")
public class TrdProjectInvestTradeController {

    /**
     * 注入存款类投资交易Service
     */
    @Autowired
    private TrdProjectInvestTradeService trdProjectInvestTradeService;

    /**
     * 获取委托代码下拉框集合
     * 
     * @return 委托代码下拉框集合
     * @see
     * @since 1.0
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<TrdProjectInvestTradeDto> findPage(@RequestBody Map<String, Object> param, @PathVariable("page") int page,
                                                   @PathVariable("length") int length)
        throws Exception {
        return trdProjectInvestTradeService.findPage(page, length, param);
    }

    /**
     * 获取委托代码下拉框集合
     *
     * @return 委托代码下拉框集合
     * @see
     * @since 1.0
     */
    @PostMapping("findEntrustId/{page}/{length}")
    public Page<SelectOptionDto> findEntrustId(@RequestBody Map<String, Object> param, @PathVariable("page") int page,
                                               @PathVariable("length") int length) {
        return trdProjectInvestTradeService.findEntrustId(page, length, param);
    }

    /**
     * 画面查询方法-市场代码
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("getFinprodMarketId/{page}/{length}")
    public Page<SelectOptionDto> getFinprodMarketId(@RequestBody Map<String, Object> param, @PathVariable("page") int page,
                                                    @PathVariable("length") int length)
        throws Exception {
        return trdProjectInvestTradeService.getFinprodMarketId(page, length, param);
    }

    /**
     * 画面查询方法-市场代码
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("findInvestRate")
    public Map<String, Object> findInvestRate(@RequestBody Map<String, Object> params) {
        return trdProjectInvestTradeService.findInvestRate(params);
    }

    /**
     * 画面查询方法-市场代码
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("findEffectiveData")
    public TrdProjectInvestTradeDto findEffectiveData(@RequestBody Map<String, Object> param) {
        return trdProjectInvestTradeService.findEffectiveData(param);
    }

    /**
     * 设置工作流代码
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @Transactional
    @DTXTransaction
    @PostMapping("updateSetWflByTradeId")
    public String updateSetWflByTradeId(@RequestBody BaseTradeInfoDto tradeInfoDto) {
        return trdProjectInvestTradeService.updateSetWflByTradeId(tradeInfoDto);
    }

    /**
     * 查询是否有后续交易
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("countFollowDeal")
    public int countFollowDeal(@RequestBody Map<String, Object> param) {
        return trdProjectInvestTradeService.countFollowDeal(param);
    }
    
    
    @PostMapping("getDoEntrustMoneyByEntrustId")
    public BigDecimal getDoEntrustMoneyByEntrustId(@RequestBody Map<String, Object> param) {
        return trdProjectInvestTradeService.getDoEntrustMoneyByEntrustId(param);
    }
    

    /**
     * 查询是否为还本不带息资产
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("findRepayFlag")
    public Map<String, Object> findRepayFlag(@RequestBody Map<String, Object> params) {
        return trdProjectInvestTradeService.findRepayFlag(params);
    }
    
    /**
     * 查询交易是否撤销
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("findRevokeDeal")
    public int findRevokeDeal(@RequestBody Map<String, Object> param) {
        return trdProjectInvestTradeService.findRevokeDeal(param);
    }
}
