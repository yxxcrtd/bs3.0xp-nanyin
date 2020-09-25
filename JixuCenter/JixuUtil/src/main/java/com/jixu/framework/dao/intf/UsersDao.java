package com.jixu.framework.dao.intf;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.Cacheable;

import com.jixu.common.pojo.Users;

/**
 * @ClassName 		UsersDao
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

public interface UsersDao extends CommFuncDAO{
	
	public Users getUserAccount(@Param("userName")String userName); 
	
	@Cacheable(value="userDataCache", key="'UserID'+#userid")
	public Users loadUserDetail(@Param("userid")int userid);

	public List<Users> loadAllUser();
}
