package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.AssetPlanDetail;
import com.joyintech.tams.common.base.entity.AssetPlanDetailKey;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetPlanDetailMapper {
    int deleteByPrimaryKey(AssetPlanDetailKey key);

    int insert(AssetPlanDetail record);

    int insertSelective(AssetPlanDetail record);

    int insertBasicByBasicInfo(AssetPlanDetail record);

    AssetPlanDetail selectByPrimaryKey(AssetPlanDetailKey key);

    int updateByPrimaryKeySelective(AssetPlanDetail record);

    int updateByPrimaryKey(AssetPlanDetail record);
}