/*
 * 文件名：TaxBalanceQuery001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：leipeng
 * 修改时间：2018年10月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.valuation.trademanage.trdfxforwardvaluationtrade.controller;

import java.util.List;
import java.util.Map;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.service.valuation.trademanage.trdfxforwardvaluationtrade.dto.TrdFxForwardTradeResult;
import com.joyintech.service.valuation.trademanage.trdfxforwardvaluationtrade.dto.TrdFxForwardValuationTradeDto;
import com.joyintech.service.valuation.trademanage.trdfxforwardvaluationtrade.service.TrdFxForwardValuationTradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;

/**
 * service 计划类利率型交易
 * 
 * @author yuzj
 * @version 1.0 2021年12月29日
 * @see TrdFxforwardValuationTradeController
 * @since 1.0
 */
@RestController(value="serviceTrdFxForwardValuationTradeController")
@RequestMapping("/TrdFxForwardValuationTrade")
public class TrdFxforwardValuationTradeController extends BaseController {

    @Autowired
    private TrdFxForwardValuationTradeService trdFxForwardValuationTradeService;

    /**
     * 产品代码下拉框
     *
     * @return 返回的数据
     * @exception Exception
     *                异常
     * @since 1.0
     */
    @PostMapping("findPortfolioId/{page}/{length}")
    public Page<SelectOptionDto> findPortfolioId(@RequestBody Map<String,Object> map,
                                           @PathVariable("page") int page, @PathVariable("length") int length) {
        return trdFxForwardValuationTradeService.findPortfolioId(page,length,map);
    }

    /**
     * 资产代码下拉框
     * @since 1.0
     */
    @PostMapping("findFinprodId/{page}/{length}")
    public Page<SelectOptionDto> findFinprodId(@RequestBody Map<String,Object> map,
                                                 @PathVariable("page") int page, @PathVariable("length") int length) {
        return trdFxForwardValuationTradeService.findFinprodId(page,length,map);
    }

    /**
     * 查询 支持分页 <功能详细描述>
     *
     * @since 1.0
     */
    @RequestMapping("/selectForPage/{page}/{length}")
    public Page<TrdFxForwardValuationTradeDto> selectForPage(@RequestBody Map<String, Object> pageParam, @PathVariable("page") int page,
                                                        @PathVariable("length") int length)
            throws Exception {
        Page<TrdFxForwardValuationTradeDto> page1 = trdFxForwardValuationTradeService.selectForPage(page, length, pageParam);
        return page1;
    }

    /**
     * 策略代码生成
     * @see
     * @since 1.0
     */
    @PostMapping("createPlanTradeId")
    public String createPlanTradeId() {
        return trdFxForwardValuationTradeService.createPlanTradeId();
    }

    /**
     * 分页数据查询
     *
     * @return page
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @PostMapping("/findAll")
    public List<TrdFxForwardValuationTradeDto> findAll(@RequestBody Map<String, Object> param){
        return trdFxForwardValuationTradeService.findAll(param);
    }

    /**
     * 回调函数 操作具体 new
     *
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("insert")
    public void insert(@RequestBody TrdFxForwardTradeResult dto) {
        trdFxForwardValuationTradeService.insert(dto);
    }

    /**
     * 回调函数 操作具体 update <功能详细描述>
     *
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("update")
    public void update(@RequestBody TrdFxForwardTradeResult dto) {
        trdFxForwardValuationTradeService.update(dto);
    }


    @Transactional
    @DTXTransaction
    @PostMapping("delete")
    public int delete(@RequestBody TrdFxForwardValuationTradeDto dto){
        return trdFxForwardValuationTradeService.delete(dto);
    }

    /**
     * 根据交易编号查询对象
     * @see
     * @since 1.0
     */
    @PostMapping("queryTradeInfoByTradeId")
    public TrdFxForwardValuationTradeDto queryTradeInfoByTradeId(@RequestBody String  tradeId) {
        return trdFxForwardValuationTradeService.queryTradeInfoByTradeId(tradeId);
    }

}
