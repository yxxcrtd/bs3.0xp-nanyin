package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValFundMnfPayPlan;

@Repository
public interface ValFundMnfPayPlanMapper {
    int deleteByPrimaryKey(String assetId);

    int insert(ValFundMnfPayPlan record);

    int insertSelective(ValFundMnfPayPlan record);

    int insertBasicByBasicInfo(ValFundMnfPayPlan record);

    ValFundMnfPayPlan selectByPrimaryKey(String assetId);

    int updateByPrimaryKeySelective(ValFundMnfPayPlan record);

    int updateByPrimaryKey(ValFundMnfPayPlan record);
}