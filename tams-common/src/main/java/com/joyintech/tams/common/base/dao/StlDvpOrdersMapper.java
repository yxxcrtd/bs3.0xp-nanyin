package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.StlDvpOrders;
import org.springframework.stereotype.Repository;

@Repository
public interface StlDvpOrdersMapper {
    int deleteByPrimaryKey(String orderId);

    int insert(StlDvpOrders record);

    int insertSelective(StlDvpOrders record);

    int insertBasicByBasicInfo(StlDvpOrders record);

    StlDvpOrders selectByPrimaryKey(String orderId);

    int updateByPrimaryKeySelective(StlDvpOrders record);

    int updateByPrimaryKey(StlDvpOrders record);
}