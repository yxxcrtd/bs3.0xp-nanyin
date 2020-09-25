package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.PtlSecPosition;
import com.joyintech.tams.common.base.entity.PtlSecPositionKey;
import org.springframework.stereotype.Repository;

@Repository
public interface PtlSecPositionMapper {
    int deleteByPrimaryKey(PtlSecPositionKey key);

    int insert(PtlSecPosition record);

    int insertSelective(PtlSecPosition record);

    int insertBasicByBasicInfo(PtlSecPosition record);

    PtlSecPosition selectByPrimaryKey(PtlSecPositionKey key);

    int updateByPrimaryKeySelective(PtlSecPosition record);

    int updateByPrimaryKey(PtlSecPosition record);
}