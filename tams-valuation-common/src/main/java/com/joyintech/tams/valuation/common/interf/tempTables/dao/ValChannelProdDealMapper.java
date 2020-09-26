package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValChannelProdDeal;

@Repository
public interface ValChannelProdDealMapper {
    int insert(ValChannelProdDeal record);

    int insertSelective(ValChannelProdDeal record);

    int insertBasicByBasicInfo(ValChannelProdDeal record);
}