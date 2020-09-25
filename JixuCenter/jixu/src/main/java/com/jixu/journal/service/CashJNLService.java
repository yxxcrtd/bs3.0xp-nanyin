package com.jixu.journal.service;

import java.util.Date;

import com.jixu.common.exception.JNLException;
import com.jixu.journal.obj.CashFlowWrapObj;
import com.orm.obj.PortfolioHdr;

public interface CashJNLService {
	CashFlowWrapObj loadCashFlowWrap(PortfolioHdr portfolio, Date date) throws JNLException;
}
