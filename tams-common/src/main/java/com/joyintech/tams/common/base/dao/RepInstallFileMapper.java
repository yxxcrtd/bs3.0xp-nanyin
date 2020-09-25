package com.joyintech.tams.common.base.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.RepInstallFile;

@Repository
public interface RepInstallFileMapper {
    int deleteByPrimaryKey(String id);

    int insert(RepInstallFile record);

    int insertSelective(RepInstallFile record);

    int insertBasicByBasicInfo(RepInstallFile record);

    RepInstallFile selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(RepInstallFile record);

    int updateByPrimaryKey(RepInstallFile record);
}