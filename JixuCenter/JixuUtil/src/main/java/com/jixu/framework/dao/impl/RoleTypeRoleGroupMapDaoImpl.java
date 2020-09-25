package com.jixu.framework.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jixu.framework.dao.intf.RoleTypeRoleGroupMapDao;

/**
 * @ClassName 		RoleTypeRoleGroupMapDaoImpl
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

@Repository("roleTypeRoleGroupMapDao")
public class RoleTypeRoleGroupMapDaoImpl extends CommFuncDAOImpl implements RoleTypeRoleGroupMapDao{
	
	@Override
	public void deleteRoleGroupRoleTypeMap(int groupID) {
		String hql="delete from RoleTypeRoleGroupMap where roleGroup.groupID=:groupID";
		String[] params={"groupID"};
		Object[] values={groupID};
		this.deleteHql(hql, params, values);
	}

	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public void deleteRoleGroupMap(int roleID) {
		String hql="delete from RoleTypeRoleGroupMap where roleType.roleID=:roleID";
		this.deleteHql(hql, "roleID", roleID);
	}
}
