package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValCashBonusTrade;
import org.springframework.stereotype.Repository;

@Repository
public interface ValCashBonusTradeMapper {
    int deleteByPrimaryKey(String prodId);

    int insert(ValCashBonusTrade record);

    int insertSelective(ValCashBonusTrade record);

    int insertBasicByBasicInfo(ValCashBonusTrade record);

    ValCashBonusTrade selectByPrimaryKey(String prodId);

    int updateByPrimaryKeySelective(ValCashBonusTrade record);

    int updateByPrimaryKey(ValCashBonusTrade record);
}