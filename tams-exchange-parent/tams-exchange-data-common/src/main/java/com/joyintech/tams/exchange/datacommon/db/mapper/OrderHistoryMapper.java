package com.joyintech.tams.exchange.datacommon.db.mapper;

import com.joyintech.tams.exchange.common.db.domain.OrderHistory;
import com.joyintech.tams.exchange.common.db.domain.OrderHistoryExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderHistoryMapper extends IMybatisMapper<OrderHistory, String, OrderHistoryExample> {
    int countByExample(OrderHistoryExample example);

    int deleteByExample(OrderHistoryExample example);

    int deleteByPrimaryKey(String id);

    int insert(OrderHistory record);

    int insertBatch(List<OrderHistory> records);

    int insertSelective(OrderHistory record);

    List<OrderHistory> selectByExample(OrderHistoryExample example);

    OrderHistory selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") OrderHistory record, @Param("example") OrderHistoryExample example);

    int updateByExample(@Param("record") OrderHistory record, @Param("example") OrderHistoryExample example);

    int updateByPrimaryKeySelective(OrderHistory record);

    int updateByPrimaryKey(OrderHistory record);

    String queryLastClearDate();
}