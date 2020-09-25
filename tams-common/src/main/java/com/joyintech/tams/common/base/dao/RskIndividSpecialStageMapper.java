package com.joyintech.tams.common.base.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.RskIndividSpecialStage;

@Repository
public interface RskIndividSpecialStageMapper {

    int deleteByPrimaryKey(String uniteId);

    int insert(RskIndividSpecialStage record);

    int insertSelective(RskIndividSpecialStage record);

    int insertBasicByBasicInfo(RskIndividSpecialStage record);

    RskIndividSpecialStage selectByPrimaryKey(String uniteId);

    int updateByPrimaryKeySelective(RskIndividSpecialStage record);

    int updateByPrimaryKey(RskIndividSpecialStage record);
}