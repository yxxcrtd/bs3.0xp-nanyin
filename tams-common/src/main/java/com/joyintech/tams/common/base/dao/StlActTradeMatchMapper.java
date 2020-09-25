package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.StlActTradeMatch;
import com.joyintech.tams.common.base.entity.StlActTradeMatchKey;
import org.springframework.stereotype.Repository;

@Repository
public interface StlActTradeMatchMapper {
    int deleteByPrimaryKey(StlActTradeMatchKey key);

    int insert(StlActTradeMatch record);

    int insertSelective(StlActTradeMatch record);

    int insertBasicByBasicInfo(StlActTradeMatch record);

    StlActTradeMatch selectByPrimaryKey(StlActTradeMatchKey key);

    int updateByPrimaryKeySelective(StlActTradeMatch record);

    int updateByPrimaryKey(StlActTradeMatch record);
}