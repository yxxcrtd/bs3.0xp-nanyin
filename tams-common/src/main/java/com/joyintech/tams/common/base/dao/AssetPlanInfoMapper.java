package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.AssetPlanInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetPlanInfoMapper {
    int deleteByPrimaryKey(String assetPlanCode);

    int insert(AssetPlanInfo record);

    int insertSelective(AssetPlanInfo record);

    int insertBasicByBasicInfo(AssetPlanInfo record);

    AssetPlanInfo selectByPrimaryKey(String assetPlanCode);

    int updateByPrimaryKeySelective(AssetPlanInfo record);

    int updateByPrimaryKey(AssetPlanInfo record);
}