package com.jixu.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.jixu.common.types.ExpenseBaseType;
import com.jixu.dao.intf.TranBasicFeeDao;
import com.jixu.framework.dao.impl.CommFuncDAOImpl;
import com.orm.obj.TranBasicFee;

/**
 * @ClassName 		TranBasicFeeDaoImpl
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

@Repository("tranBasicFeeDao")
public class TranBasicFeeDaoImpl extends CommFuncDAOImpl implements TranBasicFeeDao{

	@Override
	public List<TranBasicFee> loadTranBasicFee(String tanum) {
		String hql="from TranBasicFee where tanum.id=:tanum";
		String[] params={"tanum"};
		Object[] values={tanum};
		return this.executeHQL(hql, params, values);
	}
	
	@Override
	public List<TranBasicFee> loadTranFeeList(String portnum, Date ledgerDate) {
		String hql="select fee from TranBasicFee fee, TranBasic tb where fee.tanum.id=tb.id and tb.portfolio=:portnum and ledgerDate=:ledgerDate "
				+ "and fee.feeTypeID.oneTimeFeeFlag=:oneTimeFeeFlag";
		String[] params={"portnum","ledgerDate", "oneTimeFeeFlag"};
		Object[] values={portnum,ledgerDate,ExpenseBaseType.onetimeFee};
		return this.executeHQL(hql, params, values);
	}
	
	@Override
	public List<TranBasicFee> loadSubRedTranFeeList(String portnum, Date ledgerDate) {
		String hql="select fee from TranBasicFee fee, TranSubRed tb where fee.tanum.id=tb.tanum.id and tb.portfolio.id=:portnum and ledgerin=:ledgerDate "
				+ "and fee.feeTypeID.oneTimeFeeFlag=:oneTimeFeeFlag";
		String[] params={"portnum","ledgerDate", "oneTimeFeeFlag"};
		Object[] values={portnum,ledgerDate,ExpenseBaseType.onetimeFee};
		return this.executeHQL(hql, params, values);
	}
}
