package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.RepPosition;
import com.joyintech.tams.common.base.entity.RepPositionKey;
import org.springframework.stereotype.Repository;

@Repository
public interface RepPositionMapper {
    int deleteByPrimaryKey(RepPositionKey key);

    int insert(RepPosition record);

    int insertSelective(RepPosition record);

    int insertBasicByBasicInfo(RepPosition record);

    RepPosition selectByPrimaryKey(RepPositionKey key);

    int updateByPrimaryKeySelective(RepPosition record);

    int updateByPrimaryKey(RepPosition record);
}