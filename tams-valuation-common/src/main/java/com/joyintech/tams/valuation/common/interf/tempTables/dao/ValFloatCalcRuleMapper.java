package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValFloatCalcRule;
import org.springframework.stereotype.Repository;

@Repository
public interface ValFloatCalcRuleMapper {
    int deleteByPrimaryKey(String prodId);

    int insert(ValFloatCalcRule record);

    int insertSelective(ValFloatCalcRule record);

    int insertBasicByBasicInfo(ValFloatCalcRule record);

    ValFloatCalcRule selectByPrimaryKey(String prodId);

    int updateByPrimaryKeySelective(ValFloatCalcRule record);

    int updateByPrimaryKey(ValFloatCalcRule record);
}