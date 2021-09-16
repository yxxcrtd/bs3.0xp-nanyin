package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ModBatchLogs;
import com.joyintech.tams.common.base.entity.ModBatchLogsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ModBatchLogsMapper {
    int countByExample(ModBatchLogsExample example);

    int deleteByExample(ModBatchLogsExample example);

    int insert(ModBatchLogs record);

    int insertSelective(ModBatchLogs record);

    int insertBasicByBasicInfo(ModBatchLogs record);

    List<ModBatchLogs> selectByExampleWithBLOBs(ModBatchLogsExample example);

    List<ModBatchLogs> selectByExample(ModBatchLogsExample example);

    int updateByExampleSelective(@Param("record") ModBatchLogs record, @Param("example") ModBatchLogsExample example);

    int updateByExampleWithBLOBs(@Param("record") ModBatchLogs record, @Param("example") ModBatchLogsExample example);

    int updateByExample(@Param("record") ModBatchLogs record, @Param("example") ModBatchLogsExample example);
}