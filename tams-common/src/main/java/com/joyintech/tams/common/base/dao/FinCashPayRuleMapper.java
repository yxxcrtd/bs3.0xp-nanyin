package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.FinCashPayRule;
import com.joyintech.tams.common.base.entity.FinCashPayRuleKey;
import org.springframework.stereotype.Repository;

@Repository
public interface FinCashPayRuleMapper {
    int deleteByPrimaryKey(FinCashPayRuleKey key);

    int insert(FinCashPayRule record);

    int insertSelective(FinCashPayRule record);

    int insertBasicByBasicInfo(FinCashPayRule record);

    FinCashPayRule selectByPrimaryKey(FinCashPayRuleKey key);

    int updateByPrimaryKeySelective(FinCashPayRule record);

    int updateByPrimaryKey(FinCashPayRule record);
}