package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.RskFaciDeduceRule;
import com.joyintech.tams.common.base.entity.RskFaciDeduceRuleKey;
import org.springframework.stereotype.Repository;

@Repository
public interface RskFaciDeduceRuleMapper {
    int deleteByPrimaryKey(RskFaciDeduceRuleKey key);

    int insert(RskFaciDeduceRule record);

    int insertSelective(RskFaciDeduceRule record);

    int insertBasicByBasicInfo(RskFaciDeduceRule record);

    RskFaciDeduceRule selectByPrimaryKey(RskFaciDeduceRuleKey key);

    int updateByPrimaryKeySelective(RskFaciDeduceRule record);

    int updateByPrimaryKey(RskFaciDeduceRule record);
}