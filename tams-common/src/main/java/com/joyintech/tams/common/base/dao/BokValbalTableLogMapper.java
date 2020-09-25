package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.BokValbalTableLog;
import com.joyintech.tams.common.base.entity.BokValbalTableLogKey;
import org.springframework.stereotype.Repository;

@Repository
public interface BokValbalTableLogMapper {
    int deleteByPrimaryKey(BokValbalTableLogKey key);

    int insert(BokValbalTableLog record);

    int insertSelective(BokValbalTableLog record);

    int insertBasicByBasicInfo(BokValbalTableLog record);

    BokValbalTableLog selectByPrimaryKey(BokValbalTableLogKey key);

    int updateByPrimaryKeySelective(BokValbalTableLog record);

    int updateByPrimaryKey(BokValbalTableLog record);
}