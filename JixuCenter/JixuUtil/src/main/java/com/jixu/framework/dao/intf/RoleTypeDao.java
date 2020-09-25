package com.jixu.framework.dao.intf;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jixu.common.pojo.RoleType;

/**
 * @ClassName 		RoleTypeDao
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

public interface RoleTypeDao extends CommFuncDAO{
	
	public List<RoleType> loadRoleType();
	
	/**
	 * 获取该权限组的所有权限
	 * @param groupID
	 * @return
	 */
	public List<RoleType> loadRoleList(@Param("groupID")int groupID);
}
