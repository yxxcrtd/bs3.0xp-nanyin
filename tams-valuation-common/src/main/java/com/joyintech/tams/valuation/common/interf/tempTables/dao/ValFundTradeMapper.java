package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValFundTrade;
import org.springframework.stereotype.Repository;

@Repository
public interface ValFundTradeMapper {
    int deleteByPrimaryKey(String prodId);

    int insert(ValFundTrade record);

    int insertSelective(ValFundTrade record);

    int insertBasicByBasicInfo(ValFundTrade record);

    ValFundTrade selectByPrimaryKey(String prodId);

    int updateByPrimaryKeySelective(ValFundTrade record);

    int updateByPrimaryKey(ValFundTrade record);
}