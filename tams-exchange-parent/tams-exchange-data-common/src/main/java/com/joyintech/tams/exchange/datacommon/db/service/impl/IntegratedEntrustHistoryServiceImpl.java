package com.joyintech.tams.exchange.datacommon.db.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Strings;
import com.joyintech.tams.exchange.common.bean.EntrustQryBean;
import com.joyintech.tams.exchange.common.constant.InvestmentAssetType;
import com.joyintech.tams.exchange.common.db.domain.IntegratedEntrustHistory;
import com.joyintech.tams.exchange.common.db.domain.IntegratedEntrustHistoryExample;
import com.joyintech.tams.exchange.common.utils.StockCodeUtils;
import com.joyintech.tams.exchange.datacommon.db.mapper.IntegratedEntrustHistoryMapper;
import com.joyintech.tams.exchange.datacommon.db.service.IntegratedEntrustHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class IntegratedEntrustHistoryServiceImpl implements IntegratedEntrustHistoryService {

    @Autowired
    private IntegratedEntrustHistoryMapper integratedEntrustHistoryMapper;

    @Override
    public List<IntegratedEntrustHistory> queryAll() {
        return integratedEntrustHistoryMapper.selectByExample(null);
    }

    @Override
    public void saveIntegratedEntrustHistory(List<IntegratedEntrustHistory> integratedEntrustHistories) {
        integratedEntrustHistoryMapper.insertBatch(integratedEntrustHistories);
    }

    @Override
    public PageInfo<IntegratedEntrustHistory> queryAllForPage(int pageNum, int pageSize, String userId, String startDay, String endDay, String accountId, String portfolioId, String market, String stockCode, String entrustProp, int entrustStatus, List<String> ptlAuthList) {
        IntegratedEntrustHistoryExample integratedEntrustHistoryExample = new IntegratedEntrustHistoryExample();
        IntegratedEntrustHistoryExample.Criteria criteria = integratedEntrustHistoryExample.createCriteria();
        if (!Strings.isNullOrEmpty(userId))
            criteria.andUserIdEqualTo(userId);
        if (!Strings.isNullOrEmpty(startDay))
            criteria.andTradingDayGreaterThanOrEqualTo(startDay);
        if (!Strings.isNullOrEmpty(endDay))
            criteria.andTradingDayLessThanOrEqualTo(endDay);
        if (!Strings.isNullOrEmpty(accountId))
            criteria.andAccountIdEqualTo(accountId);
        if (!Strings.isNullOrEmpty(portfolioId))
            criteria.andPortfolioIdEqualTo(portfolioId);
        if (Strings.isNullOrEmpty(portfolioId) && Objects.nonNull(ptlAuthList) && ptlAuthList.size() > 0)
            criteria.andPortfolioIdIn(ptlAuthList);
        if (!Strings.isNullOrEmpty(market))
            criteria.andMarketEqualTo(market);
        if (!Strings.isNullOrEmpty(stockCode))
            criteria.andStockCodeEqualTo(stockCode);
        if (!Strings.isNullOrEmpty(entrustProp))
            criteria.andEntrustPropEqualTo(entrustProp);
        if (entrustStatus > -100)
            criteria.andEntrustStatusEqualTo(entrustStatus);
        PageHelper.startPage(pageNum, pageSize);
        List<IntegratedEntrustHistory> integratedEntrustHistoryList = integratedEntrustHistoryMapper.selectByExample(integratedEntrustHistoryExample);
        return new PageInfo<>(integratedEntrustHistoryList);
    }

    @Override
    public PageInfo<IntegratedEntrustHistory> queryAllEntrustForPage(int pageNum, int pageSize, String userId, String startDay, String endDay, String accountId, String portfolioId, String market, String stockCode, String entrustProp, int entrustStatus, List<String> ptlAuthList) {
        PageHelper.startPage(pageNum, pageSize);
        EntrustQryBean entrustQryBean = new EntrustQryBean(userId, startDay, endDay, accountId, "", portfolioId, market, stockCode, entrustProp, entrustStatus, ptlAuthList);
        List<IntegratedEntrustHistory> integratedEntrustHistoryList = integratedEntrustHistoryMapper.selectEntrustsPageInfo(entrustQryBean);
        integratedEntrustHistoryList.forEach(integratedEntrustHistory -> {
            if (InvestmentAssetType.isFutures(integratedEntrustHistory.getAssetType())) {
                integratedEntrustHistory.setStockCode(StockCodeUtils.getStockCode(integratedEntrustHistory.getStockCode()));
            }
        });
        return new PageInfo<>(integratedEntrustHistoryList);
    }

    @Override
    public String queryLastClearDate() {
        String lastClearDate = integratedEntrustHistoryMapper.queryLastClearDate();
        return lastClearDate == null ? "" : lastClearDate;
    }
}
