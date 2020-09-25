package com.joyintech.tams.common.base.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.FinClawbackCalcRule;
import com.joyintech.tams.common.base.entity.FinClawbackCalcRuleKey;

@Repository
public interface FinClawbackCalcRuleMapper {
    int deleteByPrimaryKey(FinClawbackCalcRuleKey key);

    int insert(FinClawbackCalcRule record);

    int insertSelective(FinClawbackCalcRule record);

    int insertBasicByBasicInfo(FinClawbackCalcRule record);

    FinClawbackCalcRule selectByPrimaryKey(FinClawbackCalcRuleKey key);
    
    List<FinClawbackCalcRule> selectListByPrimaryKey(FinClawbackCalcRuleKey key);

    int updateByPrimaryKeySelective(FinClawbackCalcRule record);

    int updateByPrimaryKey(FinClawbackCalcRule record);
}