package com.jixu.msg.dto;

import com.jixu.framework.msg.BaseMsgDto;

/**
 * 现金流浮动利率Dto
 * @author Wang Zhongyuan
 */
public class CashFlowFloatDto extends BaseMsgDto{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//基准利率
	private String secid;
	
	//计算方法
	private String vrMethod;
	
	//利差
	private String vrBasisPtAdjustment;
	
	//最大值
	private String max;
	
	//最小值
	private String min;
	
	//重置频率
	private String frequency;
	
	//重置月份
	private String month;
	
	//重置日
	private String day;
	
	//付款日后N天
	private String vrResetNDays;
	
	//调整月
	private String vrDeterAdjMo;
	
	//调整日
	private String vrDeterAdjDays;

	public String getSecid() {
		return secid;
	}

	public void setSecid(String secid) {
		this.secid = secid;
	}

	public String getVrMethod() {
		return vrMethod;
	}

	public void setVrMethod(String vrMethod) {
		this.vrMethod = vrMethod;
	}

	public String getVrBasisPtAdjustment() {
		return vrBasisPtAdjustment;
	}

	public void setVrBasisPtAdjustment(String vrBasisPtAdjustment) {
		this.vrBasisPtAdjustment = vrBasisPtAdjustment;
	}

	public String getMax() {
		return max;
	}

	public void setMax(String max) {
		this.max = max;
	}

	public String getMin() {
		return min;
	}

	public void setMin(String min) {
		this.min = min;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getVrResetNDays() {
		return vrResetNDays;
	}

	public void setVrResetNDays(String vrResetNDays) {
		this.vrResetNDays = vrResetNDays;
	}

	public String getVrDeterAdjMo() {
		return vrDeterAdjMo;
	}

	public void setVrDeterAdjMo(String vrDeterAdjMo) {
		this.vrDeterAdjMo = vrDeterAdjMo;
	}

	public String getVrDeterAdjDays() {
		return vrDeterAdjDays;
	}

	public void setVrDeterAdjDays(String vrDeterAdjDays) {
		this.vrDeterAdjDays = vrDeterAdjDays;
	}
	
}
