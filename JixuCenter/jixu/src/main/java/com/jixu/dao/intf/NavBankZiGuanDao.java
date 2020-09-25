package com.jixu.dao.intf;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jixu.framework.dao.intf.CommFuncDAO;
import com.orm.obj.NavBankZiGuan;

/**
 * @ClassName 		NavBankZiGuanDao
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

public interface NavBankZiGuanDao extends CommFuncDAO{
	public void deleteNavBankZG(@Param("portnum")String portnum, @Param("navDate") Date navDate);
	
	/**
	 * 获取资管的所有批次信息
	 * @param portnum
	 * @param asOfDate
	 * @return
	 */
	public List<NavBankZiGuan> getNavBankZiGuanList(@Param("portnum")String portnum, @Param("asOfDate") Date asOfDate);
	
	public NavBankZiGuan getNavBankZiGuan(@Param("portnum")String portnum, @Param("cusip") String cusip, @Param("lotnum") int lotnum, @Param("navDate") Date navDate);
	
	/**
	 * 获取银行资管申赎批次的净值信息
	 * @param portnum
	 * @param asOfDate
	 * @return
	 */
	public List<NavBankZiGuan> loadNavBankZiGuan(@Param("portnum")String portnum, @Param("asOfDate") Date asOfDate);
}
