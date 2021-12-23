package com.joyintech.tams.exchange.service.trade.controller;

import com.joyintech.tams.exchange.common.ResponseResultBody;
import com.joyintech.tams.exchange.common.bean.ResultBean;
import com.joyintech.tams.exchange.common.request.HistoryTradeInfoRequest;
import com.joyintech.tams.exchange.common.request.PageTradeInfoRequest;
import com.joyintech.tams.exchange.common.request.TradeInfoRequest;
import com.joyintech.tams.exchange.service.trade.management.service.TradeManagement;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController("TradeController_dtx")
@RequestMapping("/trade/joyin")
@ResponseResultBody
@Api(tags = "场内交易-成交查询接口")
public class TradeController {

    @Autowired
    private TradeManagement tradeManagement;

    @ApiOperation(value = "查询成交信息", notes = "查询成交信息")
    @RequestMapping(value = "/trade", method = RequestMethod.POST)
    public ResultBean queryTradeAll(@RequestBody @Validated TradeInfoRequest request) {
        return tradeManagement.queryTrade(request);
    }

    @ApiOperation(value = "查询历史成交信息", notes = "查询历史成交信息")
    @RequestMapping(value = "/trade/history", method = RequestMethod.POST)
    public ResultBean queryHistoryTrade(@RequestBody @Validated HistoryTradeInfoRequest request) {
        return tradeManagement.queryHistoryTrade(request);
    }

    @ApiOperation(value = "查询分页成交信息", notes = "查询分页成交信息")
    @RequestMapping(value = "/trade/page", method = RequestMethod.POST)
    public ResultBean queryAllForPage(@RequestBody @Validated PageTradeInfoRequest request) {
        return tradeManagement.queryAllForPage(request);
    }
}
