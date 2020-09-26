package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValSecRateAdjust;

@Repository
public interface ValSecRateAdjustMapper {
    int deleteByPrimaryKey(String assetId);

    int insert(ValSecRateAdjust record);

    int insertSelective(ValSecRateAdjust record);

    int insertBasicByBasicInfo(ValSecRateAdjust record);

    ValSecRateAdjust selectByPrimaryKey(String assetId);

    int updateByPrimaryKeySelective(ValSecRateAdjust record);

    int updateByPrimaryKey(ValSecRateAdjust record);
}