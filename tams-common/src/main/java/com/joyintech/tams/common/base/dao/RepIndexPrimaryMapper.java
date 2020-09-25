package com.joyintech.tams.common.base.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.RepIndexPrimary;

@Repository
public interface RepIndexPrimaryMapper {
    int deleteByPrimaryKey(String id);

    int insert(RepIndexPrimary record);

    int insertSelective(RepIndexPrimary record);

    int insertBasicByBasicInfo(RepIndexPrimary record);

    RepIndexPrimary selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(RepIndexPrimary record);

    int updateByPrimaryKey(RepIndexPrimary record);
}