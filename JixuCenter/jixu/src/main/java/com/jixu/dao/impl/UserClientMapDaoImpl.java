package com.jixu.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jixu.dao.intf.UserClientMapDao;
import com.jixu.framework.dao.impl.CommFuncDAOImpl;
import com.orm.obj.UserClientMap;

/**
 * @ClassName 		UserClientMapDaoImpl
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

@Repository("userClientMapDao")
public class UserClientMapDaoImpl extends CommFuncDAOImpl implements UserClientMapDao{
	
	@Override
	public List<UserClientMap> loadUserClientMapListByUserID(int userID) {
		String hql="from UserClientMap where users.userid=:userID";
		String[] params={"userID"};
		Object[] values={userID};
		return this.executeHQL(hql, params, values);
	}
	
	@Override
	public void deleteUserClientMap(int userID) {
		String hql="delete from UserClientMap where users.userid=:userID";
		String[] params={"userID"};
		Object[] values={userID};
		this.deleteHql(hql, params, values);
	}

}
