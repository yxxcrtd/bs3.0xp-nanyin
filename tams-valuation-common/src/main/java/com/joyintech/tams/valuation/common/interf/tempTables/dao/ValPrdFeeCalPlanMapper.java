package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValPrdFeeCalPlan;

@Repository
public interface ValPrdFeeCalPlanMapper {
    int insert(ValPrdFeeCalPlan record);

    int insertSelective(ValPrdFeeCalPlan record);

    int insertBasicByBasicInfo(ValPrdFeeCalPlan record);
}