package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValChannelFeeInfo;

@Repository
public interface ValChannelFeeInfoMapper {
    int insert(ValChannelFeeInfo record);

    int insertSelective(ValChannelFeeInfo record);

    int insertBasicByBasicInfo(ValChannelFeeInfo record);
}