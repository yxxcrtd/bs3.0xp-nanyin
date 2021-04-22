package com.joyintech.tams.exchange.datacommon.db.service.impl;

import com.joyintech.tams.exchange.common.bean.MinPrice;
import com.joyintech.tams.exchange.common.bean.MinTrdUnitLimit;
import com.joyintech.tams.exchange.common.bean.instrument.MinPriceAndMinTrdUnitLimitBean;
import com.joyintech.tams.exchange.common.constant.ErrorCode;
import com.joyintech.tams.exchange.common.constant.instrument.TradeUnit;
import com.joyintech.tams.exchange.common.db.domain.FuturesInstrument;
import com.joyintech.tams.exchange.common.db.domain.FuturesInstrumentExample;
import com.joyintech.tams.exchange.common.db.domain.Instruction;
import com.joyintech.tams.exchange.datacommon.cache.impl.AsyncServiceImpl;
import com.joyintech.tams.exchange.datacommon.db.mapper.FuturesInstrumentMapper;
import com.joyintech.tams.exchange.datacommon.db.mapper.IMybatisMapper;
import com.joyintech.tams.exchange.datacommon.db.service.FuturesInstrumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class FuturesInstrumentServiceImpl extends AsyncServiceImpl<String, FuturesInstrument> implements FuturesInstrumentService {
    @Autowired
    FuturesInstrumentMapper futuresInstrumentMapper;

    private Map<String, Map<String, FuturesInstrument>> marketMap = new ConcurrentHashMap<>();

    private Map<String, FuturesInstrument> stockCodeMap = new ConcurrentHashMap<>();
    // 缓存合约下价差、交易上下限数据 --- 仅查询时缓存
    private Map<String, MinPriceAndMinTrdUnitLimitBean> minPriceAndMinTrdUnitLimitBeanMap = new ConcurrentHashMap<>();

    @Override
    public void clearMinPriceAndMinTrdUnitLimitCache() {
        minPriceAndMinTrdUnitLimitBeanMap.clear();
    }

    @Override
    public FuturesInstrument queryByInnerCode(String innerCode) {
        return findById(innerCode);
    }

    @Override
    public FuturesInstrument queryByStockCode(String stockCode) {
        return stockCodeMap.get(stockCode);
    }

    @Override
    public List<FuturesInstrument> queryByMarket(String market) {
        return new ArrayList<>(marketMap.getOrDefault(market, new HashMap<>()).values());
    }

    @Override
    public MinPriceAndMinTrdUnitLimitBean queryMinPriceAndMinTrdUnitLimitByFuture(Instruction instruction) {
        MinPriceAndMinTrdUnitLimitBean minPriceAndMinTrdUnitLimitBean = minPriceAndMinTrdUnitLimitBeanMap.get(instruction.getFinprodMarketId());
        if (Objects.nonNull(minPriceAndMinTrdUnitLimitBean)) {
            return minPriceAndMinTrdUnitLimitBean;
        }
        FuturesInstrument futuresInstrument = queryByInnerCode(instruction.getFinprodMarketId());
        if (Objects.nonNull(futuresInstrument)) {
            //设置最小价差
            minPriceAndMinTrdUnitLimitBean = new MinPriceAndMinTrdUnitLimitBean(futuresInstrument.getInnerCode(), ErrorCode.SUCCESS.value, ErrorCode.SUCCESS.errorMsg);
            minPriceAndMinTrdUnitLimitBean.setStockCode(futuresInstrument.getInnerCode());
            List<MinPrice> minPriceList = new ArrayList<>();
            MinPrice minPrice = new MinPrice();
            minPrice.setMarket(futuresInstrument.getExchangeId());
            minPrice.setAssetType(instruction.getAssetType());
            minPrice.setAssetType1(instruction.getAssetType1());
            minPrice.setMinPrice(futuresInstrument.getPriceTick());
            minPriceList.add(minPrice);
            minPriceAndMinTrdUnitLimitBean.setMinPriceList(minPriceList);
            //设置最小/最大报单数量
            List<MinTrdUnitLimit> minTrdUnitLimitList = new ArrayList<>();
            MinTrdUnitLimit minTrdUnitLimit = new MinTrdUnitLimit();
            minTrdUnitLimit.setMarket(futuresInstrument.getExchangeId());
            minTrdUnitLimit.setAssetType(instruction.getAssetType());
            minTrdUnitLimit.setAssetType1(instruction.getAssetType1());
            minTrdUnitLimit.setPs(instruction.getPs());
            minTrdUnitLimit.setMinStep(futuresInstrument.getBuyUnit());
            minTrdUnitLimit.setMinStepUnit(TradeUnit.ZHANG.value);
            minTrdUnitLimit.setMinNum(futuresInstrument.getMinLimitOrderVolume());
            minTrdUnitLimit.setMinNumUnit(TradeUnit.ZHANG.value);
            minTrdUnitLimit.setMaxNum(futuresInstrument.getMaxLimitOrderVolume());
            minTrdUnitLimit.setMaxNumUnit(TradeUnit.ZHANG.value);
            minTrdUnitLimit.setMinNumMarket(futuresInstrument.getMinMarketOrderVolume());
            minTrdUnitLimit.setMinNumMarketUnit(TradeUnit.ZHANG.value);
            minTrdUnitLimit.setMaxNumMarket(futuresInstrument.getMaxMarketOrderVolume());
            minTrdUnitLimit.setMaxNumMarketUnit(TradeUnit.ZHANG.value);
            minTrdUnitLimitList.add(minTrdUnitLimit);
            minPriceAndMinTrdUnitLimitBean.setMinTrdUnitLimitList(minTrdUnitLimitList);
            //设置合约乘数
            minPriceAndMinTrdUnitLimitBean.setVolumeMultiple(futuresInstrument.getVolumeMultiple());
            minPriceAndMinTrdUnitLimitBeanMap.put(futuresInstrument.getInnerCode(), minPriceAndMinTrdUnitLimitBean);
        } else {
            minPriceAndMinTrdUnitLimitBean = new MinPriceAndMinTrdUnitLimitBean(instruction.getFinprodMarketId(), ErrorCode.REQ_PARAMETER_STOCK_CODE_ERROR.value, ErrorCode.REQ_PARAMETER_STOCK_CODE_ERROR.errorMsg);
        }
        return minPriceAndMinTrdUnitLimitBean;
    }

    @Override
    public void clearCache() {
        memory_cache.clear();
        marketMap.clear();
    }

    @Override
    protected void update(FuturesInstrument instrument) {
        save(instrument);
    }

    @Override
    protected void save(FuturesInstrument instrument) {
        saveMemoryCache(instrument);
    }

    private void saveMemoryCache(FuturesInstrument instrument) {
        stockCodeMap.put(instrument.getInstrumentId(), instrument);
        Map<String, FuturesInstrument> map = marketMap.get(instrument.getExchangeId());
        if (Objects.isNull(map)) {
            map = new ConcurrentHashMap<>();
            marketMap.put(instrument.getExchangeId(), map);
        }
        map.put(instrument.getInnerCode(), instrument);
    }

    @Override
    protected void delete(String id) {
        FuturesInstrument instrument = findById(id);
        if (Objects.nonNull(instrument)) {
            marketMap.get(instrument.getExchangeId()).remove(instrument.getInnerCode());
        }
    }

    @Override
    public IMybatisMapper<FuturesInstrument, String, FuturesInstrumentExample> getMapper() {
        return futuresInstrumentMapper;
    }

    @Override
    protected String genDbId() {
        return null;
    }
}
