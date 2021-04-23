package com.joyintech.tams.exchange.datacommon.db.service;

import com.github.pagehelper.PageInfo;
import com.joyintech.tams.exchange.common.db.domain.MonitorHistory;
import com.joyintech.tams.exchange.common.db.domain.TradeHistory;

import java.util.List;

public interface MonitorHistoryService {
    List<MonitorHistory> queryAll();

    void saveMonitorHistory(List<MonitorHistory> monitorHistories);

    PageInfo<MonitorHistory> queryAllForPage(int pageNum, int pageSize);
}
