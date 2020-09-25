/*
 * 文件名：TrdOutRightRepoTrade001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2019年02月12日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.tams.invest.trade.buyoutrepo.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;

public class TrdOutRightRepoTrade003Dto {
	
	/**
	 * 委托金额
	 */
	@JsonSerialize(using = BigDecimalSerializer.class)
	private BigDecimal entrustAmt;
	
	/**
	 * 委托的回购利率
	 */
	@JsonSerialize(using = BigDecimalSerializer.class)
	private BigDecimal entrustRepoRate;
	
	/**
	 * 委托的回购起息日
	 */
	private Date entrustVdate;
	
	/**
	 * 委托的回购到期日
	 */
	private Date entrustMdate;
	
	/**
	 * 委托的回购期限From
	 */
	private Long entrustTermStart;
	
	/**
	 * 委托的回购期限To
	 */
	private Long entrustTermEnd;
	
	/**
	 * 委托的期限品种
	 */
	private String entrustTermType;

	public BigDecimal getEntrustAmt() {
		return entrustAmt;
	}

	public void setEntrustAmt(BigDecimal entrustAmt) {
		this.entrustAmt = entrustAmt;
	}

	public BigDecimal getEntrustRepoRate() {
		return entrustRepoRate;
	}

	public void setEntrustRepoRate(BigDecimal entrustRepoRate) {
		this.entrustRepoRate = entrustRepoRate;
	}

	public Date getEntrustVdate() {
		return entrustVdate;
	}

	public void setEntrustVdate(Date entrustVdate) {
		this.entrustVdate = entrustVdate;
	}

	public Date getEntrustMdate() {
		return entrustMdate;
	}

	public void setEntrustMdate(Date entrustMdate) {
		this.entrustMdate = entrustMdate;
	}

	public Long getEntrustTermStart() {
		return entrustTermStart;
	}

	public void setEntrustTermStart(Long entrustTermStart) {
		this.entrustTermStart = entrustTermStart;
	}

	public Long getEntrustTermEnd() {
		return entrustTermEnd;
	}

	public void setEntrustTermEnd(Long entrustTermEnd) {
		this.entrustTermEnd = entrustTermEnd;
	}

	public String getEntrustTermType() {
		return entrustTermType;
	}

	public void setEntrustTermType(String entrustTermType) {
		this.entrustTermType = entrustTermType;
	}
	
}
