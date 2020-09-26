package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValSecPayRateRule;

@Repository
public interface ValSecPayRateRuleMapper {
    int deleteByPrimaryKey(String assetId);

    int insert(ValSecPayRateRule record);

    int insertSelective(ValSecPayRateRule record);

    int insertBasicByBasicInfo(ValSecPayRateRule record);

    ValSecPayRateRule selectByPrimaryKey(String assetId);

    int updateByPrimaryKeySelective(ValSecPayRateRule record);

    int updateByPrimaryKey(ValSecPayRateRule record);
}