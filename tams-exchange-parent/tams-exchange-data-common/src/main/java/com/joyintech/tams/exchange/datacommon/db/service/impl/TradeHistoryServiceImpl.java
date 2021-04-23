package com.joyintech.tams.exchange.datacommon.db.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Strings;
import com.joyintech.tams.exchange.common.constant.InvestmentAssetType;
import com.joyintech.tams.exchange.common.db.domain.TradeHistory;
import com.joyintech.tams.exchange.common.db.domain.TradeHistoryExample;
import com.joyintech.tams.exchange.common.utils.StockCodeUtils;
import com.joyintech.tams.exchange.datacommon.db.mapper.TradeHistoryMapper;
import com.joyintech.tams.exchange.datacommon.db.service.TradeHistoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class TradeHistoryServiceImpl implements TradeHistoryService {
    private static final Logger logger = LoggerFactory.getLogger(EntrustHistoryServerImpl.class);

    @Autowired
    private TradeHistoryMapper tradeHistoryMapper;

    @Override
    public List<TradeHistory> queryAll() {
        return tradeHistoryMapper.selectByExample(null);
    }

    @Override
    public void saveTradeHistory(List<TradeHistory> tradeHistories) {
        tradeHistoryMapper.insertBatch(tradeHistories);
    }

    @Override
    public PageInfo<TradeHistory> queryAllForPage(int pageNum, int pageSize, String userId, String startDay, String endDay, String accountId, String market, String stockCode, String entrustProp, List<String> ptlAuthList) {
        TradeHistoryExample tradeHistoryExample = new TradeHistoryExample();
        TradeHistoryExample.Criteria criteria = tradeHistoryExample.createCriteria();
        if (!Strings.isNullOrEmpty(startDay))
            criteria.andTradingDayGreaterThanOrEqualTo(startDay);
        if (!Strings.isNullOrEmpty(endDay))
            criteria.andTradingDayLessThanOrEqualTo(endDay);
        if (!Strings.isNullOrEmpty(accountId))
            criteria.andAccountIdEqualTo(accountId);
        if (!Strings.isNullOrEmpty(market))
            criteria.andMarketEqualTo(market);
        if (!Strings.isNullOrEmpty(stockCode))
            criteria.andStockCodeEqualTo(stockCode);
        if (!Strings.isNullOrEmpty(entrustProp))
            criteria.andEntrustPropEqualTo(entrustProp);
        if (Objects.nonNull(ptlAuthList) && ptlAuthList.size() > 0) {
            criteria.andPortfolioIdIn(ptlAuthList);
        }
        PageHelper.startPage(pageNum, pageSize);
        List<TradeHistory> tradeHistories = tradeHistoryMapper.selectByExample(tradeHistoryExample);
        tradeHistories.forEach(tradeHistory -> {
            if (InvestmentAssetType.isFutures(tradeHistory.getAssetType())) {
                tradeHistory.setStockCode(StockCodeUtils.getStockCode(tradeHistory.getStockCode()));
            }
        });
        return new PageInfo<>(tradeHistories);
    }

    @Override
    public List<TradeHistory> queryTradeHistoryByEntrust(String entrustId) {
        TradeHistoryExample tradeHistoryExample = new TradeHistoryExample();
        TradeHistoryExample.Criteria criteria = tradeHistoryExample.createCriteria();
        criteria.andEntrustIdEqualTo(entrustId);
        return tradeHistoryMapper.selectByExample(tradeHistoryExample);
    }

    @Override
    public String queryLastClearDate() {
        String lastClearDate = tradeHistoryMapper.queryLastClearDate();
        return lastClearDate == null ? "" : lastClearDate;
    }
}
