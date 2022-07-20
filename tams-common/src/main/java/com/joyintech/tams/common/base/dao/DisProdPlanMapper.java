package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.DisProdPlanInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface DisProdPlanMapper {
    int deleteByPrimaryKey(String prodPlanId);

    int insert(DisProdPlanInfo record);

    int insertSelective(DisProdPlanInfo record);

    int insertBasicByBasicInfo(DisProdPlanInfo record);

    DisProdPlanInfo selectByPrimaryKey(String prodPlanId);

    int updateByPrimaryKeySelective(DisProdPlanInfo record);

    int updateByPrimaryKey(DisProdPlanInfo record);
}