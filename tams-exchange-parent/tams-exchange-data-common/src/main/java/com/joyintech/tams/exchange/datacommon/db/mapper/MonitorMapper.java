package com.joyintech.tams.exchange.datacommon.db.mapper;

import com.joyintech.tams.exchange.common.db.domain.Monitor;
import com.joyintech.tams.exchange.common.db.domain.MonitorExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MonitorMapper extends IMybatisMapper<Monitor, String, MonitorExample> {
    int countByExample(MonitorExample example);

    int deleteByExample(MonitorExample example);

    int deleteBatchByPrimaryKey(List<String> ids);

    int insert(Monitor record);

    int insertSelective(Monitor record);

    int updateOrInsertSelective(Monitor record);

    List<Monitor> selectByExample(MonitorExample example);

    int updateByExampleSelective(@Param("record") Monitor record, @Param("example") MonitorExample example);

    int updateByExample(@Param("record") Monitor record, @Param("example") MonitorExample example);
}