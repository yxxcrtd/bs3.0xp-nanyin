package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValSeclendInfo;

@Repository
public interface ValSeclendInfoMapper {
    int insert(ValSeclendInfo record);

    int insertSelective(ValSeclendInfo record);

    int insertBasicByBasicInfo(ValSeclendInfo record);
}