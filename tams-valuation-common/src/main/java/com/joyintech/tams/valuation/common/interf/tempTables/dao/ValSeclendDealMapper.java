package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValSeclendDeal;
import org.springframework.stereotype.Repository;

@Repository
public interface ValSeclendDealMapper {
    int insert(ValSeclendDeal record);

    int insertSelective(ValSeclendDeal record);

    int insertBasicByBasicInfo(ValSeclendDeal record);
}