package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValPrdRateAdjustN;

@Repository
public interface ValPrdRateAdjustNMapper {
    int deleteByPrimaryKey(String prodId);

    int insert(ValPrdRateAdjustN record);

    int insertSelective(ValPrdRateAdjustN record);

    int insertBasicByBasicInfo(ValPrdRateAdjustN record);

    ValPrdRateAdjustN selectByPrimaryKey(String prodId);

    int updateByPrimaryKeySelective(ValPrdRateAdjustN record);

    int updateByPrimaryKey(ValPrdRateAdjustN record);
}