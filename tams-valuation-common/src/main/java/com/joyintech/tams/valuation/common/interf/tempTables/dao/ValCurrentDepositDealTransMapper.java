package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValCurrentDepositDealTrans;

@Repository
public interface ValCurrentDepositDealTransMapper {
    int deleteByPrimaryKey(String prodId);

    int insert(ValCurrentDepositDealTrans record);

    int insertSelective(ValCurrentDepositDealTrans record);

    int insertBasicByBasicInfo(ValCurrentDepositDealTrans record);

    ValCurrentDepositDealTrans selectByPrimaryKey(String prodId);

    int updateByPrimaryKeySelective(ValCurrentDepositDealTrans record);

    int updateByPrimaryKey(ValCurrentDepositDealTrans record);
}