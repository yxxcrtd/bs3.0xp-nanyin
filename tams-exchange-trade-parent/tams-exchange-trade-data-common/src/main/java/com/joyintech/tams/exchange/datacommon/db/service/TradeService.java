package com.joyintech.tams.exchange.datacommon.db.service;

import com.github.pagehelper.PageInfo;
import com.joyintech.tams.exchange.common.db.domain.Trade;
import com.joyintech.tams.exchange.datacommon.cache.AsyncService;

import java.util.List;

public interface TradeService extends AsyncService<String, Trade> {
    List<Trade> queryTradeByEntrust(String entrustId);

    List<Trade> queryTradeByOrder(String orderId);

    List<Trade> queryTradeByInstructionId(String instructionId);

    List<Trade> queryTradeByAccountId(String accountId);

    Trade queryTradeByAccountIdAndBrokerTradeId(String accountId, String brokerTradeId);

    String genDBId();

    PageInfo<Trade> queryAllForPage(int pageNum, int pageSize, String userId, String accountId, String market, String stockCode, String entrustProp, List<String> ptlAuthList);

    void clearSet(String date);

    void fillPlatDate(String date);
}
