package com.jixu.journal.service;

import java.util.Date;
import java.util.List;

import com.jixu.common.exception.JNLException;
import com.jixu.journal.obj.CashBalanceWrapObj;
import com.jixu.journal.obj.STCostWrapObj;
import com.jixu.journal.obj.SecCashWrapObj;
import com.orm.obj.PortfolioHdr;

public interface GeneralJNLService {
	double loadCusipPrice(String cusip);
	/**
	 * 加载股票估值
	 * @param portfolio
	 * @param date		yyyymmdd
	 * @param assetType
	 * @return
	 * @throws JNLException
	 */
	List<STCostWrapObj> loadSecGenerelJNLRecord(PortfolioHdr portfolio, Date date, String assetType) throws JNLException;
	
	/***加载银行存款估值****/
	List<CashBalanceWrapObj> loadCashBalanceGenJNLRecord(PortfolioHdr portoflio, Date ledgerDate, Date lastDate) throws JNLException;
	
	/***加载证券清算款估值****/
	List<SecCashWrapObj> loadSecCashJNLRecord(PortfolioHdr portfolio, Date date) throws JNLException;
}
