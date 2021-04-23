package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.StlPayTempOrders;
import org.springframework.stereotype.Repository;

@Repository
public interface StlPayTempOrdersMapper {
    int deleteByPrimaryKey(String orderId);

    int insert(StlPayTempOrders record);

    int insertSelective(StlPayTempOrders record);

    int insertBasicByBasicInfo(StlPayTempOrders record);

    StlPayTempOrders selectByPrimaryKey(String orderId);

    int updateByPrimaryKeySelective(StlPayTempOrders record);

    int updateByPrimaryKey(StlPayTempOrders record);
}