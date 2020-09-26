package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValMngMnfCashBonusTrade;

@Repository
public interface ValMngMnfCashBonusTradeMapper {
    int deleteByPrimaryKey(String prodId);

    int insert(ValMngMnfCashBonusTrade record);

    int insertSelective(ValMngMnfCashBonusTrade record);

    int insertBasicByBasicInfo(ValMngMnfCashBonusTrade record);

    ValMngMnfCashBonusTrade selectByPrimaryKey(String prodId);

    int updateByPrimaryKeySelective(ValMngMnfCashBonusTrade record);

    int updateByPrimaryKey(ValMngMnfCashBonusTrade record);
}