package com.joyintech.tams.common.base.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.RepInstallLogDetail;

@Repository
public interface RepInstallLogDetailMapper {
    int deleteByPrimaryKey(String id);

    int insert(RepInstallLogDetail record);

    int insertSelective(RepInstallLogDetail record);

    int insertBasicByBasicInfo(RepInstallLogDetail record);

    RepInstallLogDetail selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(RepInstallLogDetail record);

    int updateByPrimaryKey(RepInstallLogDetail record);
}