/*
 * 文件名：MoneyTaAutoCalResult.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：May 25, 2019
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.batch.investratechange.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;

/**
 * 利率互换估值批处理dto
 * @author taoran
 * @version 20200114
 * @see InvestRateChangeReaderDto
 * @since 1.0
 */
public class InvestRateChangeReaderDto extends NeedOperateEntity {
	
	/**
	 * 交易日期
	 */
	private Date tradeDate;
	/**
	 * 交易方向
	 */
	private String ps;
	/**
	 * 基准编号
	 */
	private String benchMarkId;
	/**
	 * 交易金额
	 */
	private BigDecimal tradeAmt;
	/**
	 * 起息日
	 */
	private Date vDate;
	/**
	 * 到期日
	 */
	private Date mDate;
	/**
	 * 期限品种
	 */
	private String termType;
	/**
	 * 利率
	 */
	private BigDecimal yield;
	/**
	 * 首期起息日
	 */
	private Date firstvDate;
	/**
	 * 终止日期
	 */
	private Date endDate;
	/**
	 * 确认终止日期
	 */
	private Date sureEndDate;
	/**
	 * 是否到期
	 */
	private String isOverTime;
	/**
	 * 交易日历
	 */
	private String calendarId;
	/**
	 * 币种
	 */
	private String ccy;
	/**
	 * 产品代码
	 */
	private String finprodId;
	/**
     * 账套代码
     */
    private String booksetId;

    /**
     * 账套日期
     */
    private Date booksetDate;

    /**
     * 发生日期
     */
    private Date happenDate;

    /**
     * 入账日期
     */
    private Date bookDate;
	
	private List<InvestRateChangeReaderDto> investRateChangeReaderList;
	
	public Date getTradeDate() {
		return tradeDate;
	}
	public void setTradeDate(Date tradeDate) {
		this.tradeDate = tradeDate;
	}
	public String getPs() {
		return ps;
	}
	public void setPs(String ps) {
		this.ps = ps;
	}
	public String getBenchMarkId() {
		return benchMarkId;
	}
	public void setBenchMarkId(String benchMarkId) {
		this.benchMarkId = benchMarkId;
	}
	public BigDecimal getTradeAmt() {
		return tradeAmt;
	}
	public void setTradeAmt(BigDecimal tradeAmt) {
		this.tradeAmt = tradeAmt;
	}
	public Date getvDate() {
		return vDate;
	}
	public void setvDate(Date vDate) {
		this.vDate = vDate;
	}
	public Date getmDate() {
		return mDate;
	}
	public void setmDate(Date mDate) {
		this.mDate = mDate;
	}
	public String getTermType() {
		return termType;
	}
	public void setTermType(String termType) {
		this.termType = termType;
	}
	public Date getFirstvDate() {
		return firstvDate;
	}
	public void setFirstvDate(Date firstvDate) {
		this.firstvDate = firstvDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Date getSureEndDate() {
		return sureEndDate;
	}
	public void setSureEndDate(Date sureEndDate) {
		this.sureEndDate = sureEndDate;
	}
	public BigDecimal getYield() {
		return yield;
	}
	public void setYield(BigDecimal yield) {
		this.yield = yield;
	}
	public String getIsOverTime() {
		return isOverTime;
	}
	public void setIsOverTime(String isOverTime) {
		this.isOverTime = isOverTime;
	}
	public String getCalendarId() {
		return calendarId;
	}
	public void setCalendarId(String calendarId) {
		this.calendarId = calendarId;
	}
	public String getCcy() {
		return ccy;
	}
	public void setCcy(String ccy) {
		this.ccy = ccy;
	}
	public String getFinprodId() {
		return finprodId;
	}
	public void setFinprodId(String finprodId) {
		this.finprodId = finprodId;
	}
	public String getBooksetId() {
		return booksetId;
	}
	public void setBooksetId(String booksetId) {
		this.booksetId = booksetId;
	}
	public Date getBooksetDate() {
		return booksetDate;
	}
	public void setBooksetDate(Date booksetDate) {
		this.booksetDate = booksetDate;
	}
	public Date getHappenDate() {
		return happenDate;
	}
	public void setHappenDate(Date happenDate) {
		this.happenDate = happenDate;
	}
	public Date getBookDate() {
		return bookDate;
	}
	public void setBookDate(Date bookDate) {
		this.bookDate = bookDate;
	}
	public List<InvestRateChangeReaderDto> getInvestRateChangeReaderList() {
		return investRateChangeReaderList;
	}
	public void setInvestRateChangeReaderList(List<InvestRateChangeReaderDto> investRateChangeReaderList) {
		this.investRateChangeReaderList = investRateChangeReaderList;
	}
	
}
