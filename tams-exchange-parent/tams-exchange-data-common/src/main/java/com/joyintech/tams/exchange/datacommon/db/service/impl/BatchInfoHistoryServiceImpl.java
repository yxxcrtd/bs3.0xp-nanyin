package com.joyintech.tams.exchange.datacommon.db.service.impl;

import com.joyintech.tams.exchange.common.db.domain.BatchInfoHistory;
import com.joyintech.tams.exchange.datacommon.db.mapper.BatchInfoHistoryMapper;
import com.joyintech.tams.exchange.datacommon.db.service.BatchInfoHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BatchInfoHistoryServiceImpl implements BatchInfoHistoryService {

    @Autowired
    private BatchInfoHistoryMapper batchInfoHistoryMapper;

    @Override
    public void saveBatchInfoHistories(List<BatchInfoHistory> batchInfoHistories) {
        batchInfoHistoryMapper.insertBatch(batchInfoHistories);
    }
}
