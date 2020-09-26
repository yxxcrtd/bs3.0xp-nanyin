package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValMngAssetInfoN;

@Repository
public interface ValMngAssetInfoNMapper {
    int deleteByPrimaryKey(String assetId);

    int insert(ValMngAssetInfoN record);

    int insertSelective(ValMngAssetInfoN record);

    int insertBasicByBasicInfo(ValMngAssetInfoN record);

    ValMngAssetInfoN selectByPrimaryKey(String assetId);

    int updateByPrimaryKeySelective(ValMngAssetInfoN record);

    int updateByPrimaryKey(ValMngAssetInfoN record);
}