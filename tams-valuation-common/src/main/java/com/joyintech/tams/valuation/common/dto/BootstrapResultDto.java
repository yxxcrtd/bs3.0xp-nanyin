/*
 * 文件名：Shibor3mRateDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：chengzhong
 * 修改时间：2019年12月25日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.common.dto;

import java.util.Date;


public class BootstrapResultDto {

    // 结束日
    private Date end_date;

    // 计息区间天数
    private long dsys;
    
    // 即期利率
    private double spot_yield;
    
    // 日期
    private Date date;
    
    // 标记
    private String mark;
    
    // 结束日
    private Date end_date1;

    // 计息区间天数
    private long dsys1;
    
    // 即期利率
    private double spot_yield1;
    
    // 结束日
    private Date end_date2;

    // 计息区间天数
    private long dsys2;
    
    // 即期利率
    private double spot_yield2;
    
    //DISTANCE_1
    private long distance_1;
    
    //DF
    private double df;
    
    //spot_date
    private Date spot_date;
    
    //DF_SPOT
	private double df_spot;
	
    //DATE_3M
    private Date date_3m;
    
    //DF_3M
	private double df_3m;
	
	//FORWARD_RATE
	private double forward_rate;
    
	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	public long getDsys() {
		return dsys;
	}

	public void setDsys(long dsys) {
		this.dsys = dsys;
	}

	public double getSpot_yield() {
		return spot_yield;
	}

	public void setSpot_yield(double spot_yield) {
		this.spot_yield = spot_yield;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public Date getEnd_date1() {
		return end_date1;
	}

	public void setEnd_date1(Date end_date1) {
		this.end_date1 = end_date1;
	}

	public long getDsys1() {
		return dsys1;
	}

	public void setDsys1(long dsys1) {
		this.dsys1 = dsys1;
	}

	public double getSpot_yield1() {
		return spot_yield1;
	}

	public void setSpot_yield1(double spot_yield1) {
		this.spot_yield1 = spot_yield1;
	}

	public Date getEnd_date2() {
		return end_date2;
	}

	public void setEnd_date2(Date end_date2) {
		this.end_date2 = end_date2;
	}

	public long getDsys2() {
		return dsys2;
	}

	public void setDsys2(long dsys2) {
		this.dsys2 = dsys2;
	}

	public double getSpot_yield2() {
		return spot_yield2;
	}

	public void setSpot_yield2(double spot_yield2) {
		this.spot_yield2 = spot_yield2;
	}

	public long getDistance_1() {
		return distance_1;
	}

	public void setDistance_1(long distance_1) {
		this.distance_1 = distance_1;
	}

	public double getDf() {
		return df;
	}

	public void setDf(double df) {
		this.df = df;
	}

	public Date getSpot_date() {
		return spot_date;
	}

	public void setSpot_date(Date spot_date) {
		this.spot_date = spot_date;
	}

	public double getDf_spot() {
		return df_spot;
	}

	public void setDf_spot(double df_spot) {
		this.df_spot = df_spot;
	}

	public Date getDate_3m() {
		return date_3m;
	}

	public void setDate_3m(Date date_3m) {
		this.date_3m = date_3m;
	}

	public double getDf_3m() {
		return df_3m;
	}

	public void setDf_3m(double df_3m) {
		this.df_3m = df_3m;
	}

	public double getForward_rate() {
		return forward_rate;
	}

	public void setForward_rate(double forward_rate) {
		this.forward_rate = forward_rate;
	}
    
 
}
