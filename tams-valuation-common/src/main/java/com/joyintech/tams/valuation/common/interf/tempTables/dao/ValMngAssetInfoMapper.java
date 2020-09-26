package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValMngAssetInfo;

@Repository
public interface ValMngAssetInfoMapper {
    int deleteByPrimaryKey(String assetId);

    int insert(ValMngAssetInfo record);

    int insertSelective(ValMngAssetInfo record);

    int insertBasicByBasicInfo(ValMngAssetInfo record);

    ValMngAssetInfo selectByPrimaryKey(String assetId);

    int updateByPrimaryKeySelective(ValMngAssetInfo record);

    int updateByPrimaryKey(ValMngAssetInfo record);
}