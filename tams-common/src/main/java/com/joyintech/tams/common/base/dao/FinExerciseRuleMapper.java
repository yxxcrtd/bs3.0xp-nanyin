package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.FinExerciseRule;
import com.joyintech.tams.common.base.entity.FinExerciseRuleKey;
import org.springframework.stereotype.Repository;

@Repository
public interface FinExerciseRuleMapper {
    int deleteByPrimaryKey(FinExerciseRuleKey key);

    int insert(FinExerciseRule record);

    int insertSelective(FinExerciseRule record);

    int insertBasicByBasicInfo(FinExerciseRule record);

    FinExerciseRule selectByPrimaryKey(FinExerciseRuleKey key);

    int updateByPrimaryKeySelective(FinExerciseRule record);

    int updateByPrimaryKey(FinExerciseRule record);
}