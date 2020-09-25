package com.jixu.common.types;

/** 
 * @ClassName 		PayChangeType
 * @author  		Pan Wei 
 * @Date 			2018年8月7日 上午11:05:07 	
 * @Description		[]  
 */

public class ParChange {
	
	public ParChange(double initPar) {
		this.setTodayParValue(initPar);
		this.setYesterdayParValue(initPar);
		this.setPayParToday(0);
		this.setParParRateToday(0);
	}
	
	/**
	 * 前一天的面值
	 */
	private double yesterdayParValue;
	
	/**
	 * 当天的面值
	 */
	private double todayParValue;
	
	/**
	 * 今天减少的面值
	 */
	private double payParToday;
	
	/**
	 * 今天减少的面值比率
	 */
	private double parParRateToday;

	/** 
	 * 前一天的面值
	 * @return 
	 */
	public double getYesterdayParValue() {
		return yesterdayParValue;
	}

	public void setYesterdayParValue(double yesterdayParValue) {
		this.yesterdayParValue = yesterdayParValue;
	}

	/**
	 * 当天的面值
	 * @return
	 */
	public double getTodayParValue() {
		return todayParValue;
	}

	public void setTodayParValue(double todayParValue) {
		this.todayParValue = todayParValue;
	}

	/**
	 * 今天减少的面值
	 * @return
	 */
	public double getPayParToday() {
		return payParToday;
	}

	public void setPayParToday(double payParToday) {
		this.payParToday = payParToday;
	}

	/**
	 * 今天减少的面值比率
	 * @return
	 */
	public double getParParRateToday() {
		return parParRateToday;
	}

	public void setParParRateToday(double parParRateToday) {
		this.parParRateToday = parParRateToday;
	}
}
