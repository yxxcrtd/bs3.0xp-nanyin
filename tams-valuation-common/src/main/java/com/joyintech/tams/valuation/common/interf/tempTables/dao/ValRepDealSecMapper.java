package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValRepDealSec;
import org.springframework.stereotype.Repository;

@Repository
public interface ValRepDealSecMapper {
    int deleteByPrimaryKey(String prodId);

    int insert(ValRepDealSec record);

    int insertSelective(ValRepDealSec record);

    int insertBasicByBasicInfo(ValRepDealSec record);

    ValRepDealSec selectByPrimaryKey(String prodId);

    int updateByPrimaryKeySelective(ValRepDealSec record);

    int updateByPrimaryKey(ValRepDealSec record);
}