package com.jixu.dao.impl;

import org.springframework.stereotype.Repository;

import com.jixu.dao.intf.ValuationReportDao;
import com.jixu.framework.dao.impl.CommFuncDAOImpl;

/**
 * @ClassName 		SecContractDaoImpl
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

@Repository("valuationReportDao")
public class ValuationReportDaoImpl extends CommFuncDAOImpl implements ValuationReportDao{
	
	@Override
	public void deleteValuationReport(String portnum, java.sql.Date asOfDate) {
		String hql="delete from ValuationReport where portnum=:portnum and asOfDate>=:asOfDate";
		String[] params={"portnum","asOfDate"};
		Object[] values={portnum,asOfDate};
		this.deleteHql(hql, params, values);
	}
}
