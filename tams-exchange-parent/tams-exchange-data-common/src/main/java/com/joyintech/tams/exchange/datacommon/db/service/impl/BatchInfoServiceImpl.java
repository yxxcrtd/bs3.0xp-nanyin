package com.joyintech.tams.exchange.datacommon.db.service.impl;

import com.joyintech.tams.exchange.common.db.domain.BatchInfo;
import com.joyintech.tams.exchange.common.db.domain.BatchInfoExample;
import com.joyintech.tams.exchange.datacommon.cache.impl.AsyncServiceImpl;
import com.joyintech.tams.exchange.datacommon.db.mapper.BatchInfoMapper;
import com.joyintech.tams.exchange.datacommon.db.mapper.IMybatisMapper;
import com.joyintech.tams.exchange.datacommon.db.service.BatchInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class BatchInfoServiceImpl extends AsyncServiceImpl<String, BatchInfo> implements BatchInfoService {

    @Autowired
    private BatchInfoMapper batchInfoMapper;

    private Map<String, BatchInfo> orderIdMap = new ConcurrentHashMap<>();

    @Override
    public void clearCache() {
        orderIdMap.clear();
        memory_cache.clear();
    }

    @Override
    protected void update(BatchInfo batchInfo) {
        orderIdMap.put(batchInfo.getOrderId(), batchInfo);
    }

    @Override
    protected void save(BatchInfo batchInfo) {
        update(batchInfo);
    }

    @Override
    protected void delete(String PK) {
        BatchInfo batchInfo = findById(PK);
        if (Objects.nonNull(batchInfo)) {
            orderIdMap.remove(batchInfo.getOrderId());
        }
    }

    @Override
    public IMybatisMapper<BatchInfo, String, BatchInfoExample> getMapper() {
        return batchInfoMapper;
    }

    @Override
    public BatchInfo findByOrderId(String orderId) {
        return orderIdMap.get(orderId);
    }
}
