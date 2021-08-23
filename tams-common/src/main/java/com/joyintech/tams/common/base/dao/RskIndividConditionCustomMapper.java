package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.RskIndividConditionCustom;
import org.springframework.stereotype.Repository;

@Repository
public interface RskIndividConditionCustomMapper {
    int deleteByPrimaryKey(String conditionItem);

    int insert(RskIndividConditionCustom record);

    int insertSelective(RskIndividConditionCustom record);

    int insertBasicByBasicInfo(RskIndividConditionCustom record);

    RskIndividConditionCustom selectByPrimaryKey(String conditionItem);

    int updateByPrimaryKeySelective(RskIndividConditionCustom record);

    int updateByPrimaryKeyWithBLOBs(RskIndividConditionCustom record);

    int updateByPrimaryKey(RskIndividConditionCustom record);
}