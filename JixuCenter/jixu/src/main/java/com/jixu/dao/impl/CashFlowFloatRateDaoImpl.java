package com.jixu.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.jixu.dao.intf.CashFlowFloatRateDao;
import com.jixu.framework.dao.impl.CommFuncDAOImpl;
import com.orm.obj.CashFlowFloatRate;
import com.orm.obj.CashFlowRate;

/**
 * @ClassName 		CashFlowFloatRateDaoImpl
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

@Repository("cashFlowFloatRateDao")
public class CashFlowFloatRateDaoImpl extends CommFuncDAOImpl implements CashFlowFloatRateDao{
	
	@Override
	public List<CashFlowFloatRate> loadCashFlowFloatRateByCfID(String cfID) {
		String hql="from CashFlowFloatRate where cfID=:cfID";
		String[] params={"cfID"};
		Object[] values={cfID};
		return this.executeHQL(hql,params,values);
	}
	
	@Override
	public CashFlowRate getCashFlowRate(String cashFlowID, Date ledgerDate) {
		String hql="from CashFlowRate where cfid=:cashFlowID and cfDate<=:ledgerDate order by cfDate desc";
		String[] params={"cashFlowID", "ledgerDate"};
		Object[] values={cashFlowID, ledgerDate};
		return (CashFlowRate)this.executeHQLNo1Record(hql, params, values);
	}
	

}
