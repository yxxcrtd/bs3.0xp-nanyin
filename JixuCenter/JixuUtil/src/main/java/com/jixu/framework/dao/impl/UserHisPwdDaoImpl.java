package com.jixu.framework.dao.impl;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.Page;
import com.jixu.common.pojo.UserHisPwd;
import com.jixu.common.util.Pages;
import com.jixu.framework.dao.intf.UserHisPwdDao;

/**
 * @ClassName 		UserHisPwdDaoImpl
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

@Repository("userHisPwdDao")
public class UserHisPwdDaoImpl extends CommFuncDAOImpl implements UserHisPwdDao{
	
	@Override
	public Pages<UserHisPwd> getUpdatePwdList(Pages<UserHisPwd> pages, int userid) {
		String hql="from UserHisPwd where userid=:userid order by id desc";
		String[] params={"userid"};
		Object[] values={userid}; 
		return this.getPageHqlData(hql, params, values, false, pages);
	}
	
	@Override
	public Page<UserHisPwd> getPageHelperUpdatePwdList(@Param("pages")Page<UserHisPwd> page,int userid) {
		return page;
	}
}
