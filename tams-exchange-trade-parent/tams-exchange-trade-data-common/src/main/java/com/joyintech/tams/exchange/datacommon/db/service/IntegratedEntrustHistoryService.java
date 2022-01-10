package com.joyintech.tams.exchange.datacommon.db.service;

import com.github.pagehelper.PageInfo;
import com.joyintech.tams.exchange.common.db.domain.IntegratedEntrustHistory;

import java.util.List;

public interface IntegratedEntrustHistoryService {

    List<IntegratedEntrustHistory> queryAll();

    void saveIntegratedEntrustHistory(List<IntegratedEntrustHistory> integratedEntrustHistories);

    PageInfo<IntegratedEntrustHistory> queryAllForPage(int pageNum, int pageSize, String userId, String startDay, String endDay, String accountId, String portfolioId, String market, String stockCode, String entrustProp, int entrustStatus, List<String> ptlAuthList);

    PageInfo<IntegratedEntrustHistory> queryAllEntrustForPage(int pageNum, int pageSize, String userId, String startDay, String endDay, String accountId, String portfolioId, String market, String stockCode, String entrustProp, int entrustStatus, List<String> ptlAuthList);

    String queryLastClearDate();
}
