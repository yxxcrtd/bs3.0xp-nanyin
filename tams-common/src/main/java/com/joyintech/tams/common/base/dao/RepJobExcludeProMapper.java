package com.joyintech.tams.common.base.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.RepJobExcludePro;

@Repository
public interface RepJobExcludeProMapper {
    int deleteByPrimaryKey(String id);

    int insert(RepJobExcludePro record);

    int insertSelective(RepJobExcludePro record);

    int insertBasicByBasicInfo(RepJobExcludePro record);

    RepJobExcludePro selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(RepJobExcludePro record);

    int updateByPrimaryKey(RepJobExcludePro record);
}