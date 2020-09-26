package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValChannelProdFeeDeal;

@Repository
public interface ValChannelProdFeeDealMapper {
    int insert(ValChannelProdFeeDeal record);

    int insertSelective(ValChannelProdFeeDeal record);

    int insertBasicByBasicInfo(ValChannelProdFeeDeal record);
}