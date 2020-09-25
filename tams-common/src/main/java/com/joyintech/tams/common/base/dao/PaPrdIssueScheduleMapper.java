package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.PaPrdIssueSchedule;
import org.springframework.stereotype.Repository;

@Repository
public interface PaPrdIssueScheduleMapper {
    int deleteByPrimaryKey(String finprodId);

    int insert(PaPrdIssueSchedule record);

    int insertSelective(PaPrdIssueSchedule record);

    int insertBasicByBasicInfo(PaPrdIssueSchedule record);

    PaPrdIssueSchedule selectByPrimaryKey(String finprodId);

    int updateByPrimaryKeySelective(PaPrdIssueSchedule record);

    int updateByPrimaryKey(PaPrdIssueSchedule record);
}