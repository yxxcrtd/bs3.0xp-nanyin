package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.PtlInvStrategy;
import org.springframework.stereotype.Repository;

@Repository
public interface PtlInvStrategyMapper {
    int deleteByPrimaryKey(String strategyId);

    int insert(PtlInvStrategy record);

    int insertSelective(PtlInvStrategy record);

    int insertBasicByBasicInfo(PtlInvStrategy record);

    PtlInvStrategy selectByPrimaryKey(String strategyId);

    int updateByPrimaryKeySelective(PtlInvStrategy record);

    int updateByPrimaryKey(PtlInvStrategy record);
}