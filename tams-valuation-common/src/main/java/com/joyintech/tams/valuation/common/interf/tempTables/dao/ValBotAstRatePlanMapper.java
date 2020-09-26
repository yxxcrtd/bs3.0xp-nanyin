package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValBotAstRatePlan;

@Repository
public interface ValBotAstRatePlanMapper {
    int deleteByPrimaryKey(String assetId);

    int insert(ValBotAstRatePlan record);

    int insertSelective(ValBotAstRatePlan record);

    int insertBasicByBasicInfo(ValBotAstRatePlan record);

    ValBotAstRatePlan selectByPrimaryKey(String assetId);

    int updateByPrimaryKeySelective(ValBotAstRatePlan record);

    int updateByPrimaryKey(ValBotAstRatePlan record);
}