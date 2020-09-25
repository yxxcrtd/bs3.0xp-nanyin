package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbPrdPeriodYield;

public interface ChbPrdPeriodYieldMapper {
    int deleteByPrimaryKey(String seqno);

    int insert(ChbPrdPeriodYield record);

    int insertSelective(ChbPrdPeriodYield record);

    ChbPrdPeriodYield selectByPrimaryKey(String seqno);

    int updateByPrimaryKeySelective(ChbPrdPeriodYield record);

    int updateByPrimaryKey(ChbPrdPeriodYield record);
}