package com.joyintech.service.report.poc.portfolio.ptlmock.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.service.report.poc.portfolio.ptlmock.entity.MockTestTaa;
import com.joyintech.service.report.poc.portfolio.ptlmock.entity.MockTestTaaKey;

@Repository
public interface MockTestTaaMapper {
    int deleteByPrimaryKey(MockTestTaaKey key);

    int insert(MockTestTaa record);

    int insertSelective(MockTestTaa record);

    int insertBasicByBasicInfo(MockTestTaa record);

    MockTestTaa selectByPrimaryKey(MockTestTaaKey key);

    int updateByPrimaryKeySelective(MockTestTaa record);

    int updateByPrimaryKey(MockTestTaa record);
}