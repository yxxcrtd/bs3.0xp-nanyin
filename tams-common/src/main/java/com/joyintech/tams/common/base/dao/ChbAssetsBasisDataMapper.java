package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbAssetsBasisData;
import org.springframework.stereotype.Repository;

@Repository
public interface ChbAssetsBasisDataMapper {
    int deleteByPrimaryKey(String valId);

    int insert(ChbAssetsBasisData record);

    int insertSelective(ChbAssetsBasisData record);

    int insertBasicByBasicInfo(ChbAssetsBasisData record);

    ChbAssetsBasisData selectByPrimaryKey(String valId);

    int updateByPrimaryKeySelective(ChbAssetsBasisData record);

    int updateByPrimaryKey(ChbAssetsBasisData record);
}