package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValMngBuyTrade;

@Repository
public interface ValMngBuyTradeMapper {
    int deleteByPrimaryKey(String prodId);

    int insert(ValMngBuyTrade record);

    int insertSelective(ValMngBuyTrade record);

    int insertBasicByBasicInfo(ValMngBuyTrade record);

    ValMngBuyTrade selectByPrimaryKey(String prodId);

    int updateByPrimaryKeySelective(ValMngBuyTrade record);

    int updateByPrimaryKey(ValMngBuyTrade record);
}