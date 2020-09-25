package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.BokDetailId;
import com.joyintech.tams.common.base.entity.BokDetailIdKey;
import org.springframework.stereotype.Repository;

@Repository
public interface BokDetailIdMapper {
    int deleteByPrimaryKey(BokDetailIdKey key);

    int insert(BokDetailId record);

    int insertSelective(BokDetailId record);

    int insertBasicByBasicInfo(BokDetailId record);

    BokDetailId selectByPrimaryKey(BokDetailIdKey key);

    int updateByPrimaryKeySelective(BokDetailId record);

    int updateByPrimaryKey(BokDetailId record);
}