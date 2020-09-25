package com.jixu.dao.intf;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jixu.framework.dao.intf.CommFuncDAO;
import com.orm.obj.YGZTaiZhang;

/**
 * @ClassName 		YGZTaiZhangDao
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

public interface YGZTaiZhangDao extends CommFuncDAO{
	/**
	 * 获取某天的营改增台账
	 * @param portnum
	 * @param asOfDate
	 * @return
	 */
	public List<YGZTaiZhang> loadYGZTaiZhang(@Param("portnum")String portnum, @Param("asOfDate") java.sql.Date asOfDate);
	
	/**
	 * 删除台账的相关信息
	 * @param portnum
	 * @param asOfDate
	 * @param taxFeeType
	 */
	public void deleteYGZTaiZhang(@Param("portnum")String portnum, @Param("asOfDate") java.sql.Date asOfDate, @Param("taxFeeType") int taxFeeType);
}
