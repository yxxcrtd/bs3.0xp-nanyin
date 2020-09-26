package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValSeclendAssetPeriodDeal;
import org.springframework.stereotype.Repository;

@Repository
public interface ValSeclendAssetPeriodDealMapper {
    int insert(ValSeclendAssetPeriodDeal record);

    int insertSelective(ValSeclendAssetPeriodDeal record);

    int insertBasicByBasicInfo(ValSeclendAssetPeriodDeal record);
}