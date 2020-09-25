package com.jixu.framework.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jixu.common.pojo.RoleType;
import com.jixu.framework.dao.intf.RoleTypeDao;

/**
 * @ClassName 		RoleTypeDaoImpl
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

@Repository("roleTypeDao")
public class RoleTypeDaoImpl extends CommFuncDAOImpl implements RoleTypeDao{
	
	@Override
	public List<RoleType> loadRoleType() {
		String hql="from RoleType";
		return this.executeHQL(hql);
	}
	
	@Override
	public List<RoleType> loadRoleList(int groupID) {
		String hql="select rt from RoleType rt, RoleTypeRoleGroupMap rgm where rt.id=rgm.roleType.id and rgm.roleGroup.groupID=:groupID";
		return this.executeHQL(hql, "groupID", groupID);
	}
}
