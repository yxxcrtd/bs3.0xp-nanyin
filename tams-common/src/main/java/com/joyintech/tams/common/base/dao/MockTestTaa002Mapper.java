package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.MockTestTaa;
import com.joyintech.tams.common.base.entity.MockTestTaaKey;
import org.springframework.stereotype.Repository;

@Repository
public interface MockTestTaa002Mapper {
    int deleteByPrimaryKey(MockTestTaaKey key);

    int insert(MockTestTaa record);

    int insertSelective(MockTestTaa record);

    int insertBasicByBasicInfo(MockTestTaa record);

    MockTestTaa selectByPrimaryKey(MockTestTaaKey key);

    int updateByPrimaryKeySelective(MockTestTaa record);

    int updateByPrimaryKey(MockTestTaa record);
}