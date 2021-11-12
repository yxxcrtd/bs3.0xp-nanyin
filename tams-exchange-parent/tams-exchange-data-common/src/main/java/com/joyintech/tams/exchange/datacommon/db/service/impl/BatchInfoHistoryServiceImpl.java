package com.joyintech.tams.exchange.datacommon.db.service.impl;

import com.joyintech.tams.exchange.common.db.domain.BatchInfoHistory;
import com.joyintech.tams.exchange.datacommon.db.dao.BatchInfoHistoryMapper;
import com.joyintech.tams.exchange.datacommon.db.service.BatchInfoHistoryService;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class BatchInfoHistoryServiceImpl implements BatchInfoHistoryService {

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Override
    public void saveBatchInfoHistories(List<BatchInfoHistory> batchInfoHistories) {
        SqlSession sqlSession = sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH,false);
        BatchInfoHistoryMapper batchInfoHistoryMapper = sqlSession.getMapper(BatchInfoHistoryMapper.class);
        AtomicInteger index = new AtomicInteger(0);
        batchInfoHistories.forEach(batchInfoHistory -> {
            index.getAndIncrement();
            batchInfoHistoryMapper.insertSelective(batchInfoHistory);
            if (index.get() % 500 == 0){
                sqlSession.flushStatements();
            }
        });
        sqlSession.commit();
        sqlSession.close();
    }
}
