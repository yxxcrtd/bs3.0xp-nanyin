package com.joyintech.service.report.poc.portfolio.ptlmock.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.service.report.poc.portfolio.ptlmock.entity.MockTestNetTrend;
import com.joyintech.service.report.poc.portfolio.ptlmock.entity.MockTestNetTrendKey;

@Repository
public interface MockTestNetTrendMapper {
    int deleteByPrimaryKey(MockTestNetTrendKey key);

    int insert(MockTestNetTrend record);

    int insertSelective(MockTestNetTrend record);

    int insertBasicByBasicInfo(MockTestNetTrend record);

    MockTestNetTrend selectByPrimaryKey(MockTestNetTrendKey key);

    int updateByPrimaryKeySelective(MockTestNetTrend record);

    int updateByPrimaryKey(MockTestNetTrend record);
}