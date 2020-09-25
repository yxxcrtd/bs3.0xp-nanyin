package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.FinCashCountingRule;
import com.joyintech.tams.common.base.entity.FinCashCountingRuleKey;
import org.springframework.stereotype.Repository;

@Repository
public interface FinCashCountingRuleMapper {
    int deleteByPrimaryKey(FinCashCountingRuleKey key);

    int insert(FinCashCountingRule record);

    int insertSelective(FinCashCountingRule record);

    int insertBasicByBasicInfo(FinCashCountingRule record);

    FinCashCountingRule selectByPrimaryKey(FinCashCountingRuleKey key);

    int updateByPrimaryKeySelective(FinCashCountingRule record);

    int updateByPrimaryKey(FinCashCountingRule record);
}