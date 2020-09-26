package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValRepDealAsset;

@Repository
public interface ValRepDealAssetMapper {
    int deleteByPrimaryKey(String tradeId);

    int insert(ValRepDealAsset record);

    int insertSelective(ValRepDealAsset record);

    int insertBasicByBasicInfo(ValRepDealAsset record);

    ValRepDealAsset selectByPrimaryKey(String tradeId);

    int updateByPrimaryKeySelective(ValRepDealAsset record);

    int updateByPrimaryKey(ValRepDealAsset record);
}