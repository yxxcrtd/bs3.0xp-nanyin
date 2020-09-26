package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValAffirmPurTrade;

@Repository
public interface ValAffirmPurTradeMapper {
    int deleteByPrimaryKey(String prodId);

    int insert(ValAffirmPurTrade record);

    int insertSelective(ValAffirmPurTrade record);

    int insertBasicByBasicInfo(ValAffirmPurTrade record);

    ValAffirmPurTrade selectByPrimaryKey(String prodId);

    int updateByPrimaryKeySelective(ValAffirmPurTrade record);

    int updateByPrimaryKey(ValAffirmPurTrade record);
}