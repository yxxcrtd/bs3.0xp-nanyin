package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.MockTradeProduct;
import com.joyintech.tams.common.base.entity.MockTradeProductKey;
import org.springframework.stereotype.Repository;

@Repository
public interface MockTradeProduct002Mapper {
    int deleteByPrimaryKey(MockTradeProductKey key);

    int insert(MockTradeProduct record);

    int insertSelective(MockTradeProduct record);

    int insertBasicByBasicInfo(MockTradeProduct record);

    MockTradeProduct selectByPrimaryKey(MockTradeProductKey key);

    int updateByPrimaryKeySelective(MockTradeProduct record);

    int updateByPrimaryKey(MockTradeProduct record);
}