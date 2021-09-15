package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.HxProfit;
import org.springframework.stereotype.Repository;

@Repository
public interface HxProfitMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HxProfit record);

    int insertSelective(HxProfit record);

    HxProfit selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HxProfit record);

    int updateByPrimaryKey(HxProfit record);
}