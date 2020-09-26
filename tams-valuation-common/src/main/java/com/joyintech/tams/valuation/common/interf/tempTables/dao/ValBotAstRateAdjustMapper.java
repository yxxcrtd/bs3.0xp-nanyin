package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValBotAstRateAdjust;

@Repository
public interface ValBotAstRateAdjustMapper {
    int deleteByPrimaryKey(String prodId);

    int insert(ValBotAstRateAdjust record);

    int insertSelective(ValBotAstRateAdjust record);

    int insertBasicByBasicInfo(ValBotAstRateAdjust record);

    ValBotAstRateAdjust selectByPrimaryKey(String prodId);

    int updateByPrimaryKeySelective(ValBotAstRateAdjust record);

    int updateByPrimaryKey(ValBotAstRateAdjust record);
}