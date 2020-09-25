package com.joyintech.service.report.poc.portfolio.ptlmock.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.service.report.poc.portfolio.ptlmock.entity.MockTest;

@Repository
public interface MockTestMapper {
    int deleteByPrimaryKey(String mockId);

    int insert(MockTest record);

    int insertSelective(MockTest record);

    int insertBasicByBasicInfo(MockTest record);

    MockTest selectByPrimaryKey(String mockId);

    int updateByPrimaryKeySelective(MockTest record);

    int updateByPrimaryKey(MockTest record);
}