package com.joyintech.tams.report.monthreport.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.framework.grid.Page;
import com.joyintech.tams.report.monthreport.dto.MonthReport001Dto;

@Repository
public interface MonthReport001Mapper {

	public List<MonthReport001Dto> getMonthReportFBB(Map<String, Object> pageParam);

	public List<MonthReport001Dto> getMonthReportBB(Map<String, Object> pageParam);

	public String getSubmitstatus(Map<String, Object> pageParam);

	public int manualSubmitstatus(Map<String, Object> pageParam);
	
	public int cancelSubmitstatus(Map<String, Object> pageParam);
	
	
}
