package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.EtrAutoDispenseRule;
import org.springframework.stereotype.Repository;

@Repository
public interface EtrAutoDispenseRuleMapper {
    int deleteByPrimaryKey(String ruleId);

    int insert(EtrAutoDispenseRule record);

    int insertSelective(EtrAutoDispenseRule record);

    int insertBasicByBasicInfo(EtrAutoDispenseRule record);

    EtrAutoDispenseRule selectByPrimaryKey(String ruleId);

    int updateByPrimaryKeySelective(EtrAutoDispenseRule record);

    int updateByPrimaryKey(EtrAutoDispenseRule record);
}