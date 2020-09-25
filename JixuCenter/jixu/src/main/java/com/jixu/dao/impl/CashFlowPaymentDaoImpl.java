package com.jixu.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.jixu.common.tool.JixuUserUtil;
import com.jixu.dao.intf.CashFlowPaymentDao;
import com.jixu.framework.dao.impl.CommFuncDAOImpl;
import com.orm.obj.CashFlowPayment;
import com.orm.obj.CashFlowPlan;

/**
 * @ClassName 		CashFlowPaymentDaoImpl
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

@Repository("cashFlowPaymentDao")
public class CashFlowPaymentDaoImpl extends CommFuncDAOImpl implements CashFlowPaymentDao{
	
	@Override
	public List<CashFlowPayment> loadCashFlowPaymentByCfID(String cfID) {
		String hql="from CashFlowPayment where cfid=:cfID";
		String[] params={"cfID"};
		Object[] values={cfID};
		return this.executeHQL(hql,params,values);
	}
	
	@Override
	public List<Date> loadCashFlowSchedule(String cashflowID) {
		if(JixuUserUtil.isJoyinTablesFlag()) {
			String hql="from CashFlowPlan where cash_id=:cashflowID order by mDate asc";
			List<CashFlowPlan> planList=this.executeHQL(hql, "cashflowID", cashflowID);
			List<Date> dateList=new ArrayList<Date>();
			for(int i=0;i<planList.size();i++) {
				CashFlowPlan plan=planList.get(i);
				if(i==0) {
					//付息计划的第一条数据，vdate是起息日
					dateList.add(plan.getvDate());
				}
				dateList.add(plan.getmDate());
			}
			return dateList;
		}else {
			String hql="select payDate from CashFlowPayment where cfid=:cashflowID order by payDate asc";
			return this.executeHQL(hql, "cashflowID", cashflowID);
		}
		
	}
	
	@Override
	public Date getCashFlowResetRateDate(String cfgid, Date resetDate) {
		if(JixuUserUtil.isJoyinTablesFlag()) {
			String hql="select confirm_date from CashFlowResetPlan where cash_id=:cfgid and reset_date=:resetDate"; 
			String[] params= {"cfgid", "resetDate"};
			Object[] values= {cfgid, resetDate};
			List<Date> dateList=this.executeHQL(hql, params, values);
			if(dateList.size()>0) {
				return dateList.get(0);
			}else{
				return resetDate;
			}
		}else {
			return resetDate;
		}
	}
	
	@Override
	public List<Date> loadCashFlowRestSchedule(String cashflowID) {
		String hql="select reset_date from CashFlowResetPlan where cash_id=:cashflowID order by reset_date asc";
		return this.executeHQL(hql, "cashflowID", cashflowID);
	}
}
