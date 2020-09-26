package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValMngCashBonusN;

@Repository
public interface ValMngCashBonusNMapper {
    int deleteByPrimaryKey(String prodId);

    int insert(ValMngCashBonusN record);

    int insertSelective(ValMngCashBonusN record);

    int insertBasicByBasicInfo(ValMngCashBonusN record);

    ValMngCashBonusN selectByPrimaryKey(String prodId);

    int updateByPrimaryKeySelective(ValMngCashBonusN record);

    int updateByPrimaryKey(ValMngCashBonusN record);
}