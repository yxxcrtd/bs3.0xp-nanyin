package com.joyintech.tams.exchange.datacommon.db.service.impl;

import com.joyintech.tams.exchange.common.db.domain.OrderHistory;
import com.joyintech.tams.exchange.datacommon.db.mapper.OrderHistoryMapper;
import com.joyintech.tams.exchange.datacommon.db.service.OrderHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderHistoryServerImpl implements OrderHistoryService {

    @Autowired
    private OrderHistoryMapper orderHistoryMapper;

    @Override
    public List<OrderHistory> queryAll() {
        return orderHistoryMapper.selectByExample(null);
    }

    @Override
    public void saveOrderHistory(List<OrderHistory> orderHistories) {
        orderHistoryMapper.insertBatch(orderHistories);
    }

    @Override
    public String queryLastClearDate() {
        String lastClearDate = orderHistoryMapper.queryLastClearDate();
        return lastClearDate == null ? "" : lastClearDate;
    }
}
