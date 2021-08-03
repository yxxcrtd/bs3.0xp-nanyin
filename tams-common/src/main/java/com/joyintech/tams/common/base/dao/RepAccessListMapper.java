package com.joyintech.tams.common.base.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.RepAccessList;

@Repository
public interface RepAccessListMapper {
    int deleteByPrimaryKey(String listId);

    int insert(RepAccessList record);

    int insertSelective(RepAccessList record);

    int insertBasicByBasicInfo(RepAccessList record);

    RepAccessList selectByPrimaryKey(String listId);

    int updateByPrimaryKeySelective(RepAccessList record);

    int updateByPrimaryKey(RepAccessList record);
}