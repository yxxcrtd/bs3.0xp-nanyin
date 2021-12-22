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
import org.springframework.web.bind.annotation.*;

@RestController("TradeController_dtx")
@RequestMapping("/trade/joyin")
@ResponseResultBody
@Api(tags = "场内交易成交接口")
public class TradeController {

    @Autowired
    private TradeManagement tradeManagement;

    @ApiOperation(value = "查询成交信息", notes = "查询成交信息")
    @RequestMapping(value = "/trade", method = RequestMethod.POST)
    public ResultBean queryTradeAll(@RequestBody TradeInfoRequest request) {
        return tradeManagement.queryTrade(request);
    }

    @ApiOperation(value = "查询历史成交信息", notes = "查询历史成交信息")
    @RequestMapping(value = "/trade/history", method = RequestMethod.POST)
    public ResultBean queryHistoryTrade(@RequestBody HistoryTradeInfoRequest request) {
        return tradeManagement.queryHistoryTrade(request);
    }

    @ApiOperation(value = "查询分页成交信息", notes = "查询分页成交信息")
    @RequestMapping(value = "/trade/page", method = RequestMethod.POST)
    public ResultBean queryAllForPage(PageTradeInfoRequest request) {
        return tradeManagement.queryAllForPage(request);
    }
}
