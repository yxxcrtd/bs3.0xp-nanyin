package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValBotAstTrade;

@Repository
public interface ValBotAstTradeMapper {
    int deleteByPrimaryKey(String prodId);

    int insert(ValBotAstTrade record);

    int insertSelective(ValBotAstTrade record);

    int insertBasicByBasicInfo(ValBotAstTrade record);

    ValBotAstTrade selectByPrimaryKey(String prodId);

    int updateByPrimaryKeySelective(ValBotAstTrade record);

    int updateByPrimaryKey(ValBotAstTrade record);
}