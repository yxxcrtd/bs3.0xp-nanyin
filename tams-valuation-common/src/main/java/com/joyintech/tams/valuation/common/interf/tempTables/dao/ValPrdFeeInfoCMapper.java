package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValPrdFeeInfoC;

@Repository
public interface ValPrdFeeInfoCMapper {
    int deleteByPrimaryKey(String prodId);

    int insert(ValPrdFeeInfoC record);

    int insertSelective(ValPrdFeeInfoC record);

    int insertBasicByBasicInfo(ValPrdFeeInfoC record);

    ValPrdFeeInfoC selectByPrimaryKey(String prodId);

    int updateByPrimaryKeySelective(ValPrdFeeInfoC record);

    int updateByPrimaryKey(ValPrdFeeInfoC record);
}