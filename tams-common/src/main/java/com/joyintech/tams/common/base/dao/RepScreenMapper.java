package com.joyintech.tams.common.base.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.RepScreen;

@Repository
public interface RepScreenMapper {
    int deleteByPrimaryKey(String screenId);

    int insert(RepScreen record);

    int insertSelective(RepScreen record);

    int insertBasicByBasicInfo(RepScreen record);

    RepScreen selectByPrimaryKey(String screenId);

    int updateByPrimaryKeySelective(RepScreen record);

    int updateByPrimaryKey(RepScreen record);
}