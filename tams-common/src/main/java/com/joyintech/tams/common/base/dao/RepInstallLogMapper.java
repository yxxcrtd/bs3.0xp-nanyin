package com.joyintech.tams.common.base.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.RepInstallLog;

@Repository
public interface RepInstallLogMapper {
    int deleteByPrimaryKey(String id);

    int insert(RepInstallLog record);

    int insertSelective(RepInstallLog record);

    int insertBasicByBasicInfo(RepInstallLog record);

    RepInstallLog selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(RepInstallLog record);

    int updateByPrimaryKey(RepInstallLog record);
}