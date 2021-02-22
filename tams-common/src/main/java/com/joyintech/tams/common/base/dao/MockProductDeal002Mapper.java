package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.MockProductDeal;
import org.springframework.stereotype.Repository;

@Repository
public interface MockProductDeal002Mapper {
    int deleteByPrimaryKey(String tradeId);

    int insert(MockProductDeal record);

    int insertSelective(MockProductDeal record);

    int insertBasicByBasicInfo(MockProductDeal record);

    MockProductDeal selectByPrimaryKey(String tradeId);

    int updateByPrimaryKeySelective(MockProductDeal record);

    int updateByPrimaryKey(MockProductDeal record);
}