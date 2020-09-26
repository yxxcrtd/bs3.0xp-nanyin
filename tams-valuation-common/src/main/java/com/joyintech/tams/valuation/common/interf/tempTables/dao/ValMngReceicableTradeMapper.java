package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValMngReceicableTrade;

@Repository
public interface ValMngReceicableTradeMapper {
    int deleteByPrimaryKey(String prodId);

    int insert(ValMngReceicableTrade record);

    int insertSelective(ValMngReceicableTrade record);

    int insertBasicByBasicInfo(ValMngReceicableTrade record);

    ValMngReceicableTrade selectByPrimaryKey(String prodId);

    int updateByPrimaryKeySelective(ValMngReceicableTrade record);

    int updateByPrimaryKey(ValMngReceicableTrade record);
}