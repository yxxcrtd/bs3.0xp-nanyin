package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValFloatCalcRuleN;
import org.springframework.stereotype.Repository;

@Repository
public interface ValFloatCalcRuleNMapper {
    int deleteByPrimaryKey(String prodId);

    int insert(ValFloatCalcRuleN record);

    int insertSelective(ValFloatCalcRuleN record);

    int insertBasicByBasicInfo(ValFloatCalcRuleN record);

    ValFloatCalcRuleN selectByPrimaryKey(String prodId);

    int updateByPrimaryKeySelective(ValFloatCalcRuleN record);

    int updateByPrimaryKey(ValFloatCalcRuleN record);
}