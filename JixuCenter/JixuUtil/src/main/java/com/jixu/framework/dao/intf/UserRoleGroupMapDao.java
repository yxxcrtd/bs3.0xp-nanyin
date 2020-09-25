package com.jixu.framework.dao.intf;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jixu.common.pojo.UserRoleGroupMap;

/**
 * @ClassName 		UserRoleGroupMapDao
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

public interface UserRoleGroupMapDao extends CommFuncDAO{
	
	public void deleteUserRoleGroupMap(@Param("roleID")int roleID);
	
	public List<UserRoleGroupMap> loadUserRoleGroupMap(@Param("roleid")int roleid);

}
