package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.BokSchemeAssetConfig;
import com.joyintech.tams.common.base.entity.BokSchemeAssetConfigKey;
import org.springframework.stereotype.Repository;

@Repository
public interface BokSchemeAssetConfigMapper {
    int deleteByPrimaryKey(BokSchemeAssetConfigKey key);

    int insert(BokSchemeAssetConfig record);

    int insertSelective(BokSchemeAssetConfig record);

    int insertBasicByBasicInfo(BokSchemeAssetConfig record);

    BokSchemeAssetConfig selectByPrimaryKey(BokSchemeAssetConfigKey key);

    int updateByPrimaryKeySelective(BokSchemeAssetConfig record);

    int updateByPrimaryKey(BokSchemeAssetConfig record);
}