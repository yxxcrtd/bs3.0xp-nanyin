package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.BokBooksetPlan;
import org.springframework.stereotype.Repository;

@Repository
public interface BokBooksetPlanMapper {
    int deleteByPrimaryKey(String booksetSchemeId);

    int insert(BokBooksetPlan record);

    int insertSelective(BokBooksetPlan record);

    int insertBasicByBasicInfo(BokBooksetPlan record);

    BokBooksetPlan selectByPrimaryKey(String booksetSchemeId);

    int updateByPrimaryKeySelective(BokBooksetPlan record);

    int updateByPrimaryKey(BokBooksetPlan record);
}