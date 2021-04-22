package com.joyintech.tams.exchange.datacommon.db.service.impl;

import com.joyintech.tams.exchange.common.db.domain.AlgorithmHistory;
import com.joyintech.tams.exchange.datacommon.db.mapper.AlgorithmHistoryMapper;
import com.joyintech.tams.exchange.datacommon.db.service.AlgorithmHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AlgorithmHistoryServiceImpl implements AlgorithmHistoryService {
    @Autowired
    private AlgorithmHistoryMapper algorithmHistoryMapper;

    @Override
    public void save(List<AlgorithmHistory> algorithmHistoryList) {
        algorithmHistoryMapper.insertBatch(algorithmHistoryList);
    }

    @Override
    public String queryLastClearDate() {
        return algorithmHistoryMapper.queryLastClearDate();
    }
}
