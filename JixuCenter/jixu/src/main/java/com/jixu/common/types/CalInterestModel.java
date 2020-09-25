package com.jixu.common.types;

import java.util.Date;
import java.util.List;

import com.orm.obj.CashFlowCfg;

/** 
 * @ClassName 		CalInterestModel
 * @author  		Pan Wei 
 * @Date 			2017年5月24日 下午1:58:39 	
 * @Description		[]  
 */

public class CalInterestModel {

	/**
	 * 现金流配置
	 */
	private CashFlowCfg cashFlowCfg;
	
	/**
	 * 向后/向前加载
	 */
	private int bookNBDays;
	
	/**
	 * 如果结息日没有值,为null,则每天计息
	 * 结息日
	 */
	private Date acuDate;
	
	/**
	 * 交易日
	 */
	private Date tradeDate;
	
	/**
	 * 上一个计息日
	 */
	private Date lastCalDate;
	
	/**
	 * 当前日期
	 */
	private Date curDate;
	
	/**
	 * 利率
	 */
	private double acRate;
	
	/**
	 * 数量
	 */
	private double amount;
	
	/**
	 * 上一天的汇率
	 */
	private double lastFxRate;
	
	/**
	 * 当天的汇率
	 */
	private double todayFxRate;
	
	/**
	 * 节假日
	 */
	private List<Date> holidayList;
	
	/**
	 * 收益比率,用在万份收益等可变利率的计算
	 */
	private double yield;

	/**
	 * 获取 现金流配置
	 */
	
	public CashFlowCfg getCashFlowCfg() {
		return cashFlowCfg;
	}

	/**
	 * 设置 现金流配置
	 */
	
	public void setCashFlowCfg(CashFlowCfg cashFlowCfg) {
		this.cashFlowCfg = cashFlowCfg;
	}

	/**
	 * 获取 向后/向前加载
	 */
	
	public int getBookNBDays() {
		return bookNBDays;
	}

	/**
	 * 设置 向后/向前加载
	 */
	
	public void setBookNBDays(int bookNBDays) {
		this.bookNBDays = bookNBDays;
	}

	/**
	 * 获取 结息日
	 */
	
	public Date getAcuDate() {
		return acuDate;
	}

	/**
	 * 设置 结息日
	 */
	
	public void setAcuDate(Date acuDate) {
		this.acuDate = acuDate;
	}

	/**
	 * 获取 交易日
	 */
	
	public Date getTradeDate() {
		return tradeDate;
	}

	/**
	 * 设置 交易日
	 */
	
	public void setTradeDate(Date tradeDate) {
		this.tradeDate = tradeDate;
	}

	/**
	 * 获取 上一个计息日
	 */
	
	public Date getLastCalDate() {
		return lastCalDate;
	}

	/**
	 * 设置 上一个计息日
	 */
	
	public void setLastCalDate(Date lastCalDate) {
		this.lastCalDate = lastCalDate;
	}

	/**
	 * 获取 当前日期
	 */
	
	public Date getCurDate() {
		return curDate;
	}

	/**
	 * 设置 当前日期
	 */
	
	public void setCurDate(Date curDate) {
		this.curDate = curDate;
	}

	/**
	 * 获取 利率
	 */
	
	public double getAcRate() {
		return acRate;
	}

	/**
	 * 设置 利率
	 */
	
	public void setAcRate(double acRate) {
		this.acRate = acRate;
	}

	/**
	 * 获取 数量
	 */
	
	public double getAmount() {
		return amount;
	}

	/**
	 * 设置 数量
	 */
	
	public void setAmount(double amount) {
		this.amount = amount;
	}

	/**
	 * 获取 上一天汇率
	 */
	
	public double getLastFxRate() {
		return lastFxRate;
	}

	/**
	 * 设置 上一天汇率
	 */
	
	public void setLastFxRate(double lastFxRate) {
		this.lastFxRate = lastFxRate;
	}

	/**
	 * 获取 当天汇率
	 */
	
	public double getTodayFxRate() {
		return todayFxRate;
	}

	/**
	 * 设置 当天汇率
	 */
	
	public void setTodayFxRate(double todayFxRate) {
		this.todayFxRate = todayFxRate;
	}

	/**
	 * 获取 节假日
	 */
	
	public List<Date> getHolidayList() {
		return holidayList;
	}

	/**
	 * 设置 holidayList
	 */
	
	public void setHolidayList(List<Date> holidayList) {
		this.holidayList = holidayList;
	}

	/**
	 * 获取 yield
	 */
	
	public double getYield() {
		return yield;
	}

	/**
	 * 设置 yield
	 */
	
	public void setYield(double yield) {
		this.yield = yield;
	}
}
