package com.joyintech.tams.common.base.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.RepExpectedLog;

@Repository
public interface RepExpectedLogMapper {
    int deleteByPrimaryKey(String id);

    int insert(RepExpectedLog record);

    int insertSelective(RepExpectedLog record);

    int insertBasicByBasicInfo(RepExpectedLog record);

    RepExpectedLog selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(RepExpectedLog record);

    int updateByPrimaryKey(RepExpectedLog record);
}