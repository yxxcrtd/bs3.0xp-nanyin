package com.jixu.dao.intf;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jixu.framework.dao.intf.CommFuncDAO;
import com.orm.obj.UserClientMap;

/**
 * @ClassName 		UserClientMapDao
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

public interface UserClientMapDao extends CommFuncDAO{
	
	public List<UserClientMap> loadUserClientMapListByUserID(@Param("userID")int userID);
	
	public void deleteUserClientMap(@Param("userID")int userID);

}
