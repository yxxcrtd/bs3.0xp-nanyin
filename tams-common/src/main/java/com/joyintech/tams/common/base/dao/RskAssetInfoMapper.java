package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.RskAssetInfo;
import org.springframework.stereotype.Repository;

@Repository("riskRskAssetInfoMapper")
public interface RskAssetInfoMapper {
    int deleteByPrimaryKey(String assetUuid);

    int insert(RskAssetInfo record);

    int insertSelective(RskAssetInfo record);

    int insertBasicByBasicInfo(RskAssetInfo record);

    RskAssetInfo selectByPrimaryKey(String assetUuid);

    int updateByPrimaryKeySelective(RskAssetInfo record);

    int updateByPrimaryKey(RskAssetInfo record);
}