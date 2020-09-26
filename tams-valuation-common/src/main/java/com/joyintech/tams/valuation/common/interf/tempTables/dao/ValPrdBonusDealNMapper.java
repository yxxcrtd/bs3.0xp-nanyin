package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValPrdBonusDealN;

@Repository
public interface ValPrdBonusDealNMapper {
    int deleteByPrimaryKey(String prodId);

    int insert(ValPrdBonusDealN record);

    int insertSelective(ValPrdBonusDealN record);

    int insertBasicByBasicInfo(ValPrdBonusDealN record);

    ValPrdBonusDealN selectByPrimaryKey(String prodId);

    int updateByPrimaryKeySelective(ValPrdBonusDealN record);

    int updateByPrimaryKey(ValPrdBonusDealN record);
}