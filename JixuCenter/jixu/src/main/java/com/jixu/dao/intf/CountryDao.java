package com.jixu.dao.intf;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jixu.framework.dao.intf.CommFuncDAO;
import com.orm.obj.Country;

/**
 * @ClassName 		CountryDao
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

public interface CountryDao extends CommFuncDAO{

	public Country loadCountry(@Param("countryID")String countryID);
	
	/**
	 * 获取国家列表
	 * @return
	 */
	public List<Country> getCountryist();
	
	public void updateCountry(@Param("country")Country country);
	
	public void saveCountry(@Param("country")Country country);
	
}
