package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.MockTestRsk;
import com.joyintech.tams.common.base.entity.MockTestRskKey;
import org.springframework.stereotype.Repository;

@Repository
public interface MockTestRsk002Mapper {
    int deleteByPrimaryKey(MockTestRskKey key);

    int insert(MockTestRsk record);

    int insertSelective(MockTestRsk record);

    int insertBasicByBasicInfo(MockTestRsk record);

    MockTestRsk selectByPrimaryKey(MockTestRskKey key);

    int updateByPrimaryKeySelective(MockTestRsk record);

    int updateByPrimaryKey(MockTestRsk record);
}