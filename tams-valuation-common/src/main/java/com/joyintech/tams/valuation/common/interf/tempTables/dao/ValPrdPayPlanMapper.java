package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValPrdPayPlan;

@Repository
public interface ValPrdPayPlanMapper {
    int deleteByPrimaryKey(String prodId);

    int insert(ValPrdPayPlan record);

    int insertSelective(ValPrdPayPlan record);

    int insertBasicByBasicInfo(ValPrdPayPlan record);

    ValPrdPayPlan selectByPrimaryKey(String prodId);

    int updateByPrimaryKeySelective(ValPrdPayPlan record);

    int updateByPrimaryKey(ValPrdPayPlan record);
}