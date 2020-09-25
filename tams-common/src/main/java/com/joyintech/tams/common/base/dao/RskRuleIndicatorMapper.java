package com.joyintech.tams.common.base.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.RskRuleIndicator;
import com.joyintech.tams.common.base.entity.RskRuleIndicatorKey;

@Repository
public interface RskRuleIndicatorMapper {

    int deleteByPrimaryKey(RskRuleIndicatorKey key);

    int insert(RskRuleIndicator record);

    int insertSelective(RskRuleIndicator record);

    int insertBasicByBasicInfo(RskRuleIndicator record);

    RskRuleIndicator selectByPrimaryKey(RskRuleIndicatorKey key);

    int updateByPrimaryKeySelective(RskRuleIndicator record);

    int updateByPrimaryKey(RskRuleIndicator record);
}