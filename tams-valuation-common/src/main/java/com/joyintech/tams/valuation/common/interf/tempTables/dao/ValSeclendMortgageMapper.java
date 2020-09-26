package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValSeclendMortgage;

@Repository
public interface ValSeclendMortgageMapper {
    int insert(ValSeclendMortgage record);

    int insertSelective(ValSeclendMortgage record);

    int insertBasicByBasicInfo(ValSeclendMortgage record);
}