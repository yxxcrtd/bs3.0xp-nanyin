package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.EtrTarget;
import com.joyintech.tams.common.base.entity.EtrTargetKey;
import org.springframework.stereotype.Repository;

@Repository
public interface EtrTargetMapper {
    int deleteByPrimaryKey(EtrTargetKey key);

    int insert(EtrTarget record);

    int insertSelective(EtrTarget record);

    int insertBasicByBasicInfo(EtrTarget record);

    EtrTarget selectByPrimaryKey(EtrTargetKey key);

    int updateByPrimaryKeySelective(EtrTarget record);

    int updateByPrimaryKey(EtrTarget record);
}