package com.joyintech.tams.exchange.datacommon.db.service.impl;

import com.joyintech.tams.exchange.common.db.domain.AlgorithmHistory;
import com.joyintech.tams.exchange.datacommon.db.dao.AlgorithmHistoryMapper;
import com.joyintech.tams.exchange.datacommon.db.service.AlgorithmHistoryService;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class AlgorithmHistoryServiceImpl implements AlgorithmHistoryService {
    @Autowired
    private AlgorithmHistoryMapper algorithmHistoryMapper;

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Override
    public void save(List<AlgorithmHistory> algorithmHistoryList) {
        SqlSession sqlSession = sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH,false);
        AlgorithmHistoryMapper algorithmHistoryMapper = sqlSession.getMapper(AlgorithmHistoryMapper.class);
        AtomicInteger index = new AtomicInteger(0);
        algorithmHistoryList.forEach(algorithmHistory -> {
            index.getAndIncrement();
            algorithmHistoryMapper.insertSelective(algorithmHistory);
            if (index.get() % 500 == 0){
                sqlSession.flushStatements();
            }
        });
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public String queryLastClearDate() {
        return algorithmHistoryMapper.queryLastClearDate();
    }
}
