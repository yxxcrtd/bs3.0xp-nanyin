package com.joyintech.tams.exchange.datacommon.db.service;

import com.github.pagehelper.PageInfo;
import com.joyintech.tams.exchange.common.bean.TradeQueryBean;
import com.joyintech.tams.exchange.common.db.domain.TradeHistory;
import com.joyintech.tams.exchange.common.request.PageTradeInfoRequest;

import java.util.List;

public interface TradeHistoryService {

    List<TradeHistory> queryAll();

    void saveTradeHistory(List<TradeHistory> tradeHistories);

    @Deprecated
    PageInfo<TradeHistory> queryAllForPage(int pageNum, int pageSize, String userId, String startDay, String endDay, String accountId, String market, String stockCode, String entrustProp, List<String> ptlAuthList);

    PageInfo<TradeQueryBean> queryAllForPage(PageTradeInfoRequest request, List<String> ptlAuthList);

    @Deprecated
    List<TradeHistory> queryTradeHistoryByEntrust(String entrustId);

    List<TradeQueryBean> queryTradeHistoryByEntrustId(String entrustId);

    String queryLastClearDate();
}
