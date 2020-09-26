package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValFxTargetPlan;
import org.springframework.stereotype.Repository;

@Repository
public interface ValFxTargetPlanMapper {
    int insert(ValFxTargetPlan record);

    int insertSelective(ValFxTargetPlan record);

    int insertBasicByBasicInfo(ValFxTargetPlan record);
}