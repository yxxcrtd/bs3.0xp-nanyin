package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.FinFloatCountingRule;
import com.joyintech.tams.common.base.entity.FinFloatCountingRuleKey;
import org.springframework.stereotype.Repository;

@Repository
public interface FinFloatCountingRuleMapper {
    int deleteByPrimaryKey(FinFloatCountingRuleKey key);

    int insert(FinFloatCountingRule record);

    int insertSelective(FinFloatCountingRule record);

    int insertBasicByBasicInfo(FinFloatCountingRule record);

    FinFloatCountingRule selectByPrimaryKey(FinFloatCountingRuleKey key);

    int updateByPrimaryKeySelective(FinFloatCountingRule record);

    int updateByPrimaryKey(FinFloatCountingRule record);
}