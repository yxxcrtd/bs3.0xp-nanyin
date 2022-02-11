package com.joyintech.tams.exchange.datacommon.db.service;

import com.joyintech.tams.exchange.common.db.domain.Monitor;
import com.joyintech.tams.exchange.datacommon.cache.AsyncService;

import java.util.Date;
import java.util.List;

public interface MonitorService extends AsyncService<String, Monitor> {

    List<Monitor> findAll();

    void insert(Monitor monitor);

    void insert(String timeStr, String monitorType, int monitorValue);

    List<Monitor> queryByRecordNameAndSnapShotTime(List<String> list, Date time);
}
