package com.jixu.dao.intf;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jixu.framework.dao.intf.CommFuncDAO;
import com.orm.obj.NewFund;

/**
 * @ClassName 		NewFundDao
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

public interface NewFundDao extends CommFuncDAO{
	
	/**
	 * 
	 * @param clientID
	 * @param newFundType
	 * @return
	 */
	public List<NewFund> loadNewFundList(@Param("clientID")int clientID, @Param("newFundType") int newFundType);

}
