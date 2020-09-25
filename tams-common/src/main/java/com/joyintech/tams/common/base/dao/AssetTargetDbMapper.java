package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.AssetTargetDb;
import com.joyintech.tams.common.base.entity.AssetTargetDbKey;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetTargetDbMapper {
    int deleteByPrimaryKey(AssetTargetDbKey key);

    int insert(AssetTargetDb record);

    int insertSelective(AssetTargetDb record);

    int insertBasicByBasicInfo(AssetTargetDb record);

    AssetTargetDb selectByPrimaryKey(AssetTargetDbKey key);

    int updateByPrimaryKeySelective(AssetTargetDb record);

    int updateByPrimaryKey(AssetTargetDb record);
}