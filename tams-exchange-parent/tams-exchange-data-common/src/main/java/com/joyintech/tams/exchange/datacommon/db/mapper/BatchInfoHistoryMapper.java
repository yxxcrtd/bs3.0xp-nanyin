package com.joyintech.tams.exchange.datacommon.db.mapper;

import com.joyintech.tams.exchange.common.db.domain.BatchInfoHistory;
import com.joyintech.tams.exchange.common.db.domain.BatchInfoHistoryExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BatchInfoHistoryMapper extends IMybatisMapper<BatchInfoHistory, String, BatchInfoHistoryExample> {
    long countByExample(BatchInfoHistoryExample example);

    int deleteByExample(BatchInfoHistoryExample example);

    int deleteByPrimaryKey(String id);

    int insert(BatchInfoHistory record);

    int insertBatch(List<BatchInfoHistory> records);

    int insertSelective(BatchInfoHistory record);

    List<BatchInfoHistory> selectByExample(BatchInfoHistoryExample example);

    BatchInfoHistory selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") BatchInfoHistory record, @Param("example") BatchInfoHistoryExample example);

    int updateByExample(@Param("record") BatchInfoHistory record, @Param("example") BatchInfoHistoryExample example);

    int updateByPrimaryKeySelective(BatchInfoHistory record);

    int updateByPrimaryKey(BatchInfoHistory record);
}