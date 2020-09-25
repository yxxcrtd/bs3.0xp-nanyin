package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.MstTradeCalendar;
import com.joyintech.tams.common.base.entity.MstTradeCalendarKey;
import org.springframework.stereotype.Repository;

@Repository
public interface MstTradeCalendarMapper {
    int deleteByPrimaryKey(MstTradeCalendarKey key);

    int insert(MstTradeCalendar record);

    int insertSelective(MstTradeCalendar record);

    int insertBasicByBasicInfo(MstTradeCalendar record);

    MstTradeCalendar selectByPrimaryKey(MstTradeCalendarKey key);

    int updateByPrimaryKeySelective(MstTradeCalendar record);

    int updateByPrimaryKey(MstTradeCalendar record);
}