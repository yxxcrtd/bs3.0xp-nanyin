package com.joyintech.tams.exchange.datacommon.db.service.impl;

import com.joyintech.tams.exchange.common.db.domain.Monitor;
import com.joyintech.tams.exchange.common.db.domain.MonitorExample;
import com.joyintech.tams.exchange.datacommon.cache.impl.AsyncServiceImpl;
import com.joyintech.tams.exchange.datacommon.db.mapper.IMybatisMapper;
import com.joyintech.tams.exchange.datacommon.db.mapper.MonitorMapper;
import com.joyintech.tams.exchange.datacommon.db.service.MonitorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Objects;
import java.util.TimeZone;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName MonitorServiceImpl
 * @Description
 * @Author swwan
 * @Date 2020/10/22 9:54
 * @Version 1.0
 **/
@Service
public class MonitorServiceImpl extends AsyncServiceImpl<String, Monitor> implements MonitorService {
    private static final Logger logger = LoggerFactory.getLogger(MonitorServiceImpl.class);
    private static int timeZoneOffset = 0;

    static {
        timeZoneOffset = TimeZone.getDefault().getRawOffset();
    }

    @Autowired
    private MonitorMapper monitorMapper;
    private final AtomicInteger localOffset = new AtomicInteger(0);

    @Override
    public IMybatisMapper<Monitor, String, MonitorExample> getMapper() {
        return monitorMapper;
    }


    @Override
    public void clearCache() {
        memory_cache.clear();
    }

    @Override
    protected void update(Monitor monitor) {

    }

    @Override
    protected void save(Monitor monitor) {
        update(monitor);
    }

    @Override
    protected void delete(String id) {

    }

    @Override
    public List<Monitor> findAll() {
        return monitorMapper.selectByExample(null);
    }

    @Override
    public void insert(Monitor monitor) {
        genId(monitor);
        monitor.setCreateTime(new Date(System.currentTimeMillis()));
        asyncUpdate(monitor);
    }

    @Override
    public void insert(String timeStr, String monitorType, int monitorValue) {
        Monitor monitor = new Monitor();
        monitor.setRecordTime(timeStr);
        monitor.setRecordName(monitorType);
        monitor.setRecordValue(monitorValue);
        insert(monitor);
    }

    private String genId(Monitor monitor) {
        int tempOffSet = localOffset.incrementAndGet();
        if (tempOffSet > 9999) {
            tempOffSet = 0;
            localOffset.set(0);
        }
        long beginMill = System.currentTimeMillis() / (1000 * 3600 * 24) * (1000 * 3600 * 24) - timeZoneOffset;
        String localId = String.format("%s%04d", (System.currentTimeMillis() - beginMill) / 1000, tempOffSet);
        monitor.setId(localId);
        return localId;
    }

    @Override
    public List<Monitor> queryByRecordNameAndSnapShotTime(List<String> list, java.util.Date time) {
        MonitorExample monitorExample = new MonitorExample();
        monitorExample.setOrderByClause("record_time ASC");
        monitorExample.createCriteria()
                .andRecordNameIn(list)
                .andCreateTimeGreaterThanOrEqualTo(time);
        return monitorMapper.selectByExample(monitorExample);
    }
}
