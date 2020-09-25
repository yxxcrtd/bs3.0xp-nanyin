package com.jixu.data.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.jixu.common.pojo.HolidayLS;
import com.jixu.common.util.DateUtil;
import com.jixu.data.pojo.Holidays;
import com.jixu.framework.dao.intf.HolidaySDDao;

/** 
 * @ClassName 		TbHoliday
 * @author  		Pan Wei 
 * @Date 			2018年2月12日 上午10:19:03 	
 * @Description		[]  
 */

@Component("JixuTbPubHoliday")
public class TbPubHoliday extends OutPublicDataOperation {
	
	@Autowired
	private HolidaySDDao holidaySDDao;
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public Class<?> getReturnClass() {
		return HolidayLS.class;
	}
	
	@Override
	public List<?> genResults(Object obj) {
		HolidayLS holidayLS=(HolidayLS) obj;
		List<Holidays> holidayList=new ArrayList<Holidays>();
		Holidays holiday=new Holidays();
		holiday.setId(holidayLS.getId());
		holiday.setHolidayID(holidayLS.getHolidaySD());
		holiday.setHolidayName(holidayLS.getHolidayName());
		holiday.setHolidayDate(DateUtil.ConvertUtil2Sql(holidayLS.getHolidayDate()));
		holidayList.add(holiday);
		
		return holidayList;
	}
	
	@Override
	protected List<?> genResults() {
		String sql="select id, holidaySD as holidayID, holidayDate, holidayName from JXU_holidayls";
		List<Holidays> holidaysList = holidaySDDao.executeSQLReturnBean(sql, Holidays.class);
		
		return holidaysList;
	}
}
