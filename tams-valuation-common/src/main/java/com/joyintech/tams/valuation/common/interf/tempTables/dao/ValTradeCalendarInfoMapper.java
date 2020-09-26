package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValTradeCalendarInfo;

@Repository
public interface ValTradeCalendarInfoMapper {
    int insert(ValTradeCalendarInfo record);

    int insertSelective(ValTradeCalendarInfo record);

    int insertBasicByBasicInfo(ValTradeCalendarInfo record);
}