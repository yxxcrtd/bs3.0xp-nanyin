package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValBotReceicableTrade;
import org.springframework.stereotype.Repository;

@Repository
public interface ValBotReceicableTradeMapper {
    int deleteByPrimaryKey(String prodId);

    int insert(ValBotReceicableTrade record);

    int insertSelective(ValBotReceicableTrade record);

    int insertBasicByBasicInfo(ValBotReceicableTrade record);

    ValBotReceicableTrade selectByPrimaryKey(String prodId);

    int updateByPrimaryKeySelective(ValBotReceicableTrade record);

    int updateByPrimaryKey(ValBotReceicableTrade record);
}