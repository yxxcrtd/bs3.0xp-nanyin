package com.jixu.dao.intf;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

import com.jixu.framework.dao.intf.CommFuncDAO;
import com.orm.obj.CloseHis;

/**
 * @ClassName 		CloseHisDao
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

public interface CloseHisDao extends CommFuncDAO{
	public CloseHis loadCloseHisByCloseDate(@Param("portnum")String portnum, @Param("closeDate") Date closeDate);
	
	public void deleteCloseHisRecords(String portnum, Date closeDate);
}
