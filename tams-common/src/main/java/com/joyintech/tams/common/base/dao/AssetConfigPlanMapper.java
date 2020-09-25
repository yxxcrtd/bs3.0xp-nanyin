package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.AssetConfigPlan;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetConfigPlanMapper {
    int deleteByPrimaryKey(String assetConfigCode);

    int insert(AssetConfigPlan record);

    int insertSelective(AssetConfigPlan record);

    int insertBasicByBasicInfo(AssetConfigPlan record);

    AssetConfigPlan selectByPrimaryKey(String assetConfigCode);

    int updateByPrimaryKeySelective(AssetConfigPlan record);

    int updateByPrimaryKey(AssetConfigPlan record);
}