package com.joyintech.service.risk.creditrisklimitmanagement.linemonitordaily.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.service.risk.creditrisklimitmanagement.linemonitordaily.dto.AbsLineMonitorDailyDto;
import com.joyintech.service.risk.creditrisklimitmanagement.linemonitordaily.dto.BusLineMonitorDailyDto;
import com.joyintech.service.risk.creditrisklimitmanagement.linemonitordaily.dto.EbCbLineMonitorDailyDto;
import com.joyintech.service.risk.creditrisklimitmanagement.linemonitordaily.dto.ObjLineMonitorDailyDto;
import com.joyintech.service.risk.creditrisklimitmanagement.linemonitordaily.dto.TotalLineMonitorDailyDto;

@Repository
public interface LineMonitorDailyMapper {
	
	List<AbsLineMonitorDailyDto> queryAbsLineMonitorDaily(@Param("cdate")Date cdate);
	
	List<EbCbLineMonitorDailyDto> queryEbCbLineMonitorDaily(@Param("cdate")Date cdate);
	
	List<ObjLineMonitorDailyDto> queryObjLineMonitorDaily(@Param("cdate")Date cdate);
	
	List<BusLineMonitorDailyDto> queryBusLineMonitorDaily(@Param("cdate")Date cdate);
	
	List<TotalLineMonitorDailyDto> queryTotalLineMonitorDaily(@Param("cdate")Date cdate);
	

}
