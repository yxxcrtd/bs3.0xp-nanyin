package com.jixu.framework.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jixu.common.pojo.Users;
import com.jixu.framework.dao.intf.UsersDao;

/**
 * @ClassName 		UsersDaoImpl
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

@Repository("usersDao")
public class UsersDaoImpl extends CommFuncDAOImpl implements UsersDao{

	@Override
	public List<Users> loadAllUser(){
		return this.executeHQL("from Users");
	}
	
	public Users getUserAccount(String userName) {
		String sql="from Users where username=:userName";
		String[] params = { "userName"};
		Object[] values = { userName };		
		List<Users> userList = this.executeHQL(sql, params, values);
		if(userList.size()>0){
			Users account = userList.get(0);
			return account;
		}else
			return null;
	}
	
	
	@Override
	public Users loadUserDetail(int userid) {
		String hql="from Users where userid=:userid";
		String[] params={"userid"};
		Object[] values={userid};
		List<Users> list=this.executeHQL(hql, params, values);
		if(list.size()>0){
			Users user=list.get(0);
			return user;
		}else{
			return null;
		}
	}
}
