package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.DisProdPlanInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface DisProdPlanMapper {
    int deleteByPrimaryKey(String prodPlanId);

    int insert(DisProdPlanInfo record);

    int insertSelective(DisProdPlanInfo record);

    int insertBasicByBasicInfo(DisProdPlanInfo record);

    DisProdPlanInfo selectByPrimaryKey(String prodPlanId);

    int updateByPrimaryKeySelective(DisProdPlanInfo record);

    int updateByPrimaryKey(DisProdPlanInfo record);

    Date getOperationStartDate(@Param("prodId") String prodId,@Param("platDate") Date platDate);
}