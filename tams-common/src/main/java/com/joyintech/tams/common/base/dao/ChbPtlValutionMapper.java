package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbPtlValution;
import com.joyintech.tams.common.base.entity.ChbPtlValutionKey;

public interface ChbPtlValutionMapper {
    int deleteByPrimaryKey(ChbPtlValutionKey key);

    int insert(ChbPtlValution record);

    int insertSelective(ChbPtlValution record);

    ChbPtlValution selectByPrimaryKey(ChbPtlValutionKey key);

    int updateByPrimaryKeySelective(ChbPtlValution record);

    int updateByPrimaryKey(ChbPtlValution record);
}