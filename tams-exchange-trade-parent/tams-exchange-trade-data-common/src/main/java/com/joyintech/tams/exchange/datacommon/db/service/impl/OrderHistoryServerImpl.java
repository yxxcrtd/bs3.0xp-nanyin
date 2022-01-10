package com.joyintech.tams.exchange.datacommon.db.service.impl;

import com.joyintech.tams.exchange.common.db.domain.OrderHistory;
import com.joyintech.tams.exchange.datacommon.db.dao.OrderHistoryMapper;
import com.joyintech.tams.exchange.datacommon.db.service.OrderHistoryService;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class OrderHistoryServerImpl implements OrderHistoryService {

    @Autowired
    private OrderHistoryMapper orderHistoryMapper;
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;
    @Override
    public List<OrderHistory> queryAll() {
        return orderHistoryMapper.selectByExample(null);
    }

    @Override
    public void saveOrderHistory(List<OrderHistory> orderHistories) {
        SqlSession sqlSession = sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH,false);
        OrderHistoryMapper orderHistoryMapper = sqlSession.getMapper(OrderHistoryMapper.class);
        AtomicInteger index = new AtomicInteger(0);
        orderHistories.forEach(orderHistory -> {
            index.getAndIncrement();
            orderHistoryMapper.insertSelective(orderHistory);
            if (index.get() % 500 == 0){
                sqlSession.flushStatements();
            }
        });
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public String queryLastClearDate() {
        String lastClearDate = orderHistoryMapper.queryLastClearDate();
        return lastClearDate == null ? "" : lastClearDate;
    }
}
