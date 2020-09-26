package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValFundNBaseInfoN;
import org.springframework.stereotype.Repository;

@Repository
public interface ValFundNBaseInfoNMapper {
    int deleteByPrimaryKey(String assetId);

    int insert(ValFundNBaseInfoN record);

    int insertSelective(ValFundNBaseInfoN record);

    int insertBasicByBasicInfo(ValFundNBaseInfoN record);

    ValFundNBaseInfoN selectByPrimaryKey(String assetId);

    int updateByPrimaryKeySelective(ValFundNBaseInfoN record);

    int updateByPrimaryKey(ValFundNBaseInfoN record);
}