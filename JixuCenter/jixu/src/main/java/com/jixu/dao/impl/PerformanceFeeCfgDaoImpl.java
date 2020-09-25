package com.jixu.dao.impl;

import org.springframework.stereotype.Repository;

import com.jixu.dao.intf.PerformanceFeeCfgDao;
import com.jixu.framework.dao.impl.CommFuncDAOImpl;
import com.orm.obj.PerformanceFeeCfg;

/**
 * @ClassName 		PerformanceFeeCfgDaoImpl
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

@Repository("performanceFeeCfgDao")
public class PerformanceFeeCfgDaoImpl extends CommFuncDAOImpl implements PerformanceFeeCfgDao{

	@Override
	public PerformanceFeeCfg loadPerformanceFeeCfg(String portnum, String cusip, int lotnum) {
		String hql="from PerformanceFeeCfg where portnum=:portnum and cusip=:cusip and lotnum=:lotnum";
		String[] params={"portnum","cusip","lotnum"};
		Object[] values={portnum,cusip,lotnum};
		return (PerformanceFeeCfg)this.executeHQLNo1Record(hql, params, values);
	}
	
	@Override
	public void updatePerformanceFee(PerformanceFeeCfg pfc) {
		this.updateEntity(pfc);
	}
	
	@Override
	public PerformanceFeeCfg loadActivePerformanceFeeCfg(String portnum, String cusip, int lotnum) {
		String hql="from PerformanceFeeCfg where portnum=:portnum and cusip=:cusip and lotnum=:lotnum and activeFlag is true";
		String[] params={"portnum","cusip","lotnum"};
		Object[] values={portnum,cusip,lotnum};
		return (PerformanceFeeCfg)this.executeHQLNo1Record(hql, params, values);
	}
	
}
