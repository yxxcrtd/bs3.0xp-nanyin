package com.joyintech.tams.exchange.datacommon.db.mapper;

import com.joyintech.tams.exchange.common.db.domain.MonitorHistory;
import com.joyintech.tams.exchange.common.db.domain.MonitorHistoryExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MonitorHistoryMapper extends IMybatisMapper<MonitorHistory, String, MonitorHistoryExample> {
    int countByExample(MonitorHistoryExample example);

    int deleteByExample(MonitorHistoryExample example);

    int insert(MonitorHistory record);

    int insertBatch(List<MonitorHistory> records);

    int insertSelective(MonitorHistory record);

    List<MonitorHistory> selectByExample(MonitorHistoryExample example);

    int updateByExampleSelective(@Param("record") MonitorHistory record, @Param("example") MonitorHistoryExample example);

    int updateByExample(@Param("record") MonitorHistory record, @Param("example") MonitorHistoryExample example);
}