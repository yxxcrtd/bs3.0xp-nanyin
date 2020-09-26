package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValBotPeriodFund;

@Repository
public interface ValBotPeriodFundMapper {
    int deleteByPrimaryKey(String prodId);

    int insert(ValBotPeriodFund record);

    int insertSelective(ValBotPeriodFund record);

    int insertBasicByBasicInfo(ValBotPeriodFund record);

    ValBotPeriodFund selectByPrimaryKey(String prodId);

    int updateByPrimaryKeySelective(ValBotPeriodFund record);

    int updateByPrimaryKey(ValBotPeriodFund record);
}