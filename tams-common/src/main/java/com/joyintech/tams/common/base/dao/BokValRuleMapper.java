package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.BokValRule;
import org.springframework.stereotype.Repository;

@Repository
public interface BokValRuleMapper {
    int deleteByPrimaryKey(String ruleId);

    int insert(BokValRule record);

    int insertSelective(BokValRule record);

    int insertBasicByBasicInfo(BokValRule record);

    BokValRule selectByPrimaryKey(String ruleId);

    int updateByPrimaryKeySelective(BokValRule record);

    int updateByPrimaryKey(BokValRule record);
}