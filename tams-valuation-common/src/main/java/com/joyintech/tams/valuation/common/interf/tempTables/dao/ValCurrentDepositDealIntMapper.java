package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValCurrentDepositDealInt;

@Repository
public interface ValCurrentDepositDealIntMapper {
    int deleteByPrimaryKey(String prodId);

    int insert(ValCurrentDepositDealInt record);

    int insertSelective(ValCurrentDepositDealInt record);

    int insertBasicByBasicInfo(ValCurrentDepositDealInt record);

    ValCurrentDepositDealInt selectByPrimaryKey(String prodId);

    int updateByPrimaryKeySelective(ValCurrentDepositDealInt record);

    int updateByPrimaryKey(ValCurrentDepositDealInt record);
}