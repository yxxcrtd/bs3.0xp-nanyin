package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.RegFinProductDealTotal;
import org.springframework.stereotype.Repository;

@Repository
public interface RegFinProductDealTotalMapper {
    int deleteByPrimaryKey(String totleTradeId);

    int insert(RegFinProductDealTotal record);

    int insertSelective(RegFinProductDealTotal record);

    int insertBasicByBasicInfo(RegFinProductDealTotal record);

    RegFinProductDealTotal selectByPrimaryKey(String totleTradeId);

    int updateByPrimaryKeySelective(RegFinProductDealTotal record);

    int updateByPrimaryKey(RegFinProductDealTotal record);
}