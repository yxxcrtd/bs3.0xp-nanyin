package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValPrdFeeCalPlanC;

@Repository
public interface ValPrdFeeCalPlanCMapper {
    int insert(ValPrdFeeCalPlanC record);

    int insertSelective(ValPrdFeeCalPlanC record);

    int insertBasicByBasicInfo(ValPrdFeeCalPlanC record);
}