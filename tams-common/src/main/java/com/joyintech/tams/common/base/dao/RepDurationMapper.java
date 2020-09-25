package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.RepDuration;
import com.joyintech.tams.common.base.entity.RepDurationKey;
import org.springframework.stereotype.Repository;

@Repository
public interface RepDurationMapper {
    int deleteByPrimaryKey(RepDurationKey key);

    int insert(RepDuration record);

    int insertSelective(RepDuration record);

    int insertBasicByBasicInfo(RepDuration record);

    RepDuration selectByPrimaryKey(RepDurationKey key);

    int updateByPrimaryKeySelective(RepDuration record);

    int updateByPrimaryKey(RepDuration record);
}