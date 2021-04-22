package com.joyintech.tams.exchange.datacommon.db.service.impl;

import com.google.common.base.Strings;
import com.joyintech.tams.exchange.common.db.domain.Order;
import com.joyintech.tams.exchange.common.db.domain.OrderExample;
import com.joyintech.tams.exchange.datacommon.cache.impl.AsyncServiceImpl;
import com.joyintech.tams.exchange.datacommon.db.mapper.IMybatisMapper;
import com.joyintech.tams.exchange.datacommon.db.mapper.OrderMapper;
import com.joyintech.tams.exchange.datacommon.db.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class OrderServiceImpl extends AsyncServiceImpl<String, Order> implements OrderService {
    @Autowired
    private OrderMapper orderMapper;

    private Map<String, Map<String, Order>> userIdMap = new ConcurrentHashMap<>();
    private Map<String, Map<String, Order>> instructionIdMap = new ConcurrentHashMap<>();
    private Map<String, Order> algorithmMap = new ConcurrentHashMap<>();

    private Set<String> orderIdSet = new HashSet<>();

    @Override
    public void clearSet() {
        orderIdSet.clear();
    }

    /**
     * 在oracle数据库Order表中根据userId查询
     */
    @Override
    public List<Order> queryOrder(String userId) {
        return new ArrayList<>(userIdMap.getOrDefault(userId, new HashMap<>()).values());
    }

    @Override
    public List<Order> queryOrderByInstructionId(String instructionId) {
        return new ArrayList<>(instructionIdMap.getOrDefault(instructionId, new HashMap<>()).values());
    }

    @Override
    public Order queryOrderByAlgorithmId(String algorithmId) {
        return algorithmMap.get(algorithmId);
    }

    @Override
    public String genDbId() {
        //1s处理不能超过3w条
        short tempOffSet = (short) Math.abs(offset.incrementAndGet());
        if (tempOffSet < 0) {
            tempOffSet = 0;
            offset.set(0);
        }
        Calendar ca = Calendar.getInstance();
        return String.format("%s%s%05d", "o", formatThreadLocal.get().format(ca.getTime()), tempOffSet);
    }

    @Override
    public void clearCache() {
        memory_cache.clear();
        userIdMap.clear();
        instructionIdMap.clear();
        algorithmMap.clear();
        orderIdSet.clear();
        orderRAMCount.set(0);
    }

    private void updateOrderRAMCount(String id) {
        boolean isNew = orderIdSet.add(id);
        if (isNew) {
            orderRAMCount.incrementAndGet();
        }
    }

    @Override
    protected void update(Order order) {
        saveMemoryCache(order);
        updateOrderRAMCount(order.getId());
    }

    private void saveMemoryCache(Order order) {
        Map<String, Order> userMap = userIdMap.get(order.getUserId());
        if (Objects.isNull(userMap)) {
            userMap = new ConcurrentHashMap<>();
            userIdMap.put(order.getUserId(), userMap);
        }
        userMap.put(order.getId(), order);

        if (!Strings.isNullOrEmpty(order.getInstructionId())) {
            Map<String, Order> instructionMap = instructionIdMap.get(order.getInstructionId());
            if (Objects.isNull(instructionMap)) {
                instructionMap = new ConcurrentHashMap<>();
                instructionIdMap.put(order.getInstructionId(), instructionMap);
            }
            instructionMap.put(order.getId(), order);
        }
        if (!Strings.isNullOrEmpty(order.getAlgorithmId())) {
            algorithmMap.put(order.getAlgorithmId(), order);
        }
    }

    @Override
    protected void save(Order order) {
        update(order);
    }

    @Override
    protected void delete(String id) {
        Order order = memory_cache.get(id);
        if (Objects.nonNull(order)) {
            deleteMemoryCache(order);
            orderRAMCount.decrementAndGet();
        }
    }

    private void deleteMemoryCache(Order order) {
        userIdMap.get(order.getUserId()).remove(order.getId());
        if (!Strings.isNullOrEmpty(order.getInstructionId()))
            instructionIdMap.get(order.getInstructionId()).remove(order.getId());
        if (!Strings.isNullOrEmpty(order.getAlgorithmId())) algorithmMap.remove(order.getAlgorithmId());
    }

    @Override
    public IMybatisMapper<Order, String, OrderExample> getMapper() {
        return orderMapper;
    }
}
