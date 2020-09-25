package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.RiskWeight;
import org.springframework.stereotype.Repository;

@Repository
public interface RiskWeightMapper {
    int deleteByPrimaryKey(String seqno);

    int insert(RiskWeight record);

    int insertSelective(RiskWeight record);

    int insertBasicByBasicInfo(RiskWeight record);

    RiskWeight selectByPrimaryKey(String seqno);

    int updateByPrimaryKeySelective(RiskWeight record);

    int updateByPrimaryKey(RiskWeight record);
}