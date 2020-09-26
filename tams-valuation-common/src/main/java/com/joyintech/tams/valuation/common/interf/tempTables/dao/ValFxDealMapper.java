package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValFxDeal;
import org.springframework.stereotype.Repository;

@Repository
public interface ValFxDealMapper {
    int deleteByPrimaryKey(String prodId);

    int insert(ValFxDeal record);

    int insertSelective(ValFxDeal record);

    int insertBasicByBasicInfo(ValFxDeal record);

    ValFxDeal selectByPrimaryKey(String prodId);

    int updateByPrimaryKeySelective(ValFxDeal record);

    int updateByPrimaryKey(ValFxDeal record);
}