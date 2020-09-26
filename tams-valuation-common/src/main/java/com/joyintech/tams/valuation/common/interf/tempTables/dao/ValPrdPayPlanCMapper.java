package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValPrdPayPlanC;

@Repository
public interface ValPrdPayPlanCMapper {
    int deleteByPrimaryKey(String prodId);

    int insert(ValPrdPayPlanC record);

    int insertSelective(ValPrdPayPlanC record);

    int insertBasicByBasicInfo(ValPrdPayPlanC record);

    ValPrdPayPlanC selectByPrimaryKey(String prodId);

    int updateByPrimaryKeySelective(ValPrdPayPlanC record);

    int updateByPrimaryKey(ValPrdPayPlanC record);
}