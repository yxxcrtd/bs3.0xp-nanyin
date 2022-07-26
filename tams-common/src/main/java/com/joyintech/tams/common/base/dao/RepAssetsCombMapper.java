package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.RepAssetsComb;
import com.joyintech.tams.common.base.entity.RepAssetsCombKey;
import org.springframework.stereotype.Repository;

@Repository
public interface RepAssetsCombMapper {
    int deleteByPrimaryKey(RepAssetsCombKey key);

    int insert(RepAssetsComb record);

    int insertSelective(RepAssetsComb record);

    int insertBasicByBasicInfo(RepAssetsComb record);

    RepAssetsComb selectByPrimaryKey(RepAssetsCombKey key);

    int updateByPrimaryKeySelective(RepAssetsComb record);

    int updateByPrimaryKey(RepAssetsComb record);
}