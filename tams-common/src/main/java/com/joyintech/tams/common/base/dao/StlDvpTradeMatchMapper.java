package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.StlDvpTradeMatch;
import com.joyintech.tams.common.base.entity.StlDvpTradeMatchKey;
import org.springframework.stereotype.Repository;

@Repository
public interface StlDvpTradeMatchMapper {
    int deleteByPrimaryKey(StlDvpTradeMatchKey key);

    int insert(StlDvpTradeMatch record);

    int insertSelective(StlDvpTradeMatch record);

    int insertBasicByBasicInfo(StlDvpTradeMatch record);

    StlDvpTradeMatch selectByPrimaryKey(StlDvpTradeMatchKey key);

    int updateByPrimaryKeySelective(StlDvpTradeMatch record);

    int updateByPrimaryKey(StlDvpTradeMatch record);
}