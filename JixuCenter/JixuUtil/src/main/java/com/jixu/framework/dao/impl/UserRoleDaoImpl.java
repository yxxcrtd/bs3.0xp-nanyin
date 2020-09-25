package com.jixu.framework.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jixu.common.pojo.UserRole;
import com.jixu.framework.dao.intf.UserRoleDao;

/**
 * @ClassName 		UserRoleDaoImpl
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

@Repository("userRoleDao")
public class UserRoleDaoImpl extends CommFuncDAOImpl implements UserRoleDao{
	
	@Override
	public List<UserRole> loadUserRoleList() {
		String hql="from UserRole";
		return this.executeHQL(hql);
	}
}
