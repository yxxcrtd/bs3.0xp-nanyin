package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.TargetDetail;
import com.joyintech.tams.common.base.entity.TargetDetailKey;
import org.springframework.stereotype.Repository;

@Repository
public interface TargetDetailMapper {
    int deleteByPrimaryKey(TargetDetailKey key);

    int insert(TargetDetail record);

    int insertSelective(TargetDetail record);

    int insertBasicByBasicInfo(TargetDetail record);

    TargetDetail selectByPrimaryKey(TargetDetailKey key);

    int updateByPrimaryKeySelective(TargetDetail record);

    int updateByPrimaryKey(TargetDetail record);
}