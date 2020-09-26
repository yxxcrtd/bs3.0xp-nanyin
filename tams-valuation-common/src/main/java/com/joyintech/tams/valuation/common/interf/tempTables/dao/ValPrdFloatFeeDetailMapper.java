package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValPrdFloatFeeDetail;

@Repository
public interface ValPrdFloatFeeDetailMapper {
    int insert(ValPrdFloatFeeDetail record);

    int insertSelective(ValPrdFloatFeeDetail record);

    int insertBasicByBasicInfo(ValPrdFloatFeeDetail record);
}