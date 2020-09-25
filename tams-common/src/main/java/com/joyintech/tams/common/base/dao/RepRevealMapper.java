package com.joyintech.tams.common.base.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.RepReveal;

@Repository
public interface RepRevealMapper {
    int deleteByPrimaryKey(String id);

    int insert(RepReveal record);

    int insertSelective(RepReveal record);

    int insertBasicByBasicInfo(RepReveal record);

    RepReveal selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(RepReveal record);

    int updateByPrimaryKey(RepReveal record);
}