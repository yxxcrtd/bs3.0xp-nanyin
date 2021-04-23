package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.EtrEntrust;
import org.springframework.stereotype.Repository;

@Repository
public interface EtrEntrustMapper {
    int deleteByPrimaryKey(String entrustId);

    int insert(EtrEntrust record);

    int insertSelective(EtrEntrust record);

    int insertBasicByBasicInfo(EtrEntrust record);

    EtrEntrust selectByPrimaryKey(String entrustId);

    int updateByPrimaryKeySelective(EtrEntrust record);

    int updateByPrimaryKey(EtrEntrust record);
}