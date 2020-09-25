package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.SysBatchUnistartDetail;
import com.joyintech.tams.common.base.entity.SysBatchUnistartDetailKey;
import org.springframework.stereotype.Repository;

@Repository
public interface SysBatchUnistartDetailMapper {
    int deleteByPrimaryKey(SysBatchUnistartDetailKey key);

    int insert(SysBatchUnistartDetail record);

    int insertSelective(SysBatchUnistartDetail record);

    int insertBasicByBasicInfo(SysBatchUnistartDetail record);

    SysBatchUnistartDetail selectByPrimaryKey(SysBatchUnistartDetailKey key);

    int updateByPrimaryKeySelective(SysBatchUnistartDetail record);

    int updateByPrimaryKey(SysBatchUnistartDetail record);
}