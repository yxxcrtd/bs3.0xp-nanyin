package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.BokTablelist;
import com.joyintech.tams.common.base.entity.BokTablelistKey;
import org.springframework.stereotype.Repository;

@Repository
public interface BokTablelistMapper {
    int deleteByPrimaryKey(BokTablelistKey key);

    int insert(BokTablelist record);

    int insertSelective(BokTablelist record);

    int insertBasicByBasicInfo(BokTablelist record);

    BokTablelist selectByPrimaryKey(BokTablelistKey key);

    int updateByPrimaryKeySelective(BokTablelist record);

    int updateByPrimaryKey(BokTablelist record);
}