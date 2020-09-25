package com.joyintech.tams.common.base.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.RskIndividCondition;

@Repository
public interface RskIndividConditionMapper {

    int deleteByPrimaryKey(String conditionId);

    int insert(RskIndividCondition record);

    int insertSelective(RskIndividCondition record);

    int insertBasicByBasicInfo(RskIndividCondition record);

    RskIndividCondition selectByPrimaryKey(String conditionId);

    int updateByPrimaryKeySelective(RskIndividCondition record);

    int updateByPrimaryKey(RskIndividCondition record);
}