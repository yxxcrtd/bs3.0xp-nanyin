package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValFeeDeal;

@Repository
public interface ValFeeDealMapper {
    int insert(ValFeeDeal record);

    int insertSelective(ValFeeDeal record);

    int insertBasicByBasicInfo(ValFeeDeal record);
}