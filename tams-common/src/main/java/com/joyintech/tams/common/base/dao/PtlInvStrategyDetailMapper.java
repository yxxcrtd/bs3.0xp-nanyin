package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.PtlInvStrategyDetail;
import org.springframework.stereotype.Repository;

@Repository
public interface PtlInvStrategyDetailMapper {
    int deleteByPrimaryKey(String batchId);

    int insert(PtlInvStrategyDetail record);

    int insertSelective(PtlInvStrategyDetail record);

    int insertBasicByBasicInfo(PtlInvStrategyDetail record);

    PtlInvStrategyDetail selectByPrimaryKey(String batchId);

    int updateByPrimaryKeySelective(PtlInvStrategyDetail record);

    int updateByPrimaryKey(PtlInvStrategyDetail record);
}