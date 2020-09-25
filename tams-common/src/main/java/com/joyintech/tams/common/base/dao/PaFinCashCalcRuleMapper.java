package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.PaFinCashCalcRule;
import com.joyintech.tams.common.base.entity.PaFinCashCalcRuleKey;
import org.springframework.stereotype.Repository;

@Repository
public interface PaFinCashCalcRuleMapper {
    int deleteByPrimaryKey(PaFinCashCalcRuleKey key);

    int insert(PaFinCashCalcRule record);

    int insertSelective(PaFinCashCalcRule record);

    int insertBasicByBasicInfo(PaFinCashCalcRule record);

    PaFinCashCalcRule selectByPrimaryKey(PaFinCashCalcRuleKey key);

    int updateByPrimaryKeySelective(PaFinCashCalcRule record);

    int updateByPrimaryKey(PaFinCashCalcRule record);
}