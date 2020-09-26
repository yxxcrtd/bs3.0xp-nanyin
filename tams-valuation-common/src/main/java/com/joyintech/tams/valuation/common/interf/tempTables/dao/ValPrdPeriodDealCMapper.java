package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValPrdPeriodDealC;

@Repository
public interface ValPrdPeriodDealCMapper {
    int deleteByPrimaryKey(String prodId);

    int insert(ValPrdPeriodDealC record);

    int insertSelective(ValPrdPeriodDealC record);

    int insertBasicByBasicInfo(ValPrdPeriodDealC record);

    ValPrdPeriodDealC selectByPrimaryKey(String prodId);

    int updateByPrimaryKeySelective(ValPrdPeriodDealC record);

    int updateByPrimaryKey(ValPrdPeriodDealC record);
}