package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.PtlGoal;
import com.joyintech.tams.common.base.entity.PtlGoalKey;
import org.springframework.stereotype.Repository;

@Repository
public interface PtlGoalMapper {
    int deleteByPrimaryKey(PtlGoalKey key);

    int insert(PtlGoal record);

    int insertSelective(PtlGoal record);

    int insertBasicByBasicInfo(PtlGoal record);

    PtlGoal selectByPrimaryKey(PtlGoalKey key);

    int updateByPrimaryKeySelective(PtlGoal record);

    int updateByPrimaryKey(PtlGoal record);
}