package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValPrdRateAdjustC;

@Repository
public interface ValPrdRateAdjustCMapper {
    int deleteByPrimaryKey(String prodId);

    int insert(ValPrdRateAdjustC record);

    int insertSelective(ValPrdRateAdjustC record);

    int insertBasicByBasicInfo(ValPrdRateAdjustC record);

    ValPrdRateAdjustC selectByPrimaryKey(String prodId);

    int updateByPrimaryKeySelective(ValPrdRateAdjustC record);

    int updateByPrimaryKey(ValPrdRateAdjustC record);
}