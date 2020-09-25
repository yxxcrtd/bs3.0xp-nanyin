package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.FinFloatAccrualSche;
import com.joyintech.tams.common.base.entity.FinFloatAccrualScheKey;
import org.springframework.stereotype.Repository;

@Repository
public interface FinFloatAccrualScheMapper {
    int deleteByPrimaryKey(FinFloatAccrualScheKey key);

    int insert(FinFloatAccrualSche record);

    int insertSelective(FinFloatAccrualSche record);

    int insertBasicByBasicInfo(FinFloatAccrualSche record);

    FinFloatAccrualSche selectByPrimaryKey(FinFloatAccrualScheKey key);

    int updateByPrimaryKeySelective(FinFloatAccrualSche record);

    int updateByPrimaryKey(FinFloatAccrualSche record);
}