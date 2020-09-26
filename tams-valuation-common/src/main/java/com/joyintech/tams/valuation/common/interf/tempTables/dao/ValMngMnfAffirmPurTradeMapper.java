package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValMngMnfAffirmPurTrade;

@Repository
public interface ValMngMnfAffirmPurTradeMapper {
    int deleteByPrimaryKey(String prodId);

    int insert(ValMngMnfAffirmPurTrade record);

    int insertSelective(ValMngMnfAffirmPurTrade record);

    int insertBasicByBasicInfo(ValMngMnfAffirmPurTrade record);

    ValMngMnfAffirmPurTrade selectByPrimaryKey(String prodId);

    int updateByPrimaryKeySelective(ValMngMnfAffirmPurTrade record);

    int updateByPrimaryKey(ValMngMnfAffirmPurTrade record);
}