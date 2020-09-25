package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.InterfCompintroduction;
import org.springframework.stereotype.Repository;

@Repository
public interface InterfCompintroductionMapper {
    int deleteByPrimaryKey(String objectId);

    int insert(InterfCompintroduction record);

    int insertSelective(InterfCompintroduction record);

    int insertBasicByBasicInfo(InterfCompintroduction record);

    InterfCompintroduction selectByPrimaryKey(String objectId);

    int updateByPrimaryKeySelective(InterfCompintroduction record);

    int updateByPrimaryKey(InterfCompintroduction record);
}