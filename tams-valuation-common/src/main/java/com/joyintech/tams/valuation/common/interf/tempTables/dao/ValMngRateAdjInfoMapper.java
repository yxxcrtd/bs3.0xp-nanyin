package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValMngRateAdjInfo;

@Repository
public interface ValMngRateAdjInfoMapper {
    int deleteByPrimaryKey(String assetId);

    int insert(ValMngRateAdjInfo record);

    int insertSelective(ValMngRateAdjInfo record);

    int insertBasicByBasicInfo(ValMngRateAdjInfo record);

    ValMngRateAdjInfo selectByPrimaryKey(String assetId);

    int updateByPrimaryKeySelective(ValMngRateAdjInfo record);

    int updateByPrimaryKey(ValMngRateAdjInfo record);
}