package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValMngPeriodTrade;

@Repository
public interface ValMngPeriodTradeMapper {
    int deleteByPrimaryKey(String prodId);

    int insert(ValMngPeriodTrade record);

    int insertSelective(ValMngPeriodTrade record);

    int insertBasicByBasicInfo(ValMngPeriodTrade record);

    ValMngPeriodTrade selectByPrimaryKey(String prodId);

    int updateByPrimaryKeySelective(ValMngPeriodTrade record);

    int updateByPrimaryKey(ValMngPeriodTrade record);
}