package com.jixu.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.jixu.dao.intf.NavBankZiGuanDao;
import com.jixu.framework.dao.impl.CommFuncDAOImpl;
import com.orm.obj.NavBankZiGuan;

/**
 * @ClassName 		NavBankZiGuanDaoImpl
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

@Repository("navBankZiGuanDao")
public class NavBankZiGuanDaoImpl extends CommFuncDAOImpl implements NavBankZiGuanDao{
	
	@Override
	public void deleteNavBankZG(String portnum, Date navDate) {
		String hql="delete from NavBankZiGuan where portnum=:portnum and navDate=:navDate";
		String[] params={"portnum","navDate"};
		Object[] values={portnum,navDate};
		this.deleteHql(hql, params, values);
	}
	
	@Override
	public List<NavBankZiGuan> getNavBankZiGuanList(String portnum, Date asOfDate) {
		String hql="from NavBankZiGuan where portnum=:portnum and navDate=:asOfDate";
		String[] params={"portnum","asOfDate"};
		Object[] values={portnum,asOfDate};
		return this.executeHQL(hql, params, values);
	}
	
	@Override
	public NavBankZiGuan getNavBankZiGuan(String portnum, String cusip, int lotnum, Date navDate) {
		String hql="from NavBankZiGuan where portnum=:portnum and cusip=:cusip and lotnum=:lotnum and navDate=:navDate";
		String[] params={"portnum","cusip","lotnum","navDate"};
		Object[] values={portnum,cusip,lotnum,navDate};
		return (NavBankZiGuan)this.executeHQLNo1Record(hql, params, values);
	}
	
	@Override
	public List<NavBankZiGuan> loadNavBankZiGuan(String portnum, Date asOfDate) {
		String hql="from NavBankZiGuan where portnum=:portnum and navDate=:asOfDate";
		String[] params={"portnum","asOfDate"};
		Object[] values={portnum, asOfDate};
		return this.executeHQL(hql, params, values);
	}
	
}
