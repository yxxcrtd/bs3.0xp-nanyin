package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValPrdOpenPlanC;

@Repository
public interface ValPrdOpenPlanCMapper {
    int deleteByPrimaryKey(String prodId);

    int insert(ValPrdOpenPlanC record);

    int insertSelective(ValPrdOpenPlanC record);

    int insertBasicByBasicInfo(ValPrdOpenPlanC record);

    ValPrdOpenPlanC selectByPrimaryKey(String prodId);

    int updateByPrimaryKeySelective(ValPrdOpenPlanC record);

    int updateByPrimaryKey(ValPrdOpenPlanC record);
}