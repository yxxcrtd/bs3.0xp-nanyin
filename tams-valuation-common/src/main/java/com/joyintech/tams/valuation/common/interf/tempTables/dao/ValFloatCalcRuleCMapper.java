package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValFloatCalcRuleC;
import org.springframework.stereotype.Repository;

@Repository
public interface ValFloatCalcRuleCMapper {
    int deleteByPrimaryKey(String prodId);

    int insert(ValFloatCalcRuleC record);

    int insertSelective(ValFloatCalcRuleC record);

    int insertBasicByBasicInfo(ValFloatCalcRuleC record);

    ValFloatCalcRuleC selectByPrimaryKey(String prodId);

    int updateByPrimaryKeySelective(ValFloatCalcRuleC record);

    int updateByPrimaryKey(ValFloatCalcRuleC record);
}