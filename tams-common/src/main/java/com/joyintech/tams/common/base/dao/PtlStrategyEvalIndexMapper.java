package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.PtlStrategyEvalIndex;
import org.springframework.stereotype.Repository;

@Repository
public interface PtlStrategyEvalIndexMapper {
    int deleteByPrimaryKey(String strategyEvalId);

    int insert(PtlStrategyEvalIndex record);

    int insertSelective(PtlStrategyEvalIndex record);

    int insertBasicByBasicInfo(PtlStrategyEvalIndex record);

    PtlStrategyEvalIndex selectByPrimaryKey(String strategyEvalId);

    int updateByPrimaryKeySelective(PtlStrategyEvalIndex record);

    int updateByPrimaryKey(PtlStrategyEvalIndex record);
}