package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValRedAccoutTrade;
import org.springframework.stereotype.Repository;

@Repository
public interface ValRedAccoutTradeMapper {
    int deleteByPrimaryKey(String prodId);

    int insert(ValRedAccoutTrade record);

    int insertSelective(ValRedAccoutTrade record);

    int insertBasicByBasicInfo(ValRedAccoutTrade record);

    ValRedAccoutTrade selectByPrimaryKey(String prodId);

    int updateByPrimaryKeySelective(ValRedAccoutTrade record);

    int updateByPrimaryKey(ValRedAccoutTrade record);
}