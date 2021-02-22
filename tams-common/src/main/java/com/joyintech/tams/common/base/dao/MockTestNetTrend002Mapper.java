package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.MockTestNetTrend;
import com.joyintech.tams.common.base.entity.MockTestNetTrendKey;
import org.springframework.stereotype.Repository;

@Repository
public interface MockTestNetTrend002Mapper {
    int deleteByPrimaryKey(MockTestNetTrendKey key);

    int insert(MockTestNetTrend record);

    int insertSelective(MockTestNetTrend record);

    int insertBasicByBasicInfo(MockTestNetTrend record);

    MockTestNetTrend selectByPrimaryKey(MockTestNetTrendKey key);

    int updateByPrimaryKeySelective(MockTestNetTrend record);

    int updateByPrimaryKey(MockTestNetTrend record);
}