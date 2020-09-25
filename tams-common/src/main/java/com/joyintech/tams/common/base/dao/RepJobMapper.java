package com.joyintech.tams.common.base.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.RepJob;

@Repository
public interface RepJobMapper {
    int deleteByPrimaryKey(String jobId);

    int insert(RepJob record);

    int insertSelective(RepJob record);

    int insertBasicByBasicInfo(RepJob record);

    RepJob selectByPrimaryKey(String jobId);

    int updateByPrimaryKeySelective(RepJob record);

    int updateByPrimaryKey(RepJob record);
}