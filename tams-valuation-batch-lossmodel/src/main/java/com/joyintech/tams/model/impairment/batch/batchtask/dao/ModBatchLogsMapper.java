package com.joyintech.tams.model.impairment.batch.batchtask.dao;

import com.joyintech.tams.model.impairment.batch.batchtask.dto.ModBatchLogs;
import com.joyintech.tams.model.impairment.batch.batchtask.dto.ModBatchLogsExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ModBatchLogsMapper {
    int countByExample(ModBatchLogsExample example);

    int deleteByExample(ModBatchLogsExample example);

    int deleteByPrimaryKey(String seqno);

    int insert(ModBatchLogs record);

    int insertSelective(ModBatchLogs record);

    int insertBasicByBasicInfo(ModBatchLogs record);

    List<ModBatchLogs> selectByExampleWithBLOBs(ModBatchLogsExample example);

    List<ModBatchLogs> selectByExample(ModBatchLogsExample example);

    ModBatchLogs selectByPrimaryKey(String seqno);

    int updateByExampleSelective(@Param("record") ModBatchLogs record, @Param("example") ModBatchLogsExample example);

    int updateByExampleWithBLOBs(@Param("record") ModBatchLogs record, @Param("example") ModBatchLogsExample example);

    int updateByExample(@Param("record") ModBatchLogs record, @Param("example") ModBatchLogsExample example);

    int updateByPrimaryKeySelective(ModBatchLogs record);

    int updateByPrimaryKeyWithBLOBs(ModBatchLogs record);

    int updateByPrimaryKey(ModBatchLogs record);
}