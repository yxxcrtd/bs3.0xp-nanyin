/*
 * 文件名：Fr007RateDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：chengzhong
 * 修改时间：2019年12月25日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.common.dto;

import java.util.Date;


public class BootstrapDto {

    // 日期
    private Date date;
    //DF
    private double df;
    
    // 即期利率
    private double spot_yield;
    
	//FORWARD_RATE
	private double forward_rate;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getDf() {
		return df;
	}

	public void setDf(double df) {
		this.df = df;
	}

	public double getSpot_yield() {
		return spot_yield;
	}

	public void setSpot_yield(double spot_yield) {
		this.spot_yield = spot_yield;
	}

	public double getForward_rate() {
		return forward_rate;
	}

	public void setForward_rate(double forward_rate) {
		this.forward_rate = forward_rate;
	}

}
