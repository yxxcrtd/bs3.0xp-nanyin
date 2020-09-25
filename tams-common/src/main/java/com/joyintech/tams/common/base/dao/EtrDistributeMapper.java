package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.EtrDistribute;
import com.joyintech.tams.common.base.entity.EtrDistributeKey;
import org.springframework.stereotype.Repository;

@Repository
public interface EtrDistributeMapper {
    int deleteByPrimaryKey(EtrDistributeKey key);

    int insert(EtrDistribute record);

    int insertSelective(EtrDistribute record);

    int insertBasicByBasicInfo(EtrDistribute record);

    EtrDistribute selectByPrimaryKey(EtrDistributeKey key);

    int updateByPrimaryKeySelective(EtrDistribute record);

    int updateByPrimaryKey(EtrDistribute record);
}