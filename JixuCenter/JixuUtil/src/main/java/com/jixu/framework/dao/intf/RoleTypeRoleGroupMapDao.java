package com.jixu.framework.dao.intf;

import org.apache.ibatis.annotations.Param;

/**
 * @ClassName 		RoleTypeRoleGroupMapDao
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

public interface RoleTypeRoleGroupMapDao extends CommFuncDAO{
	
	public void deleteRoleGroupRoleTypeMap(@Param("groupID")int groupID);

	public void deleteRoleGroupMap(@Param("roleID")int roleID);
}
