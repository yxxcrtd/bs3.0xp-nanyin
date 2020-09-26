package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValPrdBonusDealC;

@Repository
public interface ValPrdBonusDealCMapper {
    int deleteByPrimaryKey(String prodId);

    int insert(ValPrdBonusDealC record);

    int insertSelective(ValPrdBonusDealC record);

    int insertBasicByBasicInfo(ValPrdBonusDealC record);

    ValPrdBonusDealC selectByPrimaryKey(String prodId);

    int updateByPrimaryKeySelective(ValPrdBonusDealC record);

    int updateByPrimaryKey(ValPrdBonusDealC record);
}