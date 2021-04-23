package com.joyintech.tams.exchange.datacommon.db.service;

import com.github.pagehelper.PageInfo;
import com.joyintech.tams.exchange.common.db.domain.TradeHistory;

import java.util.List;

public interface TradeHistoryService {

    List<TradeHistory> queryAll();

    void saveTradeHistory(List<TradeHistory> tradeHistories);

    PageInfo<TradeHistory> queryAllForPage(int pageNum, int pageSize, String userId, String startDay, String endDay, String accountId, String market, String stockCode, String entrustProp, List<String> ptlAuthList);

    List<TradeHistory> queryTradeHistoryByEntrust(String entrustId);

    String queryLastClearDate();
}
