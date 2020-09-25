package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.MstHoliday;
import com.joyintech.tams.common.base.entity.MstHolidayKey;
import org.springframework.stereotype.Repository;

@Repository
public interface MstHolidayMapper {
    int deleteByPrimaryKey(MstHolidayKey key);

    int insert(MstHoliday record);

    int insertSelective(MstHoliday record);

    int insertBasicByBasicInfo(MstHoliday record);

    MstHoliday selectByPrimaryKey(MstHolidayKey key);

    int updateByPrimaryKeySelective(MstHoliday record);

    int updateByPrimaryKey(MstHoliday record);
}