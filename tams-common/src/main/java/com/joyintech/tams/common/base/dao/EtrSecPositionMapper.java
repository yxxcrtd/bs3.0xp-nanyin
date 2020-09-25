package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.EtrSecPosition;
import com.joyintech.tams.common.base.entity.EtrSecPositionKey;
import org.springframework.stereotype.Repository;

@Repository
public interface EtrSecPositionMapper {
    int deleteByPrimaryKey(EtrSecPositionKey key);

    int insert(EtrSecPosition record);

    int insertSelective(EtrSecPosition record);

    int insertBasicByBasicInfo(EtrSecPosition record);

    EtrSecPosition selectByPrimaryKey(EtrSecPositionKey key);

    int updateByPrimaryKeySelective(EtrSecPosition record);

    int updateByPrimaryKey(EtrSecPosition record);
}