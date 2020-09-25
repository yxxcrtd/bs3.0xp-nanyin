package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbAssetsBasisDataChild;

public interface ChbAssetsBasisDataChildMapper {
    int deleteByPrimaryKey(String valId);

    int insert(ChbAssetsBasisDataChild record);

    int insertSelective(ChbAssetsBasisDataChild record);

    int insertBasicByBasicInfo(ChbAssetsBasisDataChild record);

    ChbAssetsBasisDataChild selectByPrimaryKey(String valId);

    int updateByPrimaryKeySelective(ChbAssetsBasisDataChild record);

    int updateByPrimaryKey(ChbAssetsBasisDataChild record);
    }