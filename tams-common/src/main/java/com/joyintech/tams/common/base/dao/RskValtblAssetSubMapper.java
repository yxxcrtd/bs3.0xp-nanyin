package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.RskValtblAssetSub;
import com.joyintech.tams.common.base.entity.RskValtblAssetSubKey;
import org.springframework.stereotype.Repository;

@Repository
public interface RskValtblAssetSubMapper {
    int deleteByPrimaryKey(RskValtblAssetSubKey key);

    int insert(RskValtblAssetSub record);

    int insertSelective(RskValtblAssetSub record);

    int insertBasicByBasicInfo(RskValtblAssetSub record);

    RskValtblAssetSub selectByPrimaryKey(RskValtblAssetSubKey key);

    int updateByPrimaryKeySelective(RskValtblAssetSub record);

    int updateByPrimaryKey(RskValtblAssetSub record);
}