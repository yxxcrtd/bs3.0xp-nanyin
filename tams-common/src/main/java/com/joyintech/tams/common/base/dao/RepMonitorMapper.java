package com.joyintech.tams.common.base.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.RepMonitor;

@Repository
public interface RepMonitorMapper {
    int deleteByPrimaryKey(String jobId);

    int insert(RepMonitor record);

    int insertSelective(RepMonitor record);

    int insertBasicByBasicInfo(RepMonitor record);

    RepMonitor selectByPrimaryKey(String jobId);

    int updateByPrimaryKeySelective(RepMonitor record);

    int updateByPrimaryKey(RepMonitor record);
}