package com.joyintech.tams.exchange.datacommon.db.mapper;

import com.joyintech.tams.exchange.common.db.domain.Order;
import com.joyintech.tams.exchange.common.db.domain.OrderExample;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderMapper extends IMybatisMapper<Order, String, OrderExample> {
    int countByExample(OrderExample example);

    int deleteByExample(OrderExample example);

    int deleteByPrimaryKey(String id);

    int deleteBatchByPrimaryKey(List<String> ids);

    int insert(Order record);

    int insertSelective(Order record);

    int updateOrInsertSelective(Order record);

    List<Order> selectByExample(OrderExample example);

    Order selectByPrimaryKey(String id);

    int updateByExampleSelective(Order record, OrderExample example);

    int updateByExample(Order record, OrderExample example);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);
}