package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValNfundRate;

@Repository
public interface ValNfundRateMapper {
    int insert(ValNfundRate record);

    int insertSelective(ValNfundRate record);

    int insertBasicByBasicInfo(ValNfundRate record);
}