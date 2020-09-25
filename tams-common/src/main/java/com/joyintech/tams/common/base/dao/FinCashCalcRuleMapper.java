package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.FinCashCalcRule;
import com.joyintech.tams.common.base.entity.FinCashCalcRuleKey;
import org.springframework.stereotype.Repository;

@Repository
public interface FinCashCalcRuleMapper {
    int deleteByPrimaryKey(FinCashCalcRuleKey key);

    int insert(FinCashCalcRule record);

    int insertSelective(FinCashCalcRule record);

    int insertBasicByBasicInfo(FinCashCalcRule record);

    FinCashCalcRule selectByPrimaryKey(FinCashCalcRuleKey key);

    int updateByPrimaryKeySelective(FinCashCalcRule record);

    int updateByPrimaryKey(FinCashCalcRule record);
}