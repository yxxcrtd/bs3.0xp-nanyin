package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValPrdRateAdjustInfo;

@Repository
public interface ValPrdRateAdjustInfoMapper {
    int deleteByPrimaryKey(String prodId);

    int insert(ValPrdRateAdjustInfo record);

    int insertSelective(ValPrdRateAdjustInfo record);

    int insertBasicByBasicInfo(ValPrdRateAdjustInfo record);

    ValPrdRateAdjustInfo selectByPrimaryKey(String prodId);

    int updateByPrimaryKeySelective(ValPrdRateAdjustInfo record);

    int updateByPrimaryKey(ValPrdRateAdjustInfo record);
}