package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.RskEventItemRule;
import com.joyintech.tams.common.base.entity.RskEventItemRuleKey;
import org.springframework.stereotype.Repository;

@Repository
public interface RskEventItemRuleMapper {
    int deleteByPrimaryKey(RskEventItemRuleKey key);

    int insert(RskEventItemRule record);

    int insertSelective(RskEventItemRule record);

    int insertBasicByBasicInfo(RskEventItemRule record);

    RskEventItemRule selectByPrimaryKey(RskEventItemRuleKey key);

    int updateByPrimaryKeySelective(RskEventItemRule record);

    int updateByPrimaryKeyWithBLOBs(RskEventItemRule record);

    int updateByPrimaryKey(RskEventItemRule record);
}