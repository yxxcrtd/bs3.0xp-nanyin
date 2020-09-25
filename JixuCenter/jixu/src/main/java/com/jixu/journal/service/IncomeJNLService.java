package com.jixu.journal.service;

import java.util.Date;
import java.util.List;

import com.jixu.common.exception.JNLException;
import com.jixu.journal.obj.BankBalanceWrapObj;
import com.jixu.journal.obj.IncomeWrapObj;
import com.jixu.journal.obj.SecIncomeWrapObj;
import com.orm.obj.BkCashAcct;

public interface IncomeJNLService {
	public IncomeWrapObj loadIncomeData(String portfolio, Date date) throws JNLException;
	
	/**
	 * 获取需要计息的股票
	 * @param portfolio
	 * @param date
	 * @return
	 * @throws JNLException
	 */
	public List<SecIncomeWrapObj> loadSecIncomeData(String portfolio, Date date) throws JNLException;
	
	/**
	 * 
	 * @param cashAcct	银行现金账户
	 * @param portnum	投资组合
	 * @param lastcalInteDate	上一轮的银行结息日
	 * @param inteStartDate		本轮的银行利息开始日
	 * @param inteEndDate		本轮的银行结息日
	 * @param ledger			当天日期
	 * @return
	 */
	public BankBalanceWrapObj loadBkBalanceWrap(BkCashAcct cashAcct, String portnum, Date lastcalInteDate, Date inteStartDate, Date inteEndDate, Date ledger);
}
