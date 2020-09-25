package com.jixu.journal.obj;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.orm.obj.IssueInfo;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.Tran;
import com.orm.obj.TranClo;

/**
 *	做账当天,头寸不为0的证券头寸 
 * @author "taotaizhu"
 *
 */
public class SecIncomeWrapObj {
	
	private PortfolioHdr portfolio;

	private IssueInfo security;
	
	private Date ledgerDate;

	private TranClo clo;
	
	private TranClo lastClo;
	
	/**
	 * 当天的利率调整交易
	 */
	private List<Tran> incomeUpdateTrans=new ArrayList<Tran>();
	
	public IssueInfo getSecurity() {
		return security;
	}

	public void setSecurity(IssueInfo security) {
		this.security = security;
	}

	public TranClo getClo() {
		return clo;
	}

	public void setClo(TranClo clo) {
		this.clo = clo;
	}

	public PortfolioHdr getPortfolio() {
		return portfolio;
	}

	public void setPortfolio(PortfolioHdr portfolio) {
		this.portfolio = portfolio;
	}

	public Date getLedgerDate() {
		return ledgerDate;
	}

	public void setLedgerDate(Date ledgerDate) {
		this.ledgerDate = ledgerDate;
	}

	public TranClo getLastClo() {
		return lastClo;
	}

	public void setLastClo(TranClo lastClo) {
		this.lastClo = lastClo;
	}

	/**
	 * 当天的利率调整交易
	 * @return
	 */
	public List<Tran> getIncomeUpdateTrans() {
		return incomeUpdateTrans;
	}

	public void setIncomeUpdateTrans(List<Tran> incomeUpdateTrans) {
		this.incomeUpdateTrans = incomeUpdateTrans;
	}
}
