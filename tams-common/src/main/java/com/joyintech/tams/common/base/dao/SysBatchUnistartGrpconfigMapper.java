package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.SysBatchUnistartGrpconfig;
import com.joyintech.tams.common.base.entity.SysBatchUnistartGrpconfigKey;
import org.springframework.stereotype.Repository;

@Repository
public interface SysBatchUnistartGrpconfigMapper {
    int deleteByPrimaryKey(SysBatchUnistartGrpconfigKey key);

    int insert(SysBatchUnistartGrpconfig record);

    int insertSelective(SysBatchUnistartGrpconfig record);

    int insertBasicByBasicInfo(SysBatchUnistartGrpconfig record);

    SysBatchUnistartGrpconfig selectByPrimaryKey(SysBatchUnistartGrpconfigKey key);

    int updateByPrimaryKeySelective(SysBatchUnistartGrpconfig record);

    int updateByPrimaryKey(SysBatchUnistartGrpconfig record);
}