package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValRepDividendDeal;

@Repository
public interface ValRepDividendDealMapper {
    int deleteByPrimaryKey(String prodId);

    int insert(ValRepDividendDeal record);

    int insertSelective(ValRepDividendDeal record);

    int insertBasicByBasicInfo(ValRepDividendDeal record);

    ValRepDividendDeal selectByPrimaryKey(String prodId);

    int updateByPrimaryKeySelective(ValRepDividendDeal record);

    int updateByPrimaryKey(ValRepDividendDeal record);
}