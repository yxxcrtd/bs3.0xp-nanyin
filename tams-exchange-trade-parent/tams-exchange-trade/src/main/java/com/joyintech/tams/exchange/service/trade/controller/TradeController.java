package com.joyintech.tams.exchange.service.trade.controller;

import com.github.pagehelper.PageInfo;
import com.joyintech.tams.exchange.common.ResponseResultBody;
import com.joyintech.tams.exchange.common.bean.HistoryTrade;
import com.joyintech.tams.exchange.common.db.domain.Trade;
import com.joyintech.tams.exchange.common.domain.Message;
import com.joyintech.tams.exchange.common.request.HistoryTradeInfoRequest;
import com.joyintech.tams.exchange.common.request.PageTradeInfoRequest;
import com.joyintech.tams.exchange.common.request.TradeInfoRequest;
import com.joyintech.tams.exchange.service.trade.management.service.TradeManagement;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("TradeController_dtx")
@RequestMapping("/trade/joyin")
@ResponseResultBody
@Api(tags = "场内交易-成交查询接口")
public class TradeController {

    @Autowired
    private TradeManagement tradeManagement;

    @ApiOperation(value = "查询成交信息", notes = "查询成交信息")
    @RequestMapping(value = "/trade", method = RequestMethod.POST)
    public Message<List<Trade>> queryTradeAll(@RequestBody @Validated TradeInfoRequest request) {
        return tradeManagement.queryTrade(request);
    }

    @ApiOperation(value = "查询历史成交信息", notes = "查询历史成交信息")
    @RequestMapping(value = "/trade/history", method = RequestMethod.POST)
    public Message<List<HistoryTrade>> queryHistoryTrade(@RequestBody @Validated HistoryTradeInfoRequest request) {
        return tradeManagement.queryHistoryTrade(request);
    }

    @ApiOperation(value = "查询分页成交信息", notes = "查询分页成交信息")
    @RequestMapping(value = "/trade/page", method = RequestMethod.POST)
    public Message<PageInfo<Object>> queryAllForPage(@RequestBody @Validated PageTradeInfoRequest request) {
        return tradeManagement.queryAllForPage(request);
    }

    @ApiOperation(value = "根据委托编号查询成交信息", notes = "根据委托编号查询成交信息")
    @RequestMapping(value = "/tradeByEntrustId", method = RequestMethod.POST)
    public Message<List<Trade>> queryTradeByEntrustId(@RequestParam @NotBlank(message = "用户编号不可为空") String userId,
                                                      @RequestParam @NotBlank(message = "委托编号不可为空") String entrustId) {
        return new Message<>(tradeManagement.queryTradeByEntrustId(userId, entrustId));
    }

    @ApiOperation(value = "根据订单编号查询成交信息", notes = "根据订单编号查询成交信息")
    @RequestMapping(value = "/tradeByOrderId", method = RequestMethod.POST)
    public Message<List<Trade>> queryTradeByOrderId(@RequestParam @NotBlank(message = "用户编号不可为空") String userId,
                                                    @RequestParam @NotBlank(message = "订单编号不可为空") String orderId) {
        return new Message<>(tradeManagement.queryTradeByOrderId(userId, orderId));
    }

    @ApiOperation(value = "根据指令编号查询成交信息", notes = "根据指令编号查询成交信息")
    @RequestMapping(value = "/tradeByInstructionId", method = RequestMethod.POST)
    public Message<List<Trade>> queryTradeByInstructionId(@RequestParam @NotBlank(message = "用户编号不可为空") String userId,
                                                          @RequestParam @NotBlank(message = "指令编号不可为空") String instructionId) {
        return new Message<>(tradeManagement.queryTradeByInstructionId(userId, instructionId));
    }

    @ApiOperation(value = "根据资金账户编号查询成交信息", notes = "根据资金账户编号查询成交信息")
    @RequestMapping(value = "/tradeByAccountId", method = RequestMethod.POST)
    public Message<List<Trade>> queryTradeByAccountId(@RequestParam @NotBlank(message = "用户编号不可为空") String userId,
                                                      @RequestParam @NotBlank(message = "资金账户编号不可为空") String accountId) {
        return new Message<>(tradeManagement.queryTradeByAccountId(userId, accountId));
    }
}
