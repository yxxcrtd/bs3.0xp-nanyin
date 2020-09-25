package com.jixu.dao.intf;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jixu.framework.dao.intf.CommFuncDAO;
import com.orm.obj.SmfDiv;

/**
 * @ClassName 		SmfDivDao
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

public interface SmfDivDao extends CommFuncDAO{
	/**
	 * 根据除权除息日,获取公司行为
	 * @param ledgerDate
	 * @return
	 */
	public List<SmfDiv> loadSmfDivListByDate(@Param("ledgerDate")Date ledgerDate);
	
	public List<SmfDiv> loadSmfDivList(@Param("cusip")String cusip, @Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("caType") int caType);
	
	/**
	 * 根据证券信息和除权除息日,获取公司行为
	 */
	public SmfDiv loadSmfDiv(@Param("cusip")String cusip,@Param("ledgerDate")Date ledgerDate,int caType);
}
