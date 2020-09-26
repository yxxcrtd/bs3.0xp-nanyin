package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValSecReceicableTrade;
import org.springframework.stereotype.Repository;

@Repository
public interface ValSecReceicableTradeMapper {
    int deleteByPrimaryKey(String prodId);

    int insert(ValSecReceicableTrade record);

    int insertSelective(ValSecReceicableTrade record);

    int insertBasicByBasicInfo(ValSecReceicableTrade record);

    ValSecReceicableTrade selectByPrimaryKey(String prodId);

    int updateByPrimaryKeySelective(ValSecReceicableTrade record);

    int updateByPrimaryKey(ValSecReceicableTrade record);
}