package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.PrdIssueSchedule;
import org.springframework.stereotype.Repository;

@Repository
public interface PrdIssueScheduleMapper {
    int deleteByPrimaryKey(String finprodId);

    int insert(PrdIssueSchedule record);

    int insertSelective(PrdIssueSchedule record);

    int insertBasicByBasicInfo(PrdIssueSchedule record);

    PrdIssueSchedule selectByPrimaryKey(String finprodId);

    int updateByPrimaryKeySelective(PrdIssueSchedule record);

    int updateByPrimaryKey(PrdIssueSchedule record);
}