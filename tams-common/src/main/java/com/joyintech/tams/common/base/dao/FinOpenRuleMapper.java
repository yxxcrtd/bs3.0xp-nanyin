package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.FinOpenRule;
import com.joyintech.tams.common.base.entity.FinOpenRuleKey;
import org.springframework.stereotype.Repository;

@Repository
public interface FinOpenRuleMapper {
    int deleteByPrimaryKey(FinOpenRuleKey key);

    int insert(FinOpenRule record);

    int insertSelective(FinOpenRule record);

    int insertBasicByBasicInfo(FinOpenRule record);

    FinOpenRule selectByPrimaryKey(FinOpenRuleKey key);

    int updateByPrimaryKeySelective(FinOpenRule record);

    int updateByPrimaryKey(FinOpenRule record);
}