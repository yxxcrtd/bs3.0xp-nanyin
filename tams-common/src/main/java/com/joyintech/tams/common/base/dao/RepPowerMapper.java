package com.joyintech.tams.common.base.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.RepPower;

@Repository
public interface RepPowerMapper {
    int deleteByPrimaryKey(String id);

    int insert(RepPower record);

    int insertSelective(RepPower record);

    int insertBasicByBasicInfo(RepPower record);

    RepPower selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(RepPower record);

    int updateByPrimaryKey(RepPower record);
}