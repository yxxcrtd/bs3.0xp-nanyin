package com.joyintech.tams.common.base.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.PtlParamPoolConfig;
import com.joyintech.tams.common.base.entity.PtlParamPoolConfigKey;

@Repository
public interface PtlParamPoolConfigMapper {

    int deleteByPrimaryKey(PtlParamPoolConfigKey key);

    int insert(PtlParamPoolConfig record);

    int insertSelective(PtlParamPoolConfig record);

    int insertBasicByBasicInfo(PtlParamPoolConfig record);

    PtlParamPoolConfig selectByPrimaryKey(PtlParamPoolConfigKey key);

    int updateByPrimaryKeySelective(PtlParamPoolConfig record);

    int updateByPrimaryKey(PtlParamPoolConfig record);
}