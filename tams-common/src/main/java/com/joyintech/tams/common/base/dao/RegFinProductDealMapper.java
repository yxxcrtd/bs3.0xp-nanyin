package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.RegFinProductDeal;
import org.springframework.stereotype.Repository;

@Repository
public interface RegFinProductDealMapper {
    int deleteByPrimaryKey(String tradeId);

    int insert(RegFinProductDeal record);

    int insertSelective(RegFinProductDeal record);

    int insertBasicByBasicInfo(RegFinProductDeal record);

    RegFinProductDeal selectByPrimaryKey(String tradeId);

    int updateByPrimaryKeySelective(RegFinProductDeal record);

    int updateByPrimaryKey(RegFinProductDeal record);
}