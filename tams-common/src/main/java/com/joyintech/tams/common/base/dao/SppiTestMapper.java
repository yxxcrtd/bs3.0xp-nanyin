package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.SppiTest;
import org.springframework.stereotype.Repository;

@Repository
public interface SppiTestMapper {
    int deleteByPrimaryKey(String sppiTestId);

    int insert(SppiTest record);

    int insertSelective(SppiTest record);

    int insertBasicByBasicInfo(SppiTest record);

    SppiTest selectByPrimaryKey(String sppiTestId);

    int updateByPrimaryKeySelective(SppiTest record);

    int updateByPrimaryKey(SppiTest record);
}