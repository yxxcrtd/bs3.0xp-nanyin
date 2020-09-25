package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.PrdPlanInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface PrdPlanInfoMapper {
    int deleteByPrimaryKey(String prdPlanCode);

    int insert(PrdPlanInfo record);

    int insertSelective(PrdPlanInfo record);

    int insertBasicByBasicInfo(PrdPlanInfo record);

    PrdPlanInfo selectByPrimaryKey(String prdPlanCode);

    int updateByPrimaryKeySelective(PrdPlanInfo record);

    int updateByPrimaryKey(PrdPlanInfo record);
}