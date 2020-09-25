package com.jixu.mvc.control.service;

import java.util.Date;
import java.util.List;

public interface PeriodRunService {
	
	//自动区间做账
	public List<Object> autoRunJob(int userID,List<String> ports_list,Date startDate,Date endDate);
	
	//自动区间撤账
	public List<Object> autoRedoJob(int userID,List<String> ports_list,Date startDate,Date endDate);
}
