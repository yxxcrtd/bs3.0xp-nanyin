package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValSecPayRatePlan;

@Repository
public interface ValSecPayRatePlanMapper {
    int deleteByPrimaryKey(String assetId);

    int insert(ValSecPayRatePlan record);

    int insertSelective(ValSecPayRatePlan record);

    int insertBasicByBasicInfo(ValSecPayRatePlan record);

    ValSecPayRatePlan selectByPrimaryKey(String assetId);

    int updateByPrimaryKeySelective(ValSecPayRatePlan record);

    int updateByPrimaryKey(ValSecPayRatePlan record);
}