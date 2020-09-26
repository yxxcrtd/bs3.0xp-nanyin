package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValPrdFloatFeeDetailC;

@Repository
public interface ValPrdFloatFeeDetailCMapper {
    int deleteByPrimaryKey(String prodId);

    int insert(ValPrdFloatFeeDetailC record);

    int insertSelective(ValPrdFloatFeeDetailC record);

    int insertBasicByBasicInfo(ValPrdFloatFeeDetailC record);

    ValPrdFloatFeeDetailC selectByPrimaryKey(String prodId);

    int updateByPrimaryKeySelective(ValPrdFloatFeeDetailC record);

    int updateByPrimaryKey(ValPrdFloatFeeDetailC record);
}