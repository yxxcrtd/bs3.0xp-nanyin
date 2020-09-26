package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValMngIntPlan;

@Repository
public interface ValMngIntPlanMapper {
    int deleteByPrimaryKey(String assetId);

    int insert(ValMngIntPlan record);

    int insertSelective(ValMngIntPlan record);

    int insertBasicByBasicInfo(ValMngIntPlan record);

    ValMngIntPlan selectByPrimaryKey(String assetId);

    int updateByPrimaryKeySelective(ValMngIntPlan record);

    int updateByPrimaryKey(ValMngIntPlan record);
}