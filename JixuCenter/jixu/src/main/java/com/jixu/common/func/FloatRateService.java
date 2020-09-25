package com.jixu.common.func;

import java.util.Date;

public abstract class FloatRateService {
	
	/**
	 * 计算浮动利率
	 * @return
	 */
	public abstract double calFloatRate(String inputRule,Date ledgerDate);
	/**
	 * CPI浮动利率计算公式
	 * @return
	 */
	public abstract double getCPIRuler(double preRate,double changedRate);
	/**
	 * 利率基准类名称
	 * @return
	 */
	public abstract String getFuncName();
	
	/**
	 * 功能模块的使用说明
	 * @return
	 */
	public abstract String fundUseDesc();
	
	
	/**
	 * 把[abc]转为abc
	 * @param inputValue
	 * @return
	 */
	protected String getColumName(String inputValue){
		char ach='[';
		char bch=']';
		int apoint=inputValue.indexOf(ach);
		int bpoint=inputValue.indexOf(bch);
		if(apoint>=0 && bpoint>apoint){
			inputValue=inputValue.substring(apoint+1, bpoint);
		}
		return inputValue;
	}
}
