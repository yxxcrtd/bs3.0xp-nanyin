package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.StlSecpRecon;
import com.joyintech.tams.common.base.entity.StlSecpReconKey;
import org.springframework.stereotype.Repository;

@Repository
public interface StlSecpReconMapper {
    int deleteByPrimaryKey(StlSecpReconKey key);

    int insert(StlSecpRecon record);

    int insertSelective(StlSecpRecon record);

    int insertBasicByBasicInfo(StlSecpRecon record);

    StlSecpRecon selectByPrimaryKey(StlSecpReconKey key);

    int updateByPrimaryKeySelective(StlSecpRecon record);

    int updateByPrimaryKey(StlSecpRecon record);
}