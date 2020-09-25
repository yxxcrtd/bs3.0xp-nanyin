package com.joyintech.tams.common.base.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.RepJobRange;

@Repository
public interface RepJobRangeMapper {
    int deleteByPrimaryKey(String id);

    int insert(RepJobRange record);

    int insertSelective(RepJobRange record);

    int insertBasicByBasicInfo(RepJobRange record);

    RepJobRange selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(RepJobRange record);

    int updateByPrimaryKey(RepJobRange record);
}