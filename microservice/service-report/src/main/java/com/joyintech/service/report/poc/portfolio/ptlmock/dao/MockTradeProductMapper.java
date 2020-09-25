package com.joyintech.service.report.poc.portfolio.ptlmock.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.service.report.poc.portfolio.ptlmock.entity.MockTradeProduct;
import com.joyintech.service.report.poc.portfolio.ptlmock.entity.MockTradeProductKey;

@Repository
public interface MockTradeProductMapper {
    int deleteByPrimaryKey(MockTradeProductKey key);

    int insert(MockTradeProduct record);

    int insertSelective(MockTradeProduct record);

    int insertBasicByBasicInfo(MockTradeProduct record);

    MockTradeProduct selectByPrimaryKey(MockTradeProductKey key);

    int updateByPrimaryKeySelective(MockTradeProduct record);

    int updateByPrimaryKey(MockTradeProduct record);
}