package com.jixu.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jixu.dao.intf.BokVouchMainDao;
import com.jixu.framework.dao.impl.CommFuncDAOImpl;

/**
 * @ClassName 		CashFlowFloatRateDaoImpl
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

@Repository("bokVouchMainDao")
public class BokVouchMainDaoImpl extends CommFuncDAOImpl implements BokVouchMainDao{
	
	@Override
	public long getNextSequence() {
		String sql = "select BOOK_SEQUENCE.nextval from dual";
		List<Object> result=this.executeSQL(sql, null);
		long nextval=Long.parseLong(result.get(0).toString());
		return nextval;
	}
}
