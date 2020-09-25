package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.MockTradeRate;
import com.joyintech.tams.common.base.entity.MockTradeRateKey;
import org.springframework.stereotype.Repository;

@Repository
public interface MockTradeRateMapper {
    int deleteByPrimaryKey(MockTradeRateKey key);

    int insert(MockTradeRate record);

    int insertSelective(MockTradeRate record);

    int insertBasicByBasicInfo(MockTradeRate record);

    MockTradeRate selectByPrimaryKey(MockTradeRateKey key);

    int updateByPrimaryKeySelective(MockTradeRate record);

    int updateByPrimaryKey(MockTradeRate record);
}