package com.jixu.journal.obj;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import com.orm.obj.PortfolioHdr;
import com.orm.obj.TranBasic;

public class CashFlowWrapObj implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6162026011221527360L;
	
	public CashFlowWrapObj() {
		cashFlowHM=new HashMap<TranBasic, List<CashFlowObj>>();
	}
	
	private PortfolioHdr portfolioHdr;
	
	/***股票交易现金凭证****/
	private HashMap<TranBasic, List<CashFlowObj>> cashFlowHM;
	
	public HashMap<TranBasic, List<CashFlowObj>> getCashFlowHM() {
		return cashFlowHM;
	}

	public void setCashFlowHM(HashMap<TranBasic, List<CashFlowObj>> cashFlowHM) {
		this.cashFlowHM = cashFlowHM;
	}

	public PortfolioHdr getPortfolioHdr() {
		return portfolioHdr;
	}

	public void setPortfolioHdr(PortfolioHdr portfolioHdr) {
		this.portfolioHdr = portfolioHdr;
	}
}
