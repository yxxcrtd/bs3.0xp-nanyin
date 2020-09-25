package com.jixu.dao.intf;


import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.CacheEvict;

import com.jixu.framework.dao.intf.CommFuncDAO;

/**
 * @ClassName 		PortfolioClassIDMapDao
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

public interface PortfolioClassIDMapDao extends CommFuncDAO{

	@CacheEvict(value="portDataCache", key="'classID'+#classID")
	public void deletePortfolioClassIDMap(@Param("portID")String portID);
}
