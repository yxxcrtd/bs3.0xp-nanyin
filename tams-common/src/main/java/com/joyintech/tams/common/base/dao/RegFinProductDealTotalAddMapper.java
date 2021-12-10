package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.RegFinProductDealTotalAdd;
import org.springframework.stereotype.Repository;

@Repository
public interface RegFinProductDealTotalAddMapper {
    int deleteByPrimaryKey(String totleTradeId);

    int insert(RegFinProductDealTotalAdd record);

    int insertSelective(RegFinProductDealTotalAdd record);

    int insertBasicByBasicInfo(RegFinProductDealTotalAdd record);

    RegFinProductDealTotalAdd selectByPrimaryKey(String totleTradeId);

    int updateByPrimaryKeySelective(RegFinProductDealTotalAdd record);

    int updateByPrimaryKey(RegFinProductDealTotalAdd record);
}