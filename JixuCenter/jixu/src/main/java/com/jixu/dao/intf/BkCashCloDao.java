package com.jixu.dao.intf;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jixu.framework.dao.intf.CommFuncDAO;
import com.orm.obj.BkCashClo;

/**
 * @ClassName 		BkCashFlowDao
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

public interface BkCashCloDao extends CommFuncDAO{
	
	public void deleteBkCashClo(@Param("portnum")String portnum, @Param("closeDate") Date closeDate);
	
	/**
	 * 获取投资组合某日期的现金头寸
	 * @param portnum
	 * @param closeDate
	 * @return
	 */
	public List<BkCashClo> loadBkCashClo(@Param("portnum")String portnum, @Param("closeDate") Date closeDate);
	
}
