package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValPrdFeeCalPlanN;

@Repository
public interface ValPrdFeeCalPlanNMapper {
    int insert(ValPrdFeeCalPlanN record);

    int insertSelective(ValPrdFeeCalPlanN record);

    int insertBasicByBasicInfo(ValPrdFeeCalPlanN record);
}