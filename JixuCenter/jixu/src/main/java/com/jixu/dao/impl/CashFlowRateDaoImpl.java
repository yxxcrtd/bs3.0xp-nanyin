package com.jixu.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jixu.dao.intf.CashFlowRateDao;
import com.jixu.framework.dao.impl.CommFuncDAOImpl;
import com.orm.obj.CashFlowRate;

/**
 * @ClassName 		CashFlowRateDaoImpl
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

@Repository("cashFlowRateDao")
public class CashFlowRateDaoImpl extends CommFuncDAOImpl implements CashFlowRateDao{
	
	@Override
	public List<CashFlowRate> loadCashFlowRateByCfID(String cfID) {
		String hql="from CashFlowRate where cfid=:cfID";
		String[] params={"cfID"};
		Object[] values={cfID};
		return this.executeHQL(hql,params,values);
	}

}
