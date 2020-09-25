package com.jixu.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.jixu.dao.intf.BkCashCloDao;
import com.jixu.framework.dao.impl.CommFuncDAOImpl;
import com.orm.obj.BkCashClo;

/**
 * @ClassName 		BkCashFlowDaoImpl
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

@Repository("bkCashCloDao")
public class BkCashCloDaoImpl extends CommFuncDAOImpl implements BkCashCloDao{

	@Override
	public void deleteBkCashClo(String portnum, Date closeDate) {
		String hql="delete from BkCashClo where portnum=:portnum and closeDate>=:closeDate";
		String[] params={"portnum","closeDate"};
		Object[] values={portnum,closeDate};
		this.deleteHql(hql, params, values);
	}
	
	@Override
	public List<BkCashClo> loadBkCashClo(String portnum, Date closeDate){
		String hql="from BkCashClo where portnum=:portnum and closeDate=:closeDate";
		String[] params={"portnum","closeDate"};
		Object[] values={portnum, closeDate};
		return this.executeHQL(hql, params, values);
	}
	
}
