package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbAssetInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface ChbAssetInfoMapper {
    int deleteByPrimaryKey(String assetUuid);

    int insert(ChbAssetInfo record);

    int insertSelective(ChbAssetInfo record);

    int insertBasicByBasicInfo(ChbAssetInfo record);

    ChbAssetInfo selectByPrimaryKey(String assetUuid);

    int updateByPrimaryKeySelective(ChbAssetInfo record);

    int updateByPrimaryKey(ChbAssetInfo record);
}