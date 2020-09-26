package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValMngCapPlan;

@Repository
public interface ValMngCapPlanMapper {
    int insert(ValMngCapPlan record);

    int insertSelective(ValMngCapPlan record);

    int insertBasicByBasicInfo(ValMngCapPlan record);
}