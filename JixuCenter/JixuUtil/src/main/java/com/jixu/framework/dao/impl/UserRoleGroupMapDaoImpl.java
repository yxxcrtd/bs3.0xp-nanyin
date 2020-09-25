package com.jixu.framework.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jixu.common.pojo.UserRoleGroupMap;
import com.jixu.framework.dao.intf.UserRoleGroupMapDao;

/**
 * @ClassName 		UserRoleGroupMapDaoImpl
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

@Repository("userRoleGroupMapDao")
public class UserRoleGroupMapDaoImpl extends CommFuncDAOImpl implements UserRoleGroupMapDao{
	
	@Override
	public void deleteUserRoleGroupMap(int roleID) {
		String hql="delete from UserRoleGroupMap where userRole.roleID=:roleID";
		String[] params={"roleID"};
		Object[] values={roleID};
		this.deleteHql(hql, params, values);
	}
	
	@Override
	public List<UserRoleGroupMap> loadUserRoleGroupMap(int roleid) {
		String hql="from UserRoleGroupMap where userRole.roleID=:roleid";
		String[] params={"roleid"};
		Object[] values={roleid};
		List<UserRoleGroupMap> list=this.executeHQL(hql, params, values);
		return list;
	}

}
