package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.PtlSecPierceValution;
import com.joyintech.tams.common.base.entity.PtlSecPierceValutionKey;
import org.springframework.stereotype.Repository;

@Repository
public interface PtlSecPierceValutionMapper {
    int deleteByPrimaryKey(PtlSecPierceValutionKey key);

    int insert(PtlSecPierceValution record);

    int insertSelective(PtlSecPierceValution record);

    int insertBasicByBasicInfo(PtlSecPierceValution record);

    PtlSecPierceValution selectByPrimaryKey(PtlSecPierceValutionKey key);

    int updateByPrimaryKeySelective(PtlSecPierceValution record);

    int updateByPrimaryKey(PtlSecPierceValution record);
}