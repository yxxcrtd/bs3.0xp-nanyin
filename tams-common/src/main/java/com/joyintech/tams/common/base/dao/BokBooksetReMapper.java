package com.joyintech.tams.common.base.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.BokBooksetRe;
import com.joyintech.tams.common.base.entity.BokBooksetReKey;


@Repository
public interface BokBooksetReMapper {
    int deleteByPrimaryKey(BokBooksetReKey key);

    int insert(BokBooksetRe record);

    int insertSelective(BokBooksetRe record);

    int insertBasicByBasicInfo(BokBooksetRe record);

    BokBooksetRe selectByPrimaryKey(BokBooksetReKey key);

    int updateByPrimaryKeySelective(BokBooksetRe record);

    int updateByPrimaryKey(BokBooksetRe record);
}