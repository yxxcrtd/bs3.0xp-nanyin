package com.joyintech.tams.common.base.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.RskIsupLogKey;
import com.joyintech.tams.common.base.entity.RskIsupLog;

@Repository
public interface RskIsupLogMapper {

    int deleteByPrimaryKey(RskIsupLogKey key);

    int insert(RskIsupLog record);

    int insertSelective(RskIsupLog record);

    int insertBasicByBasicInfo(RskIsupLog record);

    RskIsupLog selectByPrimaryKey(RskIsupLogKey key);

    int updateByPrimaryKeySelective(RskIsupLog record);

    int updateByPrimaryKeyWithBLOBs(RskIsupLog record);

    int updateByPrimaryKey(RskIsupLog record);
}