package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.RepTrade;
import com.joyintech.tams.common.base.entity.RepTradeKey;
import org.springframework.stereotype.Repository;

@Repository
public interface RepTradeMapper {
    int deleteByPrimaryKey(RepTradeKey key);

    int insert(RepTrade record);

    int insertSelective(RepTrade record);

    int insertBasicByBasicInfo(RepTrade record);

    RepTrade selectByPrimaryKey(RepTradeKey key);

    int updateByPrimaryKeySelective(RepTrade record);

    int updateByPrimaryKey(RepTrade record);
}