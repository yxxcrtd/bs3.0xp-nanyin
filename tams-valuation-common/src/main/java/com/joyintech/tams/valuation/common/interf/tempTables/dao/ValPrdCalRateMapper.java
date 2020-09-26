package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValPrdCalRate;

@Repository
public interface ValPrdCalRateMapper {
    int deleteByPrimaryKey(String prodId);

    int insert(ValPrdCalRate record);

    int insertSelective(ValPrdCalRate record);

    int insertBasicByBasicInfo(ValPrdCalRate record);

    ValPrdCalRate selectByPrimaryKey(String prodId);

    int updateByPrimaryKeySelective(ValPrdCalRate record);

    int updateByPrimaryKey(ValPrdCalRate record);
}