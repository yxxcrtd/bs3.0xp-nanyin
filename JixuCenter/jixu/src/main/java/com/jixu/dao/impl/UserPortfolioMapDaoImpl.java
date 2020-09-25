package com.jixu.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jixu.dao.intf.UserPortfolioMapDao;
import com.jixu.framework.dao.impl.CommFuncDAOImpl;
import com.orm.obj.UserPortfolioMap;

/**
 * @ClassName 		UserPortfolioMapDaoImpl
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

@Repository("userPortfolioMapDao")
public class UserPortfolioMapDaoImpl extends CommFuncDAOImpl implements UserPortfolioMapDao{
	
	@Override
	public void deleteUserPortMap(int userID) {
		String hql="delete from UserPortfolioMap where users.userid=:userID";
		String[] params={"userID"};
		Object[] values={userID};
		this.deleteHql(hql, params, values);
	}
	
	@Override
	public void createUserPortMap(UserPortfolioMap userPortfolioMap) {
		this.saveEntity(userPortfolioMap);
	}
	
	@Override
	public List<UserPortfolioMap> loadUserPortMapListByUserID(int userID) {
		String hql="from UserPortfolioMap where users.userid=:userID";
		String[] params={"userID"};
		Object[] values={userID};
		return this.executeHQL(hql, params, values);
	}

}
