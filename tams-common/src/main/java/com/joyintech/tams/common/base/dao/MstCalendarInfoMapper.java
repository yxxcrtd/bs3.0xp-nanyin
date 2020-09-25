package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.MstCalendarInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface MstCalendarInfoMapper {
    int deleteByPrimaryKey(String calendarId);

    int insert(MstCalendarInfo record);

    int insertSelective(MstCalendarInfo record);

    int insertBasicByBasicInfo(MstCalendarInfo record);

    MstCalendarInfo selectByPrimaryKey(String calendarId);

    int updateByPrimaryKeySelective(MstCalendarInfo record);

    int updateByPrimaryKey(MstCalendarInfo record);
}