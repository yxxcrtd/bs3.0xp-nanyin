package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValPrdFloatFeeDetailN;

@Repository
public interface ValPrdFloatFeeDetailNMapper {
    int deleteByPrimaryKey(String prodId);

    int insert(ValPrdFloatFeeDetailN record);

    int insertSelective(ValPrdFloatFeeDetailN record);

    int insertBasicByBasicInfo(ValPrdFloatFeeDetailN record);

    ValPrdFloatFeeDetailN selectByPrimaryKey(String prodId);

    int updateByPrimaryKeySelective(ValPrdFloatFeeDetailN record);

    int updateByPrimaryKey(ValPrdFloatFeeDetailN record);
}