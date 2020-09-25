package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.RskFacilityAdjPlan;
import com.joyintech.tams.common.base.entity.RskFacilityAdjPlanKey;
import org.springframework.stereotype.Repository;

@Repository
public interface RskFacilityAdjPlanMapper {
    int deleteByPrimaryKey(RskFacilityAdjPlanKey key);

    int insert(RskFacilityAdjPlan record);

    int insertSelective(RskFacilityAdjPlan record);

    int insertBasicByBasicInfo(RskFacilityAdjPlan record);

    RskFacilityAdjPlan selectByPrimaryKey(RskFacilityAdjPlanKey key);

    int updateByPrimaryKeySelective(RskFacilityAdjPlan record);

    int updateByPrimaryKey(RskFacilityAdjPlan record);
}