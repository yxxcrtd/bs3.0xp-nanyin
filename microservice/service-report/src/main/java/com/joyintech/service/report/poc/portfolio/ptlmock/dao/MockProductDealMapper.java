package com.joyintech.service.report.poc.portfolio.ptlmock.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.service.report.poc.portfolio.ptlmock.entity.MockProductDeal;

@Repository
public interface MockProductDealMapper {
    int deleteByPrimaryKey(String tradeId);

    int insert(MockProductDeal record);

    int insertSelective(MockProductDeal record);

    int insertBasicByBasicInfo(MockProductDeal record);

    MockProductDeal selectByPrimaryKey(String tradeId);

    int updateByPrimaryKeySelective(MockProductDeal record);

    int updateByPrimaryKey(MockProductDeal record);
}