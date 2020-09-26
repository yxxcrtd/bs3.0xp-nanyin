package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValBonusResTrade;

@Repository
public interface ValBonusResTradeMapper {
    int deleteByPrimaryKey(String prodId);

    int insert(ValBonusResTrade record);

    int insertSelective(ValBonusResTrade record);

    int insertBasicByBasicInfo(ValBonusResTrade record);

    ValBonusResTrade selectByPrimaryKey(String prodId);

    int updateByPrimaryKeySelective(ValBonusResTrade record);

    int updateByPrimaryKey(ValBonusResTrade record);
}