package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValPrdOpenPlan;

@Repository
public interface ValPrdOpenPlanMapper {
    int deleteByPrimaryKey(String prodId);

    int insert(ValPrdOpenPlan record);

    int insertSelective(ValPrdOpenPlan record);

    int insertBasicByBasicInfo(ValPrdOpenPlan record);

    ValPrdOpenPlan selectByPrimaryKey(String prodId);

    int updateByPrimaryKeySelective(ValPrdOpenPlan record);

    int updateByPrimaryKey(ValPrdOpenPlan record);
}