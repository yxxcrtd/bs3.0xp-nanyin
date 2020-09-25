package com.joyintech.tams.common.base.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.RskIsupIndicatorParaDtl;
import com.joyintech.tams.common.base.entity.RskIsupIndicatorParaDtlKey;

@Repository
public interface RskIsupIndicatorParaDtlMapper {

    int deleteByPrimaryKey(RskIsupIndicatorParaDtlKey key);

    int insert(RskIsupIndicatorParaDtl record);

    int insertSelective(RskIsupIndicatorParaDtl record);

    int insertBasicByBasicInfo(RskIsupIndicatorParaDtl record);

    RskIsupIndicatorParaDtl selectByPrimaryKey(RskIsupIndicatorParaDtlKey key);

    int updateByPrimaryKeySelective(RskIsupIndicatorParaDtl record);

    int updateByPrimaryKey(RskIsupIndicatorParaDtl record);
}