package com.jixu.framework.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jixu.common.pojo.HolidayLS;
import com.jixu.common.tool.JixuUserUtil;
import com.jixu.framework.dao.intf.HolidayLSDao;

/**
 * @ClassName 		HolidayLSDaoImpl
 * @author  		Pan Wei 
 * @Date 			2018锟斤拷10锟斤拷10锟斤拷 锟斤拷锟斤拷23:29:03 	
 * @Description		[]  
 */

@Repository("holidayLSDao")
public class HolidayLSDaoImpl extends CommFuncDAOImpl implements HolidayLSDao{

	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public List<Date> loadHolidayList(String holidaySDID) {
		if(!JixuUserUtil.isJoyinTablesFlag()) {
			String hql="select holidayDate from HolidayLS where holidaySD=:holidaySDID order by holidayDate asc";
			String[] params={"holidaySDID"};
			Object[] values={holidaySDID};
			return this.executeHQL(hql, params, values);}
		else {
			String sql="select CDATE from MST_HOLIDAY where IS_HOLIDAY=1 and CALENDAR_ID=:holidaySDID";
			String[] params= {"holidaySDID"};
			Object[] values= {holidaySDID};
			return this.executeSQL(sql, params, values, null);
		}
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public List<Date> loadJoyinHolidayList(String holidaySDID) {
		String sql="select CDATE from MST_HOLIDAY where IS_HOLIDAY=1 and CALENDAR_ID=:holidaySDID";
		String[] params= {"holidaySDID"};
		Object[] values= {holidaySDID};
		return this.executeSQL(sql, params, values, null);
	}
	
	@Override
	public List<HolidayLS> loadHolidayLSList(String holidaySDNum) {
		String hql="from HolidayLS where holidaySD=:holidaySDNum order by holidayDate desc";
		String[] params={"holidaySDNum"};
		Object[] values={holidaySDNum};
		return this.executeHQL(hql, params, values);
	}
	
	@Override
	public void createHolidayLS(HolidayLS holidayLS) {
		saveEntity(holidayLS);
	}
	
	@Override
	public void updateHolidayLS(HolidayLS holidayLS) {
		this.updateEntity(holidayLS);
	}
	
	@Override
	public void deleteHolidayLS(HolidayLS holidayLS) {
		this.deleteEntity(holidayLS);
	}
}
