package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValMngMnfRedAccoutTrade;

@Repository
public interface ValMngMnfRedAccoutTradeMapper {
    int deleteByPrimaryKey(String prodId);

    int insert(ValMngMnfRedAccoutTrade record);

    int insertSelective(ValMngMnfRedAccoutTrade record);

    int insertBasicByBasicInfo(ValMngMnfRedAccoutTrade record);

    ValMngMnfRedAccoutTrade selectByPrimaryKey(String prodId);

    int updateByPrimaryKeySelective(ValMngMnfRedAccoutTrade record);

    int updateByPrimaryKey(ValMngMnfRedAccoutTrade record);
}