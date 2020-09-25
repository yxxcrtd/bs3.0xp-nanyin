package com.jixu.common.mvc.service;

import java.util.HashMap;
import java.util.List;

import com.jixu.common.pojo.HolidayLS;
import com.jixu.common.pojo.HolidaySD;

public interface ConfigHolidayService {

	/**
	 * 获取所有的节假日列表
	 * @return
	 */
	public HashMap<String, String> loadHolidaySchedule();
	
	public void createHolidaySD(HolidaySD holidaySD);
	
	public List<HolidayLS> loadHolidayLSList(String holidaySDNum);
}
