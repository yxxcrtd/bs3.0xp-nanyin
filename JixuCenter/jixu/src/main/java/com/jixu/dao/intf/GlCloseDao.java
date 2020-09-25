package com.jixu.dao.intf;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.ibatis.annotations.Param;

import com.jixu.framework.dao.intf.CommFuncDAO;
import com.orm.obj.GlClose;

/**
 * @ClassName 		GlCloseDao
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

public interface GlCloseDao extends CommFuncDAO{
	/**
	 * 获取<AcctNum, GlClose>的集合
	 * @param portnum
	 * @param glDate
	 * @return
	 */
	public List<GlClose> getClosedGL(@Param("portnum")String portnum, @Param("glDate") Date glDate);
	
	/**
	 * 获得某个科目的余额
	 * @param portnum
	 * @param glDate
	 * @param acctnum
	 * @return
	 */
	public double getCloseBalance(@Param("portnum")String portnum, @Param("glDate") Date glDate, @Param("acctnum") String acctnum);
	
	public void deleteGlGlose(@Param("portnum")String portnum, @Param("closeDate") Date closeDate);
}
