package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.FinOpenPlan;
import com.joyintech.tams.common.base.entity.FinOpenPlanKey;
import org.springframework.stereotype.Repository;

@Repository
public interface FinOpenPlanMapper {
    int deleteByPrimaryKey(FinOpenPlanKey key);

    int insert(FinOpenPlan record);

    int insertSelective(FinOpenPlan record);

    int insertBasicByBasicInfo(FinOpenPlan record);

    FinOpenPlan selectByPrimaryKey(FinOpenPlanKey key);

    int updateByPrimaryKeySelective(FinOpenPlan record);

    int updateByPrimaryKey(FinOpenPlan record);
}