package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.FinCashPlan;
import com.joyintech.tams.common.base.entity.FinCashPlanKey;
import org.springframework.stereotype.Repository;

@Repository
public interface FinCashPlanMapper {
    int deleteByPrimaryKey(FinCashPlanKey key);

    int insert(FinCashPlan record);

    int insertSelective(FinCashPlan record);

    int insertBasicByBasicInfo(FinCashPlan record);

    FinCashPlan selectByPrimaryKey(FinCashPlanKey key);

    int updateByPrimaryKeySelective(FinCashPlan record);

    int updateByPrimaryKey(FinCashPlan record);
}