package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValSecVestPeriod;
import org.springframework.stereotype.Repository;

@Repository
public interface ValSecVestPeriodMapper {
    int deleteByPrimaryKey(String prodId);

    int insert(ValSecVestPeriod record);

    int insertSelective(ValSecVestPeriod record);

    int insertBasicByBasicInfo(ValSecVestPeriod record);

    ValSecVestPeriod selectByPrimaryKey(String prodId);

    int updateByPrimaryKeySelective(ValSecVestPeriod record);

    int updateByPrimaryKey(ValSecVestPeriod record);
}