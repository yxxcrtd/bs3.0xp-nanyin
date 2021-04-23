package com.joyintech.tams.exchange.service.trade.controller;

import com.google.common.base.Strings;
import com.joyintech.tams.exchange.common.bean.ResultBean;
import com.joyintech.tams.exchange.common.db.domain.Instrument;
import com.joyintech.tams.exchange.service.trade.management.service.InstrumentManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * create By MAXX on 2020/10/15
 */
@RestController
@RequestMapping("/service/joyin")
public class InstrumentController {
    @Autowired
    private InstrumentManagement instrumentManagement;

    @RequestMapping(value = "/instrument/flush", method = RequestMethod.GET)
    public ResultBean instrumentFlush(@RequestParam(value = "market", required = false) String market,
                                      @RequestParam(value = "stock_code", required = false) String stockCode,
                                      @RequestParam(value = "page_no", required = false) String pageNo,
                                      @RequestParam(value = "count", required = false) String count) {
        Map<String, Object> params = new HashMap<String, Object>();
        if (!Strings.isNullOrEmpty(market)) {
            params.put("market", market);
        }
        if (!Strings.isNullOrEmpty(stockCode)) {
            params.put("stock_code", stockCode);
        }
        if (!Strings.isNullOrEmpty(pageNo)) {
            params.put("page_no", pageNo);
        }
        if (!Strings.isNullOrEmpty(count)) {
            params.put("count", count);
        }
        return instrumentManagement.flushInstrumentData(params);
    }

    /**
     * * 最小价差查询接口
     *
     * @param market     市场
     * @param tradePlat  交易平台
     * @param assetType  资产一级分类
     * @param assetType1 资产二级分类
     * @param assetType2 资产三级分类
     * @return ResultBean
     */
    @RequestMapping(value = "/instrument/minPrice", method = RequestMethod.GET)
    public ResultBean minPrice(@RequestParam(value = "market") String market,
                               @RequestParam(value = "trade_plat", required = false, defaultValue = "02") String tradePlat,
                               @RequestParam(value = "asset_type") String assetType,
                               @RequestParam(value = "asset_type1", required = false) String assetType1,
                               @RequestParam(value = "asset_type2", required = false) String assetType2) {
        Instrument instrument = new Instrument();
        instrument.setMarket(market);
        instrument.setAssetType(assetType);
        instrument.setAssetType1(assetType1);
        instrument.setSubStockType(assetType2);
        return instrumentManagement.minPrice(tradePlat, instrument);
    }

    /**
     * 交易单位及上下限查询
     *
     * @param market        市场
     * @param tradePlat     交易平台
     * @param assetType     资产一级分类
     * @param assetType1    资产二级分类
     * @param assetType2    资产三级分类
     * @param instructionPs 交易方向
     * @return ResultBean
     */
    @RequestMapping(value = "/instrument/minTrdUnitLimit", method = RequestMethod.GET)
    public ResultBean minTrdUnitLimit(@RequestParam(value = "market") String market,
                                      @RequestParam(value = "trade_plat", required = false, defaultValue = "02") String tradePlat,
                                      @RequestParam(value = "asset_type") String assetType,
                                      @RequestParam(value = "asset_type1", required = false) String assetType1,
                                      @RequestParam(value = "asset_type2", required = false) String assetType2,
                                      @RequestParam(value = "instruction_ps", required = false, defaultValue = "T01") String instructionPs) {
        Instrument instrument = new Instrument();
        instrument.setMarket(market);
        instrument.setAssetType(assetType);
        instrument.setAssetType1(assetType1);
        instrument.setSubStockType(assetType2);
        return instrumentManagement.minTrdUnitLimit(tradePlat, instrument, instructionPs);
    }

    @RequestMapping(value = "/futuresInstrument/flush", method = RequestMethod.GET)
    public ResultBean FuturesInstrumentFlush() {
        return instrumentManagement.flushFuturesInstrumentData();
    }

    /**
     * 最下价差及交易上下限查询
     *
     * @param stockCode 合约代码
     * @param tradePlat 交易平台
     * @return ResultBean
     */
    @RequestMapping(value = "/instrument/minPrice/minTrdUnitLimit", method = RequestMethod.POST)
    public ResultBean queryMinPriceAndMinTrdUnitLimit(@RequestParam(value = "stock_code") String stockCode,
                                                      @RequestParam(value = "trade_plat", required = false) String tradePlat,
                                                      @RequestParam(value = "instruction_ps", required = false) String instructionPs) {
        return instrumentManagement.queryMinPriceAndMinTrdUnitLimit(stockCode, tradePlat, instructionPs);
    }
}
