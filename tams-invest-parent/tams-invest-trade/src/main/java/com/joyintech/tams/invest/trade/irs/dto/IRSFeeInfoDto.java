/*
 * 文件名：IRSFeeInfoDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：admin
 * 修改时间：2019年7月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.trade.irs.dto;

/**
 * 
 *  irs 费率信息列表使用dto
 * 〈功能详细描述〉
 * @author qiaominghui
 * @version 1.0 2019年7月17日
 * @see IRSFeeInfoDto
 * @since 1.0
 */
public class IRSFeeInfoDto {

	/**
	 * 构造函数
	 * @param cycle
	 * @param cycleUnit
	 * @param rate
	 */
	public IRSFeeInfoDto(int cycle,String cycleUnit,int rate) {
		this.cycle = cycle;
		this.cycleUnit = cycleUnit;
		this.rate = rate;
	}
	/**
	 * 周期
	 */
	private int cycle;
	
	/**
	 * 周期单位
	 */
	private String cycleUnit;

	/**
	 * 利率(/百万)
	 */
	private int rate;

	/**
	 * 获取 cycle
	 * @return cycle.
	 */
	public int getCycle() {
		return cycle;
	}

	/**
	 * 设置 cycle
	 * @param cycle cycle
	 */
	public void setCycle(int cycle) {
		this.cycle = cycle;
	}

	/**
	 * 获取 cycleUnit
	 * @return cycleUnit.
	 */
	public String getCycleUnit() {
		return cycleUnit;
	}

	/**
	 * 设置 cycleUnit
	 * @param cycleUnit cycleUnit
	 */
	public void setCycleUnit(String cycleUnit) {
		this.cycleUnit = cycleUnit;
	}

	/**
	 * 获取 rate
	 * @return rate.
	 */
	public int getRate() {
		return rate;
	}

	/**
	 * 设置 rate
	 * @param rate rate
	 */
	public void setRate(int rate) {
		this.rate = rate;
	}
	
}
