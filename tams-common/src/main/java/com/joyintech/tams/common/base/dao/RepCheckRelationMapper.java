package com.joyintech.tams.common.base.dao;


import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.RepCheckRelation;
import com.joyintech.tams.common.base.entity.RepCheckRelationKey;

@Repository
public interface RepCheckRelationMapper {
    int deleteByPrimaryKey(RepCheckRelationKey key);

    int insert(RepCheckRelation record);

    int insertSelective(RepCheckRelation record);

    int insertBasicByBasicInfo(RepCheckRelation record);

    RepCheckRelation selectByPrimaryKey(RepCheckRelationKey key);

    int updateByPrimaryKeySelective(RepCheckRelation record);

    int updateByPrimaryKey(RepCheckRelation record);
}