package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValPrdFeeDeal;

@Repository
public interface ValPrdFeeDealMapper {
    int insert(ValPrdFeeDeal record);

    int insertSelective(ValPrdFeeDeal record);

    int insertBasicByBasicInfo(ValPrdFeeDeal record);
}