package com.jixu.journal.service;

import java.util.Date;
import java.util.List;

import com.jixu.common.exception.JNLException;
import com.jixu.journal.obj.TransactionWrapObj;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.TranBasic;

public interface PurSaleService {
	/*****加载交易数据。当Cusip为null时，加载当天多个cusip的交易记录****/
	List<TransactionWrapObj> loadTransData(Date tradedate, PortfolioHdr portfolio, String cusip) throws JNLException;
	
	List<TranBasic> loadTranIncDate(String portnum, Date tradeDate, String cusup);
}
