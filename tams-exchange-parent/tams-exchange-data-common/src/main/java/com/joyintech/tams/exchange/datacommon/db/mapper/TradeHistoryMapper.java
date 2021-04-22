package com.joyintech.tams.exchange.datacommon.db.mapper;

import com.joyintech.tams.exchange.common.db.domain.TradeHistory;
import com.joyintech.tams.exchange.common.db.domain.TradeHistoryExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TradeHistoryMapper extends IMybatisMapper<TradeHistory, String, TradeHistoryExample> {
    long countByExample(TradeHistoryExample example);

    int deleteByExample(TradeHistoryExample example);

    int insert(TradeHistory record);

    int insertBatch(List<TradeHistory> records);

    int insertSelective(TradeHistory record);

    List<TradeHistory> selectByExample(TradeHistoryExample example);

    int updateByExampleSelective(@Param("record") TradeHistory record, @Param("example") TradeHistoryExample example);

    int updateByExample(@Param("record") TradeHistory record, @Param("example") TradeHistoryExample example);

    int updateByPrimaryKey(TradeHistory record);

    //允许默认值
    int updateByPrimaryKeySelective(TradeHistory record);

    int deleteByPrimaryKey(String id);

    String queryLastClearDate();
}