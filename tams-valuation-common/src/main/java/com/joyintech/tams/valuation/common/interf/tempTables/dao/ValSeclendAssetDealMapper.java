package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValSeclendAssetDeal;
import org.springframework.stereotype.Repository;

@Repository
public interface ValSeclendAssetDealMapper {
    int insert(ValSeclendAssetDeal record);

    int insertSelective(ValSeclendAssetDeal record);

    int insertBasicByBasicInfo(ValSeclendAssetDeal record);
}