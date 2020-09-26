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

/**
 * 利率互换现金流计划
 * @author chengzhong
 *
 */
public class DfCashPlanDto {

    /**
     * 交易编号
     */
    private String tradeId;
    
    /**
     * 起息日
     */
    private Date vdate;
    
    /**
     * 首期起息日
     */
    private Date firstVdate;
    
    /**
     * 到期日
     */
    private Date mdate;
    
    /**
     * RATE_DATE
     */
    private Date rateDate;
    
    /**
     * ACC_START
     */
    private Date accStart;
    
    /**
     * ACC_END
     */
    private Date accEnd;
    
    /**
     * PAY_DATE
     */
    private Date payDate;
    
    
    /**
     * 是否历史利率
     */
    private boolean isHisRate;
    
    /**
     * 利率
     */
    private double rate;
    
    /**
     * 是否已支付
     */
    private boolean isPay;
    
    /**
     * 区间天数
     */
    private long accrualDays;
    
    /**
     * 固定端现金流
     */
    private double gudingduanXianjinliu;
    
    /**
     * 浮动端现金流
     */
    private double fudongduanXianjinliu;
    
    /**
     * 贴现因子
     */
    private double df;
    
    /**
     * 固定端现金流_折现
     */
    private double gudingduanXianjinliuZx;
    
    
    /**
     * 浮动端现金流_折现
     */
    private double fudongduanXianjinliuZx;
    
    
    /**
     * 现金流
     */
	private double cashflow;

    
    /**
     * 现金流_折现
     */
	private double cashflowDiscounted;
	
	
    
	public double getCashflowDiscounted() {
		return cashflowDiscounted;
	}

	public void setCashflowDiscounted(double cashflowDiscounted) {
		this.cashflowDiscounted = cashflowDiscounted;
	}

	public double getCashflow() {
		return cashflow;
	}

	public void setCashflow(double cashflow) {
		this.cashflow = cashflow;
	}

	public double getGudingduanXianjinliu() {
		return gudingduanXianjinliu;
	}

	public void setGudingduanXianjinliu(double gudingduanXianjinliu) {
		this.gudingduanXianjinliu = gudingduanXianjinliu;
	}

	public double getFudongduanXianjinliu() {
		return fudongduanXianjinliu;
	}

	public void setFudongduanXianjinliu(double fudongduanXianjinliu) {
		this.fudongduanXianjinliu = fudongduanXianjinliu;
	}

	public double getDf() {
		return df;
	}

	public void setDf(double df) {
		this.df = df;
	}

	public double getGudingduanXianjinliuZx() {
		return gudingduanXianjinliuZx;
	}

	public void setGudingduanXianjinliuZx(double gudingduanXianjinliuZx) {
		this.gudingduanXianjinliuZx = gudingduanXianjinliuZx;
	}

	public double getFudongduanXianjinliuZx() {
		return fudongduanXianjinliuZx;
	}

	public void setFudongduanXianjinliuZx(double fudongduanXianjinliuZx) {
		this.fudongduanXianjinliuZx = fudongduanXianjinliuZx;
	}

	public long getAccrualDays() {
		return accrualDays;
	}

	public void setAccrualDays(long accrualDays) {
		this.accrualDays = accrualDays;
	}

	public boolean isPay() {
		return isPay;
	}

	public void setPay(boolean isPay) {
		this.isPay = isPay;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public boolean isHisRate() {
		return isHisRate;
	}

	public void setHisRate(boolean isHisRate) {
		this.isHisRate = isHisRate;
	}

	public String getTradeId() {
		return tradeId;
	}

	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}

	public Date getVdate() {
		return vdate;
	}

	public void setVdate(Date vdate) {
		this.vdate = vdate;
	}

	public Date getFirstVdate() {
		return firstVdate;
	}

	public void setFirstVdate(Date firstVdate) {
		this.firstVdate = firstVdate;
	}

	public Date getMdate() {
		return mdate;
	}

	public void setMdate(Date mdate) {
		this.mdate = mdate;
	}

	public Date getRateDate() {
		return rateDate;
	}

	public void setRateDate(Date rateDate) {
		this.rateDate = rateDate;
	}

	public Date getAccStart() {
		return accStart;
	}

	public void setAccStart(Date accStart) {
		this.accStart = accStart;
	}

	public Date getAccEnd() {
		return accEnd;
	}

	public void setAccEnd(Date accEnd) {
		this.accEnd = accEnd;
	}

	public Date getPayDate() {
		return payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	} 
    
    
}
