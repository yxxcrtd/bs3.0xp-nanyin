package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.RskRuleInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface RskRuleInfoMapper {
    int deleteByPrimaryKey(String ruleId);

    int insert(RskRuleInfo record);

    int insertSelective(RskRuleInfo record);

    int insertBasicByBasicInfo(RskRuleInfo record);

    RskRuleInfo selectByPrimaryKey(String ruleId);

    int updateByPrimaryKeySelective(RskRuleInfo record);

    int updateByPrimaryKey(RskRuleInfo record);
}