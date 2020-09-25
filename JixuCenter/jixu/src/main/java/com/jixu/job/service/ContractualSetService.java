package com.jixu.job.service;

import java.util.Date;

import org.springframework.transaction.annotation.Transactional;

import com.jixu.common.type.ResultContainer;
import com.orm.obj.BkCashFlow;
import com.orm.obj.BkCashRcpy;

public interface ContractualSetService {
	// 把应收应付的钱全部结算到已收已付
	public ResultContainer settleToCashByRCPYID(int userID, int rcpyID, Date endDate);

	// 结转部分应收应付到已收已付
	public ResultContainer settleToCash(int userID, int rcpyID, double cashamt, Date endDate);

	// 把enddate以前的应收应付结转到已收已付
	public ResultContainer settleToCash(int userID, String portfolio, Date endDate);
	
	public ResultContainer settleToCash(int userID, String[] portnums, Date endDate);
	
	public ResultContainer deleteCashFlow(int cashID);
	
	public ResultContainer deleteCashFlow(BkCashFlow cashflow);
	
	public ResultContainer deleteCashFlow(BkCashFlow cashflow, BkCashRcpy cashrcpy);
	
	public ResultContainer deleteCashFlow(String portnum, Date ledgerDate);
	
	public ResultContainer deleteCash(int userID, String[] portnum, Date ledgerDate);
	
}
