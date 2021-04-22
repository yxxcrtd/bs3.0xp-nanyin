package com.joyintech.tams.exchange.datacommon.db.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Strings;
import com.joyintech.tams.exchange.common.constant.InvestmentAssetType;
import com.joyintech.tams.exchange.common.constant.TradeType;
import com.joyintech.tams.exchange.common.db.domain.Trade;
import com.joyintech.tams.exchange.common.db.domain.TradeExample;
import com.joyintech.tams.exchange.common.utils.StockCodeUtils;
import com.joyintech.tams.exchange.datacommon.cache.impl.AsyncServiceImpl;
import com.joyintech.tams.exchange.datacommon.db.mapper.IMybatisMapper;
import com.joyintech.tams.exchange.datacommon.db.mapper.TradeMapper;
import com.joyintech.tams.exchange.datacommon.db.service.TradeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class TradeServiceImpl extends AsyncServiceImpl<String, Trade> implements TradeService {
    private static final Logger logger = LoggerFactory.getLogger(TradeServiceImpl.class);

    @Autowired
    private TradeMapper tradeMapper;
    private Map<String, Map<String, Trade>> orderIdMap = new ConcurrentHashMap<>();
    private Map<String, Map<String, Trade>> entrustIdMap = new ConcurrentHashMap<>();
    private Map<String, Map<String, Trade>> instructionIdMap = new ConcurrentHashMap<>();
    private Map<String, Map<String, Trade>> accountIdMap = new ConcurrentHashMap<>();
    private String tradingDay;

    private Set<String> tradeIdSet = new HashSet<>();

    @Override
    public void clearSet(String date) {
        tradeIdSet.clear();
        fillPlatDate(date);
    }

    @Override
    public void fillPlatDate(String date) {
        tradingDay = date;
        logger.info("trade tradingDay is {}", tradingDay);
    }

    /**
     * 根据传入的Trade行数据的LocalTradeId与BrokerTradeId在Trade中查询
     */
    @Override
    public List<Trade> queryTradeByEntrust(String entrustId) {
        return new ArrayList<>(entrustIdMap.getOrDefault(entrustId, new HashMap<>()).values());
    }

    /**
     * 根据传入的Order表行数据的主键在Trade表中以ParentOrderId字段查询
     */
    @Override
    public List<Trade> queryTradeByOrder(String orderId) {
        return new ArrayList<>(orderIdMap.getOrDefault(orderId, new HashMap<>()).values());
    }

    @Override
    public List<Trade> queryTradeByInstructionId(String instructionId) {
        return new ArrayList<>(instructionIdMap.getOrDefault(instructionId, new HashMap<>()).values());
    }

    @Override
    public List<Trade> queryTradeByAccountId(String accountId) {
        ArrayList<Trade> trades = new ArrayList<>(accountIdMap.getOrDefault(accountId, new HashMap<>()).values());
        return trades.stream().filter(trade -> trade.getTradeType() == TradeType.NORMAL.value).collect(Collectors.toList());
    }

    @Override
    public Trade queryTradeByAccountIdAndBrokerTradeId(String accountId, String brokerTradeId) {
        Map<String, Trade> map = accountIdMap.get(accountId);
        if (Objects.nonNull(map)) {
            return map.get(tradingDay + "_" + brokerTradeId);
        }
        return null;
    }

    @Override
    public String genDBId() {
        return super.genDbId();
    }

    @Override
    public PageInfo<Trade> queryAllForPage(int pageNum, int pageSize, String userId, String accountId, String market, String stockCode, String entrustProp, List<String> ptlAuthList) {
        TradeExample tradeExample = new TradeExample();
        TradeExample.Criteria criteria = tradeExample.createCriteria();
        if (!Strings.isNullOrEmpty(accountId))
            criteria.andAccountIdEqualTo(accountId);
        if (!Strings.isNullOrEmpty(market))
            criteria.andMarketEqualTo(market);
        if (!Strings.isNullOrEmpty(stockCode))
            criteria.andStockCodeEqualTo(stockCode);
        if (!Strings.isNullOrEmpty(entrustProp))
            criteria.andEntrustPropEqualTo(entrustProp);
        if (Objects.nonNull(ptlAuthList) && ptlAuthList.size() > 0)
            criteria.andPortfolioIdIn(ptlAuthList);
        PageHelper.startPage(pageNum, pageSize);
        List<Trade> tradeHistories = tradeMapper.selectByExample(tradeExample);
        tradeHistories.forEach(tradeHistory -> {
            if (InvestmentAssetType.isFutures(tradeHistory.getAssetType())) {
                tradeHistory.setStockCode(StockCodeUtils.getStockCode(tradeHistory.getStockCode()));
            }
        });
        return new PageInfo<>(tradeHistories);
    }

    @Override
    public void clearCache() {
        memory_cache.clear();
        accountIdMap.clear();
        entrustIdMap.clear();
        orderIdMap.clear();
        instructionIdMap.clear();
        tradeIdSet.clear();
        tradeRAMCount.set(0);
    }

    private void updateTradeRAMCount(String id) {
        boolean isNew = tradeIdSet.add(id);
        if (isNew) {
            tradeRAMCount.incrementAndGet();
        }
    }

    @Override
    protected void update(Trade trade) {
        saveMemoryCache(trade);
        updateTradeRAMCount(trade.getId());
    }

    private void saveMemoryCache(Trade trade) {
        // 只缓存普通成交的数据
        if (trade.getTradeType() == TradeType.NORMAL.value) {
            Map<String, Trade> entrustMap = entrustIdMap.get(trade.getEntrustId());
            if (Objects.isNull(entrustMap)) {
                entrustMap = new ConcurrentHashMap<>();
                entrustIdMap.put(trade.getEntrustId(), entrustMap);
            }
            entrustMap.put(trade.getId(), trade);
            if (!Strings.isNullOrEmpty(trade.getParentOrderId())) {
                Map<String, Trade> orderMap = orderIdMap.get(trade.getParentOrderId());
                if (Objects.isNull(orderMap)) {
                    orderMap = new ConcurrentHashMap<>();
                    orderIdMap.put(trade.getParentOrderId(), orderMap);
                }
                orderMap.put(trade.getId(), trade);
            }
            if (!Strings.isNullOrEmpty(trade.getInstructionId())) {
                Map<String, Trade> instructionMap = instructionIdMap.get(trade.getInstructionId());
                if (Objects.isNull(instructionMap)) {
                    instructionMap = new ConcurrentHashMap<>();
                    instructionIdMap.put(trade.getInstructionId(), instructionMap);
                }
                instructionMap.put(trade.getId(), trade);
            }
        }
        Map<String, Trade> accountMap = accountIdMap.get(trade.getAccountId());
        if (Objects.isNull(accountMap)) {
            accountMap = new ConcurrentHashMap<>();
            accountIdMap.put(trade.getAccountId(), accountMap);
        }
        accountMap.put(trade.getTradingDay() + "_" + trade.getBrokerTradeId(), trade);
    }

    @Override
    protected void save(Trade trade) {
        update(trade);
    }

    @Override
    protected void delete(String id) {
        Trade trade = findById(id);
        if (Objects.nonNull(trade)) {
            deleteMemoryCache(trade);
            tradeRAMCount.decrementAndGet();
        }
    }

    private void deleteMemoryCache(Trade trade) {
        if (!Strings.isNullOrEmpty(trade.getParentOrderId())) {
            Map<String, Trade> map = orderIdMap.get(trade.getParentOrderId());
            if (Objects.nonNull(map))
                map.remove(trade.getId());
        }
        Map<String, Trade> map = entrustIdMap.get(trade.getEntrustId());
        if (Objects.nonNull(map))
            map.remove(trade.getId());
        if (!Strings.isNullOrEmpty(trade.getInstructionId())) {
            Map<String, Trade> map1 = instructionIdMap.get(trade.getInstructionId());
            if (Objects.nonNull(map1))
                map1.remove(trade.getId());
        }
        accountIdMap.get(trade.getAccountId()).remove(trade.getTradingDay() + "_" + trade.getBrokerTradeId());
    }

    @Override
    public IMybatisMapper<Trade, String, TradeExample> getMapper() {
        return tradeMapper;
    }
}
