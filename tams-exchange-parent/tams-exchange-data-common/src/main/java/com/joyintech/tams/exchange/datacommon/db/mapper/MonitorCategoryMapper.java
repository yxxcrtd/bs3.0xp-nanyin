package com.joyintech.tams.exchange.datacommon.db.mapper;

import com.joyintech.tams.exchange.common.db.domain.MonitorCategory;
import com.joyintech.tams.exchange.common.db.domain.MonitorCategoryExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MonitorCategoryMapper extends IMybatisMapper<MonitorCategory, String, MonitorCategoryExample> {
    int countByExample(MonitorCategoryExample example);

    int deleteByExample(MonitorCategoryExample example);

    int deleteByPrimaryKey(String id);

    int insert(MonitorCategory record);

    int insertBatch(List<MonitorCategory> records);

    int insertSelective(MonitorCategory record);

    List<MonitorCategory> selectByExample(MonitorCategoryExample example);

    MonitorCategory selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") MonitorCategory record, @Param("example") MonitorCategoryExample example);

    int updateByExample(@Param("record") MonitorCategory record, @Param("example") MonitorCategoryExample example);

    int updateByPrimaryKeySelective(MonitorCategory record);

    int updateByPrimaryKey(MonitorCategory record);
}