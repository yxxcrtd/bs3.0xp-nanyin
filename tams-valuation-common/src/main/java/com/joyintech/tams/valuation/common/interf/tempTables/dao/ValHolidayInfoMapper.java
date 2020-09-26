package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValHolidayInfo;

@Repository
public interface ValHolidayInfoMapper {
    int deleteByPrimaryKey(String tradeMarket);

    int insert(ValHolidayInfo record);

    int insertSelective(ValHolidayInfo record);

    int insertBasicByBasicInfo(ValHolidayInfo record);

    ValHolidayInfo selectByPrimaryKey(String tradeMarket);

    int updateByPrimaryKeySelective(ValHolidayInfo record);

    int updateByPrimaryKey(ValHolidayInfo record);
}