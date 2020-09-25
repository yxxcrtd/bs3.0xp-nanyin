package com.joyintech.service.report.poc.portfolio.ptlmock.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.service.report.poc.portfolio.ptlmock.entity.MockTestRsk;
import com.joyintech.service.report.poc.portfolio.ptlmock.entity.MockTestRskKey;

@Repository
public interface MockTestRskMapper {
    int deleteByPrimaryKey(MockTestRskKey key);

    int insert(MockTestRsk record);

    int insertSelective(MockTestRsk record);

    int insertBasicByBasicInfo(MockTestRsk record);

    MockTestRsk selectByPrimaryKey(MockTestRskKey key);

    int updateByPrimaryKeySelective(MockTestRsk record);

    int updateByPrimaryKey(MockTestRsk record);
}