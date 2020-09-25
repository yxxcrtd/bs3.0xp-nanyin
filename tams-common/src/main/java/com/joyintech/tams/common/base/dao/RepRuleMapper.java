package com.joyintech.tams.common.base.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.RepRule;

@Repository
public interface RepRuleMapper {
    int deleteByPrimaryKey(String id);

    int insert(RepRule record);

    int insertSelective(RepRule record);

    int insertBasicByBasicInfo(RepRule record);

    RepRule selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(RepRule record);

    int updateByPrimaryKey(RepRule record);
}