package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValDlReceicableTrade;
import org.springframework.stereotype.Repository;

@Repository
public interface ValDlReceicableTradeMapper {
    int deleteByPrimaryKey(String prodId);

    int insert(ValDlReceicableTrade record);

    int insertSelective(ValDlReceicableTrade record);

    int insertBasicByBasicInfo(ValDlReceicableTrade record);

    ValDlReceicableTrade selectByPrimaryKey(String prodId);

    int updateByPrimaryKeySelective(ValDlReceicableTrade record);

    int updateByPrimaryKey(ValDlReceicableTrade record);
}