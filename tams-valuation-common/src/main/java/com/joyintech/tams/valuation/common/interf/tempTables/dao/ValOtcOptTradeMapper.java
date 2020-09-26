package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValOtcOptTrade;
import org.springframework.stereotype.Repository;

@Repository
public interface ValOtcOptTradeMapper {
    int deleteByPrimaryKey(String prodId);

    int insert(ValOtcOptTrade record);

    int insertSelective(ValOtcOptTrade record);

    int insertBasicByBasicInfo(ValOtcOptTrade record);

    ValOtcOptTrade selectByPrimaryKey(String prodId);

    int updateByPrimaryKeySelective(ValOtcOptTrade record);

    int updateByPrimaryKey(ValOtcOptTrade record);
}