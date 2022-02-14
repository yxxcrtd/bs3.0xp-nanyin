package com.joyintech.tams.exchange.datacommon.db.service;

import com.joyintech.tams.exchange.common.db.domain.OrderHistory;

import java.util.List;

public interface OrderHistoryService {

    List<OrderHistory> queryAll();

    void saveOrderHistory(List<OrderHistory> orderHistories);

    String queryLastClearDate();
}
