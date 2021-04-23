package com.joyintech.tams.exchange.datacommon.db.service;

import com.joyintech.tams.exchange.datacommon.cache.AsyncService;
import com.joyintech.tams.exchange.common.db.domain.Order;

import java.util.List;

public interface OrderService extends AsyncService<String, Order> {
    List<Order> queryOrder(String userId);

    List<Order> queryOrderByInstructionId(String instructionId);

    Order queryOrderByAlgorithmId(String algorithmId);

    String genDbId();

    void clearSet();
}
