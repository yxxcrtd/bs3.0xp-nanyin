package com.jixu.framework.dao.intf;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jixu.common.pojo.HolidaySD;

/**
 * @ClassName 		HolidaySDDao
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

public interface HolidaySDDao extends CommFuncDAO{
	
	public HolidaySD loadHolidaySD(@Param("id")int id);
	
	/**
	 * 获取所有的节假日
	 * @return
	 */
	public List<HolidaySD> loadHolidaySchedule();
	
	public void saveHolidaySD(@Param("holidaySD")HolidaySD holidaySD);

}
