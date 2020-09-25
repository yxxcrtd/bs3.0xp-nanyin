package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.StlOrders;
import org.springframework.stereotype.Repository;

@Repository
public interface StlOrdersMapper {
    int deleteByPrimaryKey(String orderId);

    int insert(StlOrders record);

    int insertSelective(StlOrders record);

    int insertBasicByBasicInfo(StlOrders record);

    StlOrders selectByPrimaryKey(String orderId);

    int updateByPrimaryKeySelective(StlOrders record);

    int updateByPrimaryKey(StlOrders record);
}