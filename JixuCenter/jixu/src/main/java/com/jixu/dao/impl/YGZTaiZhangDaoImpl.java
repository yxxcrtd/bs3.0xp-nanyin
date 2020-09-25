package com.jixu.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jixu.dao.intf.YGZTaiZhangDao;
import com.jixu.framework.dao.impl.CommFuncDAOImpl;
import com.orm.obj.YGZTaiZhang;

/**
 * @ClassName 		YGZTaiZhangDaoImpl
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

@Repository("ygzTaiZhangDao")
public class YGZTaiZhangDaoImpl extends CommFuncDAOImpl implements YGZTaiZhangDao{
	@Override
	public List<YGZTaiZhang> loadYGZTaiZhang(String portnum, java.sql.Date asOfDate) {
		String hql="from YGZTaiZhang where portnum=:portnum and ledgerDate=:asOfDate";
		String[] params={"portnum","asOfDate"};
		Object[] values = { portnum, asOfDate};
		return this.executeHQL(hql, params, values);
	}
	
	@Override
	public void deleteYGZTaiZhang(String portnum, java.sql.Date asOfDate, int taxfeeType) {
		String hql="delete from YGZTaiZhang where portnum=:portnum and ledgerDate=:asOfDate and taxFeeType=:taxfeetype";
		String[] params={"portnum","asOfDate","taxfeetype"};
		Object[] values={portnum,asOfDate,taxfeeType};
		this.deleteHql(hql, params, values);
	}
}
