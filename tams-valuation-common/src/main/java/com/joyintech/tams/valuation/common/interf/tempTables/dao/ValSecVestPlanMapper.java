package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValSecVestPlan;

@Repository
public interface ValSecVestPlanMapper {
    int deleteByPrimaryKey(String assetId);

    int insert(ValSecVestPlan record);

    int insertSelective(ValSecVestPlan record);

    int insertBasicByBasicInfo(ValSecVestPlan record);

    ValSecVestPlan selectByPrimaryKey(String assetId);

    int updateByPrimaryKeySelective(ValSecVestPlan record);

    int updateByPrimaryKey(ValSecVestPlan record);
}