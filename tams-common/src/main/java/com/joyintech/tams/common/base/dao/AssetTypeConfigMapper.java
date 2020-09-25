package com.joyintech.tams.common.base.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.AssetTypeConfig;

@Repository
public interface AssetTypeConfigMapper {
    int insert(AssetTypeConfig record);

    int insertSelective(AssetTypeConfig record);

    int insertBasicByBasicInfo(AssetTypeConfig record);
    
    AssetTypeConfig selectAssetTypeConfig(Map<String,Object> map);
}