package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValPrdOpenPlanN;

@Repository
public interface ValPrdOpenPlanNMapper {
    int deleteByPrimaryKey(String prodId);

    int insert(ValPrdOpenPlanN record);

    int insertSelective(ValPrdOpenPlanN record);

    int insertBasicByBasicInfo(ValPrdOpenPlanN record);

    ValPrdOpenPlanN selectByPrimaryKey(String prodId);

    int updateByPrimaryKeySelective(ValPrdOpenPlanN record);

    int updateByPrimaryKey(ValPrdOpenPlanN record);
}