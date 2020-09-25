package com.jixu.dao.intf;

import org.apache.ibatis.annotations.Param;

import com.jixu.framework.dao.intf.CommFuncDAO;

/**
 * @ClassName 		CashFlowParDao
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

public interface ValuationReportDao extends CommFuncDAO{
	public void deleteValuationReport(@Param("portnum")String portnum, @Param("asOfDate") java.sql.Date asOfDate);
}
