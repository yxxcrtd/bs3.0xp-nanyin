package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValPrdPeriodDealN;

@Repository
public interface ValPrdPeriodDealNMapper {
    int deleteByPrimaryKey(String prodId);

    int insert(ValPrdPeriodDealN record);

    int insertSelective(ValPrdPeriodDealN record);

    int insertBasicByBasicInfo(ValPrdPeriodDealN record);

    ValPrdPeriodDealN selectByPrimaryKey(String prodId);

    int updateByPrimaryKeySelective(ValPrdPeriodDealN record);

    int updateByPrimaryKey(ValPrdPeriodDealN record);
}