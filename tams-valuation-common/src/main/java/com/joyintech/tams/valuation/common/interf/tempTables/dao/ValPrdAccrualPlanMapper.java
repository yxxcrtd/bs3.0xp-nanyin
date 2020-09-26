package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValPrdAccrualPlan;

@Repository
public interface ValPrdAccrualPlanMapper {
    int deleteByPrimaryKey(String prodId);

    int insert(ValPrdAccrualPlan record);

    int insertSelective(ValPrdAccrualPlan record);

    int insertBasicByBasicInfo(ValPrdAccrualPlan record);

    ValPrdAccrualPlan selectByPrimaryKey(String prodId);

    int updateByPrimaryKeySelective(ValPrdAccrualPlan record);

    int updateByPrimaryKey(ValPrdAccrualPlan record);
}