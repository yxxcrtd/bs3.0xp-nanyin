package com.jixu.dao.intf;

import org.apache.ibatis.annotations.Param;

import com.jixu.framework.dao.intf.CommFuncDAO;
import com.orm.obj.SecContract;

/**
 * @ClassName 		SecContractDao
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

public interface SecContractDao extends CommFuncDAO{

	public SecContract loadSecContract(@Param("cusipID")String cusipID);
}
