package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.PtlRecePosition;
import com.joyintech.tams.common.base.entity.PtlRecePositionKey;
import org.springframework.stereotype.Repository;

@Repository
public interface PtlRecePositionMapper {
    int deleteByPrimaryKey(PtlRecePositionKey key);

    int insert(PtlRecePosition record);

    int insertSelective(PtlRecePosition record);

    int insertBasicByBasicInfo(PtlRecePosition record);

    PtlRecePosition selectByPrimaryKey(PtlRecePositionKey key);

    int updateByPrimaryKeySelective(PtlRecePosition record);

    int updateByPrimaryKey(PtlRecePosition record);
}