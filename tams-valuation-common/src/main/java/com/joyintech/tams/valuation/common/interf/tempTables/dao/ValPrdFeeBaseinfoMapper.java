package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValPrdFeeBaseinfo;

@Repository
public interface ValPrdFeeBaseinfoMapper {
    int insert(ValPrdFeeBaseinfo record);

    int insertSelective(ValPrdFeeBaseinfo record);

    int insertBasicByBasicInfo(ValPrdFeeBaseinfo record);
}