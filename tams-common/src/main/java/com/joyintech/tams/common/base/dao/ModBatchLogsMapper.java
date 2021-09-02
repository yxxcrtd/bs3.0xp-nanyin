package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ModBatchLogs;
import org.springframework.stereotype.Repository;

@Repository
public interface ModBatchLogsMapper {
    int insert(ModBatchLogs record);

    int insertSelective(ModBatchLogs record);

    int insertBasicByBasicInfo(ModBatchLogs record);
}