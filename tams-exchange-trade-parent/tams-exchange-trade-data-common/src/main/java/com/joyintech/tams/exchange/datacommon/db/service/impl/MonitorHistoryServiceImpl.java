package com.joyintech.tams.exchange.datacommon.db.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.joyintech.tams.exchange.common.db.domain.MonitorHistory;
import com.joyintech.tams.exchange.common.db.domain.MonitorHistoryExample;
import com.joyintech.tams.exchange.datacommon.db.dao.MonitorHistoryMapper;
import com.joyintech.tams.exchange.datacommon.db.service.MonitorHistoryService;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName MonitorHistoryServiceImpl
 * @Description
 * @Author swwan
 * @Date 2020/10/22 9:54
 * @Version 1.0
 **/
@Service
public class MonitorHistoryServiceImpl implements MonitorHistoryService {

    @Autowired
    private MonitorHistoryMapper monitorHistoryMapper;
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;
    @Override
    public List<MonitorHistory> queryAll() {
        return monitorHistoryMapper.selectByExample(null);
    }

    @Override
    public void saveMonitorHistory(List<MonitorHistory> monitorHistories) {
        SqlSession sqlSession = sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH,false);
        MonitorHistoryMapper monitorHistoryMapper = sqlSession.getMapper(MonitorHistoryMapper.class);
        AtomicInteger index = new AtomicInteger(0);
        monitorHistories.forEach(monitorHistory -> {
            index.getAndIncrement();
            monitorHistoryMapper.insertSelective(monitorHistory);
            if (index.get() % 500 == 0){
                sqlSession.flushStatements();
            }
        });
        sqlSession.commit();
        sqlSession.close();
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
