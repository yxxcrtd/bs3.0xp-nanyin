package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.MockTest;
import org.springframework.stereotype.Repository;

@Repository
public interface MockTest002Mapper {
    int deleteByPrimaryKey(String mockId);

    int insert(MockTest record);

    int insertSelective(MockTest record);

    int insertBasicByBasicInfo(MockTest record);

    MockTest selectByPrimaryKey(String mockId);

    int updateByPrimaryKeySelective(MockTest record);

    int updateByPrimaryKey(MockTest record);
}