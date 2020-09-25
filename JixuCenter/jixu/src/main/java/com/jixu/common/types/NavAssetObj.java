package com.jixu.common.types;

import java.util.Date;

import com.jixu.common.util.RoundUtil;

public class NavAssetObj {
	
	private Date navDate;
	/************单位净值*************/
	private double navPerShare;
	
	/************净资产***********/
	private double netAsset;
	
	/***********份额**************/
	private double shares;
	
	/***********收入**************/
	private double incomes;
	
	/***********开支**************/
	private double expense;
	
	/************累计单位净值**********/
	private double accNavPerShare;
	
	private double dividends;
	
	public NavAssetObj() {
	}
	
	/**
	 * 
	 * @param navDate	nav.getNavDate()
	 * @param capital	nav.getCapital();			//资本
	 * @param capitalRG	nav.getCapitalRG();		//已实现收益
	 * @param caoitalUnReal nav.getCapitalUnreal();//未实现收益
	 * @param income nav.getIncome();				//收入
	 * @param expenses nav.getExpenses();			//开支
	 * @param shares nav.getSharesOutStanding();							//份额
	 * @param perNav nav.getNavAmount();
	 */
	public NavAssetObj(Date navDate, Double capital, Double capitalRG, Double caoitalUnReal, Double income, Double expenses, Double shares, Double perNav) {
		double netAsset=RoundUtil.roundMoney2Dec(capital+capitalRG-caoitalUnReal+income-expenses);	//净资产
		
		this.setNetAsset(netAsset);
		this.setShares(shares);
		this.setNavPerShare(perNav);
		this.setNavDate(navDate);
		this.setExpense(expenses);
		this.setIncomes(income);
	}

	public double getNavPerShare() {
		return navPerShare;
	}

	public void setNavPerShare(double navPerShare) {
		this.navPerShare = navPerShare;
	}

	public double getNetAsset() {
		return netAsset;
	}

	public void setNetAsset(double netAsset) {
		this.netAsset = netAsset;
	}

	public double getShares() {
		return shares;
	}

	public void setShares(double shares) {
		this.shares = shares;
	}

	public double getAccNavPerShare() {
		return accNavPerShare;
	}

	public void setAccNavPerShare(double accNavPerShare) {
		this.accNavPerShare = accNavPerShare;
	}

	public double getDividends() {
		return dividends;
	}

	public void setDividends(double dividends) {
		this.dividends = dividends;
	}

	public Date getNavDate() {
		return navDate;
	}

	public void setNavDate(Date navDate) {
		this.navDate = navDate;
	}

	public double getIncomes() {
		return incomes;
	}

	public void setIncomes(double incomes) {
		this.incomes = incomes;
	}

	public double getExpense() {
		return expense;
	}

	public void setExpense(double expense) {
		this.expense = expense;
	}
}
