package com.joyintech.tams.invest.trade.buyoutrepo.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.tams.invest.trade.bond.dto.TrdBondInvestTradeDto;

public class TrdOutRightRepoTrade004Dto extends TrdBondInvestTradeDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5362638937594227730L;
	
	/**
	 * 回购起息日
	 */
	private Date repoVdate;
	
	/**
	 * 回购到期日
	 */
	private Date repoMdate;
	
	/**
	 * 回购利率
	 */
	@JsonSerialize(using=BigDecimalSerializer.class)
	private BigDecimal repoRate;
	
	/**
	 * 计算类型
	 */
	private String repoCalculateField;
	
	/**
	 * 首次净价
	 */
	@JsonSerialize(using=BigDecimalSerializer.class)
	private BigDecimal fCleanPrice;
	
	/**
	 * 到期净价
	 */
	@JsonSerialize(using=BigDecimalSerializer.class)
	private BigDecimal mCleanPrice;

	/**
	 * 计算模式
	 */
	private String calcModel;
	
	

	public Date getRepoVdate() {
		return repoVdate;
	}

	public void setRepoVdate(Date repoVdate) {
		this.repoVdate = repoVdate;
	}

	public Date getRepoMdate() {
		return repoMdate;
	}

	public void setRepoMdate(Date repoMdate) {
		this.repoMdate = repoMdate;
	}

	public BigDecimal getRepoRate() {
		return repoRate;
	}

	public void setRepoRate(BigDecimal repoRate) {
		this.repoRate = repoRate;
	}

	public String getRepoCalculateField() {
		return repoCalculateField;
	}

	public void setRepoCalculateField(String repoCalculateField) {
		this.repoCalculateField = repoCalculateField;
	}

	public BigDecimal getfCleanPrice() {
		return fCleanPrice;
	}

	public void setfCleanPrice(BigDecimal fCleanPrice) {
		this.fCleanPrice = fCleanPrice;
	}

	public BigDecimal getmCleanPrice() {
		return mCleanPrice;
	}

	public void setmCleanPrice(BigDecimal mCleanPrice) {
		this.mCleanPrice = mCleanPrice;
	}

	public String getCalcModel() {
		return calcModel;
	}

	public void setCalcModel(String calcModel) {
		this.calcModel = calcModel;
	}
}
