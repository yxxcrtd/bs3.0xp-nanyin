package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.TrdProductDeal;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrdProductDealMapper {
    int deleteByPrimaryKey(String tradeId);

    int insert(TrdProductDeal record);

    int insertSelective(TrdProductDeal record);

    int insertBasicByBasicInfo(TrdProductDeal record);

    TrdProductDeal selectByPrimaryKey(String tradeId);

    int updateByPrimaryKeySelective(TrdProductDeal record);

    int updateByPrimaryKey(TrdProductDeal record);

    List<TrdProductDeal> selectByTrade(TrdProductDeal deal);
}