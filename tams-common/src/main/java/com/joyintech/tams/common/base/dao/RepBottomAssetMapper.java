package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.RepBottomAsset;
import com.joyintech.tams.common.base.entity.RepBottomAssetKey;
import org.springframework.stereotype.Repository;

@Repository
public interface RepBottomAssetMapper {
    int deleteByPrimaryKey(RepBottomAssetKey key);

    int insert(RepBottomAsset record);

    int insertSelective(RepBottomAsset record);

    int insertBasicByBasicInfo(RepBottomAsset record);

    RepBottomAsset selectByPrimaryKey(RepBottomAssetKey key);

    int updateByPrimaryKeySelective(RepBottomAsset record);

    int updateByPrimaryKey(RepBottomAsset record);
}