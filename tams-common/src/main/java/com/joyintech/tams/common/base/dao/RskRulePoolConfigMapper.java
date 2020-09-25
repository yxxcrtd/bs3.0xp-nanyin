package com.joyintech.tams.common.base.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.RskRulePoolConfig;
import com.joyintech.tams.common.base.entity.RskRulePoolConfigKey;

@Repository
public interface RskRulePoolConfigMapper {

    int deleteByPrimaryKey(RskRulePoolConfigKey key);

    int insert(RskRulePoolConfig record);

    int insertSelective(RskRulePoolConfig record);

    int insertBasicByBasicInfo(RskRulePoolConfig record);

    RskRulePoolConfig selectByPrimaryKey(RskRulePoolConfigKey key);

    int updateByPrimaryKeySelective(RskRulePoolConfig record);

    int updateByPrimaryKey(RskRulePoolConfig record);
}