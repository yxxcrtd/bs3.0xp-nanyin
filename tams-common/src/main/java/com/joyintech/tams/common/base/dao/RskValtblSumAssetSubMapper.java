package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.RskValtblSumAssetSub;
import com.joyintech.tams.common.base.entity.RskValtblSumAssetSubKey;
import org.springframework.stereotype.Repository;

@Repository
public interface RskValtblSumAssetSubMapper {
    int deleteByPrimaryKey(RskValtblSumAssetSubKey key);

    int insert(RskValtblSumAssetSub record);

    int insertSelective(RskValtblSumAssetSub record);

    int insertBasicByBasicInfo(RskValtblSumAssetSub record);

    RskValtblSumAssetSub selectByPrimaryKey(RskValtblSumAssetSubKey key);

    int updateByPrimaryKeySelective(RskValtblSumAssetSub record);

    int updateByPrimaryKey(RskValtblSumAssetSub record);
}