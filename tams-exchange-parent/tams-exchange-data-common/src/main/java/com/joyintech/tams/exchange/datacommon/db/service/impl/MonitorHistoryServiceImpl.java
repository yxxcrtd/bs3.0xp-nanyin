package com.joyintech.tams.exchange.datacommon.db.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.joyintech.tams.exchange.common.db.domain.MonitorHistory;
import com.joyintech.tams.exchange.common.db.domain.MonitorHistoryExample;
import com.joyintech.tams.exchange.datacommon.db.mapper.MonitorHistoryMapper;
import com.joyintech.tams.exchange.datacommon.db.service.MonitorHistoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName MonitorHistoryServiceImpl
 * @Description
 * @Author swwan
 * @Date 2020/10/22 9:54
 * @Version 1.0
 **/
@Service
public class MonitorHistoryServiceImpl implements MonitorHistoryService {

    private static final Logger logger = LoggerFactory.getLogger(MonitorHistoryServiceImpl.class);

    @Autowired
    private MonitorHistoryMapper monitorHistoryMapper;

    @Override
    public List<MonitorHistory> queryAll() {
        return monitorHistoryMapper.selectByExample(null);
    }

    @Override
    public void saveMonitorHistory(List<MonitorHistory> monitorHistories) {
        monitorHistoryMapper.insertBatch(monitorHistories);
    }

    @Override
    public PageInfo<MonitorHistory> queryAllForPage(int pageNum, int pageSize) {
        MonitorHistoryExample monitorHistoryExample = new MonitorHistoryExample();
        MonitorHistoryExample.Criteria criteria = monitorHistoryExample.createCriteria();
        PageHelper.startPage(pageNum, pageSize);
        List<MonitorHistory> monitorHistories = monitorHistoryMapper.selectByExample(monitorHistoryExample);
        PageInfo<MonitorHistory> pageInfo = new PageInfo<>(monitorHistories);
        return pageInfo;
    }
}
