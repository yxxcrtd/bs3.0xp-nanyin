package com.joyintech.tams.exchange.datacommon.db.service;

import com.joyintech.tams.exchange.common.db.domain.AlgorithmHistory;

import java.util.List;

public interface AlgorithmHistoryService {
    void save(List<AlgorithmHistory> algorithmHistoryList);

    String queryLastClearDate();
}
