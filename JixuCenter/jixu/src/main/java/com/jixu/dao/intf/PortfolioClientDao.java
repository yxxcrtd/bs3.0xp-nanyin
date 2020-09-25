package com.jixu.dao.intf;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jixu.framework.dao.intf.CommFuncDAO;
import com.orm.obj.PortfolioClient;

/**
 * @ClassName 		PortfolioClientDao
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

public interface PortfolioClientDao extends CommFuncDAO{

	/**
	 * 获取客户信息
	 * @return
	 */
	public List<PortfolioClient> fetchClients();
	
	public List<PortfolioClient> getClientsByUserID(@Param("userID")int userID);
}
