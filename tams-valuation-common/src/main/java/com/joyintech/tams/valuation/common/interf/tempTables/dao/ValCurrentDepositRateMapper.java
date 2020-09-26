package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValCurrentDepositRate;

@Repository
public interface ValCurrentDepositRateMapper {
    int deleteByPrimaryKey(String acctId);

    int insert(ValCurrentDepositRate record);

    int insertSelective(ValCurrentDepositRate record);

    int insertBasicByBasicInfo(ValCurrentDepositRate record);

    ValCurrentDepositRate selectByPrimaryKey(String acctId);

    int updateByPrimaryKeySelective(ValCurrentDepositRate record);

    int updateByPrimaryKey(ValCurrentDepositRate record);
}