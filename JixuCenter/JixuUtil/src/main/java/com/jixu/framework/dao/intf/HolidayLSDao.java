package com.jixu.framework.dao.intf;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import com.jixu.common.pojo.HolidayLS;

/**
 * @ClassName 		HolidayLSDao
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

public interface HolidayLSDao extends CommFuncDAO{
	
	public List<HolidayLS> loadHolidayLSList(@Param("holidaySDNum")String holidaySDNum);
	
	@CacheEvict(value="portDataCache", key="'Holiday'+#holidayLS.getHolidaySD().getId()")
	public void createHolidayLS(@Param("holidayLS")HolidayLS holidayLS);
	
	@CacheEvict(value="portDataCache", key="'Holiday'+#holidayLS.getHolidaySD().getId()")
	public void updateHolidayLS(@Param("holidayLS")HolidayLS holidayLS);
	
	@CacheEvict(value="portDataCache", key="'Holiday'+#holidayLS.getHolidaySD().getId()")
	public void deleteHolidayLS(@Param("holidayLS")HolidayLS holidayLS);

	@Cacheable(value="holidayCache", key="'Holiday'+#holidaySDID")
	public List<Date> loadHolidayList(@Param("holidaySDID")String holidaySDID);
	
	/**
	 * 获取joyin的节假日
	 * @param holidayID
	 * @return
	 */
	public List<Date> loadJoyinHolidayList(String holidayID);
}
