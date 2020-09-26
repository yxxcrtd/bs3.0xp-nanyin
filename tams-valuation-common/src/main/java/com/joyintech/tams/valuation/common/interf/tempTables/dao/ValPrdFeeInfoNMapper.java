package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValPrdFeeInfoN;

@Repository
public interface ValPrdFeeInfoNMapper {
    int deleteByPrimaryKey(String prodId);

    int insert(ValPrdFeeInfoN record);

    int insertSelective(ValPrdFeeInfoN record);

    int insertBasicByBasicInfo(ValPrdFeeInfoN record);

    ValPrdFeeInfoN selectByPrimaryKey(String prodId);

    int updateByPrimaryKeySelective(ValPrdFeeInfoN record);

    int updateByPrimaryKey(ValPrdFeeInfoN record);
}