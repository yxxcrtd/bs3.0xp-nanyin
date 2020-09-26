/*
 * 文件名：DfDealDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：chengzhong
 * 修改时间：2019年12月25日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.common.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;

/**
 * 交易数据
 * @author chengzhong
 *
 */
public class DfDealDto {

    /**
     * 账户
     */
    private String acctNo;
    
    /**
     * 交易日期
     */
    private Date tradeDate;
    
    /**
     * 交易方向，买入、卖出、收息、还本、申购、赎回等
     */
    private String ps;
    
    /**
     * 代码
     */
    private String code;
    
    /**
     * 交易金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tradeAmt;
    
    /**
     * 起息日
     */
    private Date vdate;
    
    /**
     * 期限品种
     */
    private String terms;
    
    /**
     * 到期日
     */
    private Date mdate;
    
    /**
     * 利率
     */
    private BigDecimal rate;
    
    /**
     * 交易编号
     */
    private String tradeId;
    
    /**
     * 交易费用
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal feeAmt;
    
    /**
     * 结算费用
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal settleFeeAmt;
    
    /**
     * 终止日期
     */
    private Date endDate;
    
    /**
     * 终止金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal endAmt;
    
    /**
     * 是否到期
     */
    private boolean isMdate;
    
    /**
     * 确认终止日期
     */
    private Date settleEndDate;
    
    /**
     * 首期起息日
     */
    private Date firstVdate;
    
    
    /**
     * 现金流计划(shibor3m使用)
     */
    private List<DfCashPlanDto> dfCashPlanList;
    
    /**
     * 浮动端现金流计划(fr007使用)
     */
    private List<DfCashPlanDto> dfFloatPlanList;
    
    /**
     * 固定端现金流计划(fr007使用)
     */
    private List<DfCashPlanDto> dfFixedPlanList;
    
    
    
    
    public List<DfCashPlanDto> getDfFloatPlanList() {
		return dfFloatPlanList;
	}

	public void setDfFloatPlanList(List<DfCashPlanDto> dfFloatPlanList) {
		this.dfFloatPlanList = dfFloatPlanList;
	}

	public List<DfCashPlanDto> getDfFixedPlanList() {
		return dfFixedPlanList;
	}

	public void setDfFixedPlanList(List<DfCashPlanDto> dfFixedPlanList) {
		this.dfFixedPlanList = dfFixedPlanList;
	}

	public List<DfCashPlanDto> getDfCashPlanList() {
		return dfCashPlanList;
	}

	public void setDfCashPlanList(List<DfCashPlanDto> dfCashPlanList) {
		this.dfCashPlanList = dfCashPlanList;
	}

	/**
     * 已实现损益
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal yishixianSunyi;
    
    /**
     * 浮动盈亏
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal fudongYinkui;
    
    /**
     * 已实现固定
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal yishixianGuding;
    
    /**
     * 未实现固定
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal weishixianGuding;
    
    
    /**
     * 已实现浮动
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal yishixianFudong;
    
    /**
     * 未实现浮动
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal weishixianFudong;

	public String getAcctNo() {
		return acctNo;
	}

	public void setAcctNo(String acctNo) {
		this.acctNo = acctNo;
	}

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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public BigDecimal getTradeAmt() {
		return tradeAmt;
	}

	public void setTradeAmt(BigDecimal tradeAmt) {
		this.tradeAmt = tradeAmt;
	}

	public Date getVdate() {
		return vdate;
	}

	public void setVdate(Date vdate) {
		this.vdate = vdate;
	}

	public String getTerms() {
		return terms;
	}

	public void setTerms(String terms) {
		this.terms = terms;
	}

	public Date getMdate() {
		return mdate;
	}

	public void setMdate(Date mdate) {
		this.mdate = mdate;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public String getTradeId() {
		return tradeId;
	}

	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}

	public BigDecimal getFeeAmt() {
		return feeAmt;
	}

	public void setFeeAmt(BigDecimal feeAmt) {
		this.feeAmt = feeAmt;
	}

	public BigDecimal getSettleFeeAmt() {
		return settleFeeAmt;
	}

	public void setSettleFeeAmt(BigDecimal settleFeeAmt) {
		this.settleFeeAmt = settleFeeAmt;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public BigDecimal getEndAmt() {
		return endAmt;
	}

	public void setEndAmt(BigDecimal endAmt) {
		this.endAmt = endAmt;
	}

	public boolean isMdate() {
		return isMdate;
	}

	public void setMdate(boolean isMdate) {
		this.isMdate = isMdate;
	}

	public Date getSettleEndDate() {
		return settleEndDate;
	}

	public void setSettleEndDate(Date settleEndDate) {
		this.settleEndDate = settleEndDate;
	}

	public Date getFirstVdate() {
		return firstVdate;
	}

	public void setFirstVdate(Date firstVdate) {
		this.firstVdate = firstVdate;
	}

	public BigDecimal getYishixianSunyi() {
		return yishixianSunyi;
	}

	public void setYishixianSunyi(BigDecimal yishixianSunyi) {
		this.yishixianSunyi = yishixianSunyi;
	}

	public BigDecimal getFudongYinkui() {
		return fudongYinkui;
	}

	public void setFudongYinkui(BigDecimal fudongYinkui) {
		this.fudongYinkui = fudongYinkui;
	}

	public BigDecimal getYishixianGuding() {
		return yishixianGuding;
	}

	public void setYishixianGuding(BigDecimal yishixianGuding) {
		this.yishixianGuding = yishixianGuding;
	}

	public BigDecimal getWeishixianGuding() {
		return weishixianGuding;
	}

	public void setWeishixianGuding(BigDecimal weishixianGuding) {
		this.weishixianGuding = weishixianGuding;
	}

	public BigDecimal getYishixianFudong() {
		return yishixianFudong;
	}

	public void setYishixianFudong(BigDecimal yishixianFudong) {
		this.yishixianFudong = yishixianFudong;
	}

	public BigDecimal getWeishixianFudong() {
		return weishixianFudong;
	}

	public void setWeishixianFudong(BigDecimal weishixianFudong) {
		this.weishixianFudong = weishixianFudong;
	}
    
	/**
	 * 币种 20200116 add
	 */
	private String ccy;
	
	public String getCcy() {
		return ccy;
	}

	public void setCcy(String ccy) {
		this.ccy = ccy;
	}
	
}
