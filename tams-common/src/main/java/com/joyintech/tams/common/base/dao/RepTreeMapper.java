package com.joyintech.tams.common.base.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.RepTree;

@Repository
public interface RepTreeMapper {
    int deleteByPrimaryKey(String treeId);

    int insert(RepTree record);

    int insertSelective(RepTree record);

    int insertBasicByBasicInfo(RepTree record);

    RepTree selectByPrimaryKey(String treeId);

    int updateByPrimaryKeySelective(RepTree record);

    int updateByPrimaryKey(RepTree record);
}