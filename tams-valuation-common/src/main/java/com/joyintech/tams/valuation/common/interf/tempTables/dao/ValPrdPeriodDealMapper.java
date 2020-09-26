package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValPrdPeriodDeal;

@Repository
public interface ValPrdPeriodDealMapper {
    int deleteByPrimaryKey(String prodId);

    int insert(ValPrdPeriodDeal record);

    int insertSelective(ValPrdPeriodDeal record);

    int insertBasicByBasicInfo(ValPrdPeriodDeal record);

    ValPrdPeriodDeal selectByPrimaryKey(String prodId);

    int updateByPrimaryKeySelective(ValPrdPeriodDeal record);

    int updateByPrimaryKey(ValPrdPeriodDeal record);
}