package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.MockTradeDeal;
import org.springframework.stereotype.Repository;

@Repository
public interface MockTradeDealMapper {
    int deleteByPrimaryKey(String tradeId);

    int insert(MockTradeDeal record);

    int insertSelective(MockTradeDeal record);

    int insertBasicByBasicInfo(MockTradeDeal record);

    MockTradeDeal selectByPrimaryKey(String tradeId);

    int updateByPrimaryKeySelective(MockTradeDeal record);

    int updateByPrimaryKey(MockTradeDeal record);
}