package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.RepCashYield;
import org.springframework.stereotype.Repository;

@Repository
public interface RepCashYieldMapper {
    int insert(RepCashYield record);

    int insertSelective(RepCashYield record);

    int insertBasicByBasicInfo(RepCashYield record);
}