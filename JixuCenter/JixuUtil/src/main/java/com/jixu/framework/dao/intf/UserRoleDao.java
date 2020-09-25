package com.jixu.framework.dao.intf;

import java.util.List;

import com.jixu.common.pojo.UserRole;

/**
 * @ClassName 		UserRoleDao
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

public interface UserRoleDao extends CommFuncDAO{
	
	public List<UserRole> loadUserRoleList();

}
