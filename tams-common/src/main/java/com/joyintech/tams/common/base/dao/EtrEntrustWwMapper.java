package com.joyintech.tams.common.base.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.EtrEntrustWw;

@Repository
public interface EtrEntrustWwMapper {
    int deleteByPrimaryKey(String entrustId);

    int insert(EtrEntrustWw record);

    int insertSelective(EtrEntrustWw record);

    int insertBasicByBasicInfo(EtrEntrustWw record);

    EtrEntrustWw selectByPrimaryKey(String entrustId);

    int updateByPrimaryKeySelective(EtrEntrustWw record);

    int updateByPrimaryKey(EtrEntrustWw record);
}