package com.joyintech.tams.common.base.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.RskIndividConditionLine;

@Repository
public interface RskIndividConditionLineMapper {
    int deleteByPrimaryKey(String conditionId);

    int insert(RskIndividConditionLine record);

    int insertSelective(RskIndividConditionLine record);

    int insertBasicByBasicInfo(RskIndividConditionLine record);

    RskIndividConditionLine selectByPrimaryKey(String conditionId);

    int updateByPrimaryKeySelective(RskIndividConditionLine record);

    int updateByPrimaryKey(RskIndividConditionLine record);
}