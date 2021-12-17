package com.joyintech.tams.exchange.datacommon.db.service;

import com.joyintech.tams.exchange.common.db.domain.BatchInfoHistory;

import java.util.List;

public interface BatchInfoHistoryService {

    void saveBatchInfoHistories(List<BatchInfoHistory> batchInfoHistories);
}
