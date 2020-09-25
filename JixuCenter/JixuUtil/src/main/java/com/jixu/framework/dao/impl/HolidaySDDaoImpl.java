package com.jixu.framework.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jixu.common.pojo.HolidaySD;
import com.jixu.framework.dao.intf.HolidaySDDao;

/**
 * @ClassName 		HolidaySDDaoImpl
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

@Repository("holidaySDDao")
public class HolidaySDDaoImpl extends CommFuncDAOImpl implements HolidaySDDao{

	@Override
	public HolidaySD loadHolidaySD(int id) {
		return loadEntity(HolidaySD.class,id);
	}
	
	@Override
	public List<HolidaySD> loadHolidaySchedule() {
		String hql="from HolidaySD order by id asc";
		List<HolidaySD> holidaySDList=this.executeHQL(hql);
		return holidaySDList;
	}
	
	@Override
	public void saveHolidaySD(HolidaySD holidaySD) {
		saveEntity(holidaySD);
	}
}
