package com.joyintech.tams.exchange.datacommon.db.service;

import com.github.pagehelper.PageInfo;
import com.joyintech.tams.exchange.common.db.domain.EntrustHistory;

import java.util.List;

public interface EntrustHistoryService {

    List<EntrustHistory> queryAll();

    void saveEntrustHistory(List<EntrustHistory> entrustHistories);

    PageInfo<EntrustHistory> queryAllForPage(int pageNum, int pageSize, String startDay, String endDay, String accountId, String portfolioId, String market, String stockCode, String entrustProp, int entrustStatus);

    String queryLastClearDate();
}
