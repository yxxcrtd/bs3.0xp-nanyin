package com.joyintech.tams.exchange.service.trade.controller;

import com.google.common.base.Strings;
import com.joyintech.tams.exchange.common.bean.MinPrice;
import com.joyintech.tams.exchange.common.bean.MinTrdUnitLimit;
import com.joyintech.tams.exchange.common.bean.instrument.MinPriceAndMinTrdUnitLimitBean;
import com.joyintech.tams.exchange.common.db.domain.Instrument;
import com.joyintech.tams.exchange.common.domain.Message;
import com.joyintech.tams.exchange.common.request.InstrumentFlushRequest;
import com.joyintech.tams.exchange.common.request.MinPriceAndMinTrdUnitLimitQueryRequest;
import com.joyintech.tams.exchange.service.trade.management.service.InstrumentManagement;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/trade/joyin")
@Api(tags = "场内交易-合约数据查询接口")
public class InstrumentController {
    @Autowired
    private InstrumentManagement instrumentManagement;

    @ApiOperation(value = "合约数据刷新", notes = "合约数据刷新")
    @RequestMapping(value = "/instrument/flush", method = RequestMethod.POST)
    public Message<Object> instrumentFlush(@RequestBody InstrumentFlushRequest instrumentFlushRequest) {
        Map<String, Object> params = new HashMap<String, Object>();
        if (!Strings.isNullOrEmpty(instrumentFlushRequest.getMarket())) {
            params.put("market", instrumentFlushRequest.getMarket());
        }
        if (!Strings.isNullOrEmpty(instrumentFlushRequest.getStockCode())) {
            params.put("stock_code", instrumentFlushRequest.getStockCode());
        }
        if (!Strings.isNullOrEmpty(instrumentFlushRequest.getPageNo())) {
            params.put("page_no", instrumentFlushRequest.getPageNo());
        }
        if (!Strings.isNullOrEmpty(instrumentFlushRequest.getCount())) {
            params.put("count", instrumentFlushRequest.getCount());
        }
        return new Message<Object>(instrumentManagement.flushInstrumentData(params));
    }

    @ApiOperation(value = "最小价差查询", notes = "最小价差查询")
    @RequestMapping(value = "/instrument/minPrice", method = RequestMethod.GET)
    public Message<MinPrice> minPrice(@RequestBody @Valid MinPriceAndMinTrdUnitLimitQueryRequest queryRequest) {
        Instrument instrument = new Instrument();
        instrument.setMarket(queryRequest.getMarket());
        instrument.setAssetType(queryRequest.getAssetType());
        instrument.setAssetType1(queryRequest.getAssetType1());
        instrument.setSubStockType(queryRequest.getAssetType2());
        return new Message<MinPrice>(instrumentManagement.minPrice(queryRequest.getTradePlat(), instrument));
    }

    @ApiOperation(value = "交易单位及上下限查询", notes = "交易单位及上下限查询")
    @RequestMapping(value = "/instrument/minTrdUnitLimit", method = RequestMethod.GET)
    public Message<MinTrdUnitLimit> minTrdUnitLimit(@RequestBody @Valid MinPriceAndMinTrdUnitLimitQueryRequest queryRequest) {
        Instrument instrument = new Instrument();
        instrument.setMarket(queryRequest.getMarket());
        instrument.setAssetType(queryRequest.getAssetType());
        instrument.setAssetType1(queryRequest.getAssetType1());
        instrument.setSubStockType(queryRequest.getAssetType2());
        return new Message<MinTrdUnitLimit>(instrumentManagement.minTrdUnitLimit(queryRequest.getTradePlat(), instrument, queryRequest.getInstructionPs()));
    }

    @ApiOperation(value = "期货合约数据刷新", notes = "期货合约数据刷新")
    @RequestMapping(value = "/futuresInstrument/flush", method = RequestMethod.POST)
    public Message<Object> futuresInstrumentFlush() {
        return new Message<Object>(instrumentManagement.flushFuturesInstrumentData());
    }

    /**
     * 最小价差及交易上下限查询
     *
     * @param stockCode     合约代码
     * @param tradePlat     交易平台
     * @param instructionPs 交易方向
     * @return Message
     */
    @ApiOperation(value = "最小价差及交易上下限查询", notes = "最小价差及交易上下限查询")
    @RequestMapping(value = "/instrument/minPrice/minTrdUnitLimit", method = RequestMethod.POST)
    public Message<MinPriceAndMinTrdUnitLimitBean> queryMinPriceAndMinTrdUnitLimit(@RequestParam(value = "stockCode") String stockCode,
                                                                                   @RequestParam(value = "tradePlat", required = false) String tradePlat,
                                                                                   @RequestParam(value = "instructionPs", required = false) String instructionPs) {
        return new Message<MinPriceAndMinTrdUnitLimitBean>(instrumentManagement.queryMinPriceAndMinTrdUnitLimit(stockCode, tradePlat, instructionPs));
    }
}
