package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValFxTargetRateAdjust;
import org.springframework.stereotype.Repository;

@Repository
public interface ValFxTargetRateAdjustMapper {
    int insert(ValFxTargetRateAdjust record);

    int insertSelective(ValFxTargetRateAdjust record);

    int insertBasicByBasicInfo(ValFxTargetRateAdjust record);
}