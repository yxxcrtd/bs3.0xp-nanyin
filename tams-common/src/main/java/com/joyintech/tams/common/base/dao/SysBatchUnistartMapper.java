package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.SysBatchUnistart;
import org.springframework.stereotype.Repository;

@Repository
public interface SysBatchUnistartMapper {
    int deleteByPrimaryKey(String seqNo);

    int insert(SysBatchUnistart record);

    int insertSelective(SysBatchUnistart record);

    int insertBasicByBasicInfo(SysBatchUnistart record);

    SysBatchUnistart selectByPrimaryKey(String seqNo);

    int updateByPrimaryKeySelective(SysBatchUnistart record);

    int updateByPrimaryKey(SysBatchUnistart record);
}