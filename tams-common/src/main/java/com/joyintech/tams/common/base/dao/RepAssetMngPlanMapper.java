package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.RepAssetMngPlan;
import org.springframework.stereotype.Repository;

@Repository
public interface RepAssetMngPlanMapper {
    int deleteByPrimaryKey(String repFinprodId);

    int insert(RepAssetMngPlan record);

    int insertSelective(RepAssetMngPlan record);

    int insertBasicByBasicInfo(RepAssetMngPlan record);

    RepAssetMngPlan selectByPrimaryKey(String repFinprodId);

    int updateByPrimaryKeySelective(RepAssetMngPlan record);

    int updateByPrimaryKey(RepAssetMngPlan record);
}