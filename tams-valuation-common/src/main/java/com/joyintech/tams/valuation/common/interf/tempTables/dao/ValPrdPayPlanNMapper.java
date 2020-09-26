package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValPrdPayPlanN;

@Repository
public interface ValPrdPayPlanNMapper {
    int deleteByPrimaryKey(String prodId);

    int insert(ValPrdPayPlanN record);

    int insertSelective(ValPrdPayPlanN record);

    int insertBasicByBasicInfo(ValPrdPayPlanN record);

    ValPrdPayPlanN selectByPrimaryKey(String prodId);

    int updateByPrimaryKeySelective(ValPrdPayPlanN record);

    int updateByPrimaryKey(ValPrdPayPlanN record);
}