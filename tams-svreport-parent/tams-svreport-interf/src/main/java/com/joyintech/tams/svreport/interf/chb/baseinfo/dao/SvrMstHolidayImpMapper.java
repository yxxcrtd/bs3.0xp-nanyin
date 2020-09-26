package com.joyintech.tams.svreport.interf.chb.baseinfo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.joyintech.tams.common.base.entity.MstHoliday;
import com.joyintech.tams.common.base.entity.MstHolidayKey;

public interface SvrMstHolidayImpMapper {
    int deleteByPrimaryKey(MstHolidayKey key);

    int insert(MstHoliday record);

    int insertSelective(MstHoliday record);

    MstHoliday selectByPrimaryKey(MstHolidayKey key);

    int updateByPrimaryKeySelective(MstHoliday record);

    int updateByPrimaryKey(MstHoliday record);
    
    int deleteHolidayBatch2(@Param("holidayList")List<MstHoliday> holidayList);
    
    int mergeHolidayData(@Param("list")List<MstHoliday> holidayList);

	void deleteAllData();
	
	int deleteTodayData();
}