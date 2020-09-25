package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbAssetsBasisData;

public interface ChbAssetsBasisDataMapper {
    int deleteByPrimaryKey(String valId);

    int insert(ChbAssetsBasisData record);
    
    int insertNoBtm(ChbAssetsBasisData record);

    int insertSelective(ChbAssetsBasisData record);

    ChbAssetsBasisData selectByPrimaryKey(String valId);

    int updateByPrimaryKeySelective(ChbAssetsBasisData record);

    int updateByPrimaryKey(ChbAssetsBasisData record);
}