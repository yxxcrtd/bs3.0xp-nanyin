/*
 * 文件名：TaxBalanceQuery001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：leipeng
 * 修改时间：2018年10月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.valuation.trademanage.trdplanratevaluationtrade.controller;

import java.util.Map;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.service.valuation.trademanage.trdplanratevaluationtrade.dto.TrdPlanRateValuationTradeDto;
import com.joyintech.service.valuation.trademanage.trdplanratevaluationtrade.service.TrdPlanRateValuationTradeService;
import com.joyintech.tams.systemtools.interf.IPlatformDate;
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
 * @see TrdPlanRateValuationTradeController
 * @since 1.0
 */
@RestController(value="serviceTrdPlanRateValuationTradeController")
@RequestMapping("/TrdPlanRateValuationTrade")
public class TrdPlanRateValuationTradeController extends BaseController {

    @Autowired
    private TrdPlanRateValuationTradeService trdPlanRateValuationTradeService;

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
        return trdPlanRateValuationTradeService.findPortfolioId(page,length,map);
    }

    /**
     * 资产代码下拉框
     * @since 1.0
     */
    @PostMapping("findFinprodId/{page}/{length}")
    public Page<SelectOptionDto> findFinprodId(@RequestBody Map<String,Object> map,
                                                 @PathVariable("page") int page, @PathVariable("length") int length) {
        return trdPlanRateValuationTradeService.findFinprodId(page,length,map);
    }

    /**
     * 回调函数 操作具体 new delete update <功能详细描述>
     *
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("/doComplete/{type}")
    public void doComplete(@RequestBody TrdPlanRateValuationTradeDto dto, @PathVariable("type") String type) {
        dto.setOperateType(type);
        trdPlanRateValuationTradeService.doComplete(dto);
    }

    /**
     * 查询 支持分页 <功能详细描述>
     *
     * @since 1.0
     */
    @RequestMapping("/selectForPage/{page}/{length}")
    public Page<TrdPlanRateValuationTradeDto> selectForPage(@RequestBody Map<String, Object> pageParam, @PathVariable("page") int page,
                                                @PathVariable("length") int length)
            throws Exception {
        Page<TrdPlanRateValuationTradeDto> page1 = trdPlanRateValuationTradeService.selectForPage(page, length, pageParam);
        return page1;
    }

    /**
     * 策略代码生成
     * @see
     * @since 1.0
     */
    @PostMapping("createPlanTradeId")
    public String createPlanTradeId() {
        return trdPlanRateValuationTradeService.createPlanTradeId();
    }


}
