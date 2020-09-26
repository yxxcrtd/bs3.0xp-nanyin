package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValDlTargetRateAdjust;

@Repository
public interface ValDlTargetRateAdjustMapper {
    int insert(ValDlTargetRateAdjust record);

    int insertSelective(ValDlTargetRateAdjust record);

    int insertBasicByBasicInfo(ValDlTargetRateAdjust record);
}