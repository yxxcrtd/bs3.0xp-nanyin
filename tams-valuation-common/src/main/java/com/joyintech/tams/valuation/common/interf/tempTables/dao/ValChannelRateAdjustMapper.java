package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValChannelRateAdjust;

@Repository
public interface ValChannelRateAdjustMapper {
    int insert(ValChannelRateAdjust record);

    int insertSelective(ValChannelRateAdjust record);

    int insertBasicByBasicInfo(ValChannelRateAdjust record);
}