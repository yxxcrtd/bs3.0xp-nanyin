package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.PtlSecValution;
import com.joyintech.tams.common.base.entity.PtlSecValutionKey;
import org.springframework.stereotype.Repository;

@Repository
public interface PtlSecValutionMapper {
    int deleteByPrimaryKey(PtlSecValutionKey key);

    int insert(PtlSecValution record);

    int insertSelective(PtlSecValution record);

    int insertBasicByBasicInfo(PtlSecValution record);

    PtlSecValution selectByPrimaryKey(PtlSecValutionKey key);

    int updateByPrimaryKeySelective(PtlSecValution record);

    int updateByPrimaryKey(PtlSecValution record);
}