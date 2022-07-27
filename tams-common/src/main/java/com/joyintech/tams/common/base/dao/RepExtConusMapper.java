package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.RepExtConus;
import com.joyintech.tams.common.base.entity.RepExtConusKey;
import org.springframework.stereotype.Repository;

@Repository
public interface RepExtConusMapper {
    int deleteByPrimaryKey(RepExtConusKey key);

    int insert(RepExtConus record);

    int insertSelective(RepExtConus record);

    int insertBasicByBasicInfo(RepExtConus record);

    RepExtConus selectByPrimaryKey(RepExtConusKey key);

    int updateByPrimaryKeySelective(RepExtConus record);

    int updateByPrimaryKey(RepExtConus record);
}