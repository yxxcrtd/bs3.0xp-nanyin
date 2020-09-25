package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.FinFloatCalcRule;
import com.joyintech.tams.common.base.entity.FinFloatCalcRuleKey;
import org.springframework.stereotype.Repository;

@Repository
public interface FinFloatCalcRuleMapper {
    int deleteByPrimaryKey(FinFloatCalcRuleKey key);

    int insert(FinFloatCalcRule record);

    int insertSelective(FinFloatCalcRule record);

    int insertBasicByBasicInfo(FinFloatCalcRule record);

    FinFloatCalcRule selectByPrimaryKey(FinFloatCalcRuleKey key);

    int updateByPrimaryKeySelective(FinFloatCalcRule record);

    int updateByPrimaryKey(FinFloatCalcRule record);
}