package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.FinCashResetPlan;
import com.joyintech.tams.common.base.entity.FinCashResetPlanKey;
import org.springframework.stereotype.Repository;

@Repository
public interface FinCashResetPlanMapper {
    int deleteByPrimaryKey(FinCashResetPlanKey key);

    int insert(FinCashResetPlan record);

    int insertSelective(FinCashResetPlan record);

    int insertBasicByBasicInfo(FinCashResetPlan record);

    FinCashResetPlan selectByPrimaryKey(FinCashResetPlanKey key);

    int updateByPrimaryKeySelective(FinCashResetPlan record);

    int updateByPrimaryKey(FinCashResetPlan record);
}