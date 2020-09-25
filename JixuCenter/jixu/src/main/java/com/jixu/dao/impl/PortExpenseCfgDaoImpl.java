package com.jixu.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jixu.common.types.ExpenseBaseType;
import com.jixu.dao.intf.PortExpenseCfgDao;
import com.jixu.framework.dao.impl.CommFuncDAOImpl;
import com.orm.obj.PortExpenseCfg;

/**
 * @ClassName 		PortExpenseCfgDaoImpl
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

@Repository("portExpenseCfgDao")
public class PortExpenseCfgDaoImpl extends CommFuncDAOImpl implements PortExpenseCfgDao{

	@Override
	public List<PortExpenseCfg> loadTaxFeeCfgOldNetAssetByPortNum(String portnum) {
		String hql="from PortExpenseCfg where portfolio.id=:portnum and newNavFeeFlag=:navFlag";
		String[] params={"portnum", "navFlag"};
		Object[] values={portnum, ExpenseBaseType.UnNewAssetExpense};
		return this.executeHQL(hql, params, values);
	}
	
	@Override
	public List<PortExpenseCfg> loadTaxFeeCfgNewNetAssetByPortNum(String portnum) {
		String hql="from PortExpenseCfg where portfolio.id=:portnum and newNavFeeFlag=:navFlag order by orderNum asc";
		String[] params={"portnum", "navFlag"};
		Object[] values={portnum, ExpenseBaseType.NewAssetExpense};
		return this.executeHQL(hql, params, values);
	}
	
	@Override
	public void delExpense(int expID) {
		String hql="delete from PortExpenseCfg where portFeeID=:expID";
		String[] params={"expID"};
		Object[] values={expID};
		this.deleteHql(hql, params, values);
	}
	

	@Override
	public boolean checkSamePortExpenseExise(String portnum, String expenseName) {
		String hql="from PortExpenseCfg where portfolio.id=:portnum and portFeeName=:expenseName";
		String[] params={"portnum","expenseName"};
		Object[] values={portnum,expenseName};
		List<PortExpenseCfg> expList=this.executeHQL(hql, params, values);
		if(expList.size()>0){
			return true;
		}else{
			return false;
		}
	}
	
	@Override
	public List<PortExpenseCfg> loadExpenseListByPortnum(String portnum){
		String hql="from PortExpenseCfg where portfolio.id=:portnum order by portFeeID desc";
		String[] params={"portnum"};
		Object[] values={portnum};
		List<PortExpenseCfg> expenseList=this.executeHQL(hql, params, values);
		return expenseList;
	}
	
}
