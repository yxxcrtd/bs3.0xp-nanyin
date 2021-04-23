package com.joyintech.service.entrust.etrtradesysinterf.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;

public class EtrTradePledgeDto {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 委托代码
     */
    private String entrustId;

    /**
     * 金融产品代码
     */
    private String finprodId;
    
    /**
     * 金融产品简称
     */
    private String finprodAbbr;
    
    /**
     * 交易场所，银行间、上交所、深交所、柜面等
     */
    private String tradeMarket;
    
    /**
     * 市场代码
     */
    private String finprodMarketId;
    
   
    /**
     * 质押面值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal pledgeFaceValue;
    
    /**
     * 质押数量
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal pledgeNumber;
  

    /**
     * 质押率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal pledgeRatio;
    
    /**
     * 质押金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal pledgeAmt;

	public String getEntrustId() {
		return entrustId;
	}

	public void setEntrustId(String entrustId) {
		this.entrustId = entrustId;
	}

	public String getFinprodId() {
		return finprodId;
	}

	public void setFinprodId(String finprodId) {
		this.finprodId = finprodId;
	}

	public String getFinprodAbbr() {
		return finprodAbbr;
	}

	public void setFinprodAbbr(String finprodAbbr) {
		this.finprodAbbr = finprodAbbr;
	}

	public String getTradeMarket() {
		return tradeMarket;
	}

	public void setTradeMarket(String tradeMarket) {
		this.tradeMarket = tradeMarket;
	}

	public String getFinprodMarketId() {
		return finprodMarketId;
	}

	public void setFinprodMarketId(String finprodMarketId) {
		this.finprodMarketId = finprodMarketId;
	}

	public BigDecimal getPledgeFaceValue() {
		return pledgeFaceValue;
	}

	public void setPledgeFaceValue(BigDecimal pledgeFaceValue) {
		this.pledgeFaceValue = pledgeFaceValue;
	}

	public BigDecimal getPledgeNumber() {
		return pledgeNumber;
	}

	public void setPledgeNumber(BigDecimal pledgeNumber) {
		this.pledgeNumber = pledgeNumber;
	}

	public BigDecimal getPledgeRatio() {
		return pledgeRatio;
	}

	public void setPledgeRatio(BigDecimal pledgeRatio) {
		this.pledgeRatio = pledgeRatio;
	}

	public BigDecimal getPledgeAmt() {
		return pledgeAmt;
	}

	public void setPledgeAmt(BigDecimal pledgeAmt) {
		this.pledgeAmt = pledgeAmt;
	}
    
}