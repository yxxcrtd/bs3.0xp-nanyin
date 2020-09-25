package com.jixu.framework.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jixu.common.pojo.UserOpRuleMap;
import com.jixu.framework.dao.intf.UserOpRuleMapDao;

/**
 * @ClassName 		UserOpRuleMapDaoImpl
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

@Repository("userOpRuleMapDao")
public class UserOpRuleMapDaoImpl extends CommFuncDAOImpl implements UserOpRuleMapDao{

	@Override
	public List<UserOpRuleMap> loadUserRoles(int userid) {
		String hql="from UserOpRuleMap where users.userid=:userid order by operationRole asc";
		String[] params={"userid"};
		Object[] values={userid};
		return this.executeHQL(hql, params, values);
	}
	
	@Override
	public void deleteUserOpRole(int userID) {
		String hql="delete from UserOpRuleMap where users.userid=:userID";
		String[] params={"userID"};
		Object[] values={userID};
		this.deleteHql(hql, params, values);
	}
}
