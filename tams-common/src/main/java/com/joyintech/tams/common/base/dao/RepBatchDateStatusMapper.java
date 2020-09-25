package com.joyintech.tams.common.base.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.RepBatchDateStatus;

@Repository
public interface RepBatchDateStatusMapper {
    int deleteByPrimaryKey(String id);

    int insert(RepBatchDateStatus record);

    int insertSelective(RepBatchDateStatus record);

    int insertBasicByBasicInfo(RepBatchDateStatus record);

    RepBatchDateStatus selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(RepBatchDateStatus record);

    int updateByPrimaryKey(RepBatchDateStatus record);
}