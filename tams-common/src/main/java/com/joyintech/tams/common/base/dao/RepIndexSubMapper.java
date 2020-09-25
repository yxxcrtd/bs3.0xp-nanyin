package com.joyintech.tams.common.base.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.RepIndexSub;

@Repository
public interface RepIndexSubMapper {
    int deleteByPrimaryKey(String id);

    int insert(RepIndexSub record);

    int insertSelective(RepIndexSub record);

    int insertBasicByBasicInfo(RepIndexSub record);

    RepIndexSub selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(RepIndexSub record);

    int updateByPrimaryKey(RepIndexSub record);
}