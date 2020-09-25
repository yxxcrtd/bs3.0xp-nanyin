package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.RepOtherInvestTarget;
import org.springframework.stereotype.Repository;

@Repository
public interface RepOtherInvestTargetMapper {
    int deleteByPrimaryKey(String repFinprodId);

    int insert(RepOtherInvestTarget record);

    int insertSelective(RepOtherInvestTarget record);

    int insertBasicByBasicInfo(RepOtherInvestTarget record);

    RepOtherInvestTarget selectByPrimaryKey(String repFinprodId);

    int updateByPrimaryKeySelective(RepOtherInvestTarget record);

    int updateByPrimaryKey(RepOtherInvestTarget record);
}