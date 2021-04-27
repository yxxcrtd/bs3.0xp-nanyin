package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.HxProfit;

public interface HxProfitMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HxProfit record);

    int insertSelective(HxProfit record);

    HxProfit selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HxProfit record);

    int updateByPrimaryKey(HxProfit record);
}