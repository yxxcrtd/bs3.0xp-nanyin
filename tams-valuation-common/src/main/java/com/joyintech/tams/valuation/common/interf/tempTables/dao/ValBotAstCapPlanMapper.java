package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValBotAstCapPlan;

@Repository
public interface ValBotAstCapPlanMapper {
    int deleteByPrimaryKey(String assetId);

    int insert(ValBotAstCapPlan record);

    int insertSelective(ValBotAstCapPlan record);

    int insertBasicByBasicInfo(ValBotAstCapPlan record);

    ValBotAstCapPlan selectByPrimaryKey(String assetId);

    int updateByPrimaryKeySelective(ValBotAstCapPlan record);

    int updateByPrimaryKey(ValBotAstCapPlan record);
}