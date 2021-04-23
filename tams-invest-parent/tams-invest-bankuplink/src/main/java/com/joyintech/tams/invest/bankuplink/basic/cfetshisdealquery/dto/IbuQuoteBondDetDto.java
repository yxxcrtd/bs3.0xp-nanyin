package com.joyintech.tams.invest.bankuplink.basic.cfetshisdealquery.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;

public class IbuQuoteBondDetDto extends BaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6615291769230558076L;
	
	private String detId;
	
	private String reqId;
	
	private Long version;
	
	private String cfetsReqId;
	
	private String quoteId;
	
	private String cpPartyId;
	
	private String cpTrader;
	
	@JsonSerialize(using = BigDecimalSerializer.class)
	private BigDecimal unitCprice;
	
	@JsonSerialize(using = BigDecimalSerializer.class)
	private BigDecimal unitInt;
	
	@JsonSerialize(using = BigDecimalSerializer.class)
	private BigDecimal unitFprice;
	
	@JsonSerialize(using = BigDecimalSerializer.class)
	private BigDecimal parValue;
	
	@JsonSerialize(using = BigDecimalSerializer.class)
	private BigDecimal shareAmt;
	
	@JsonSerialize(using = BigDecimalSerializer.class)
	private BigDecimal prinAmt;
	
	@JsonSerialize(using = BigDecimalSerializer.class)
	private BigDecimal parValueAmt;
	
	@JsonSerialize(using = BigDecimalSerializer.class)
	private BigDecimal cpriceAmt;
	
	@JsonSerialize(using = BigDecimalSerializer.class)
	private BigDecimal intAmt;
	
	@JsonSerialize(using = BigDecimalSerializer.class)
	private BigDecimal fpriceAmt;
	
	@JsonSerialize(using = BigDecimalSerializer.class)
	private BigDecimal yield;
	
	@JsonSerialize(using = BigDecimalSerializer.class)
	private BigDecimal exerYield;
	
	private String tradeStatus;
	
	private String sendStatus;
	
	private String execid;
	
	private Date reEffTime;
	
	private String remark;
	
	private String calculateField;

	public String getDetId() {
		return this.detId;
	}

	public void setDetId(String detId) {
		this.detId = detId == null ? null : detId.trim();
	}

	public String getReqId() {
		return this.reqId;
	}

	public void setReqId(String reqId) {
		this.reqId = reqId == null ? null : reqId.trim();
	}

	public Long getVersion() {
		return this.version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public String getCfetsReqId() {
		return this.cfetsReqId;
	}

	public void setCfetsReqId(String cfetsReqId) {
		this.cfetsReqId = cfetsReqId == null ? null : cfetsReqId.trim();
	}

	public String getQuoteId() {
		return this.quoteId;
	}

	public void setQuoteId(String quoteId) {
		this.quoteId = quoteId == null ? null : quoteId.trim();
	}

	public String getCpPartyId() {
		return this.cpPartyId;
	}

	public void setCpPartyId(String cpPartyId) {
		this.cpPartyId = cpPartyId == null ? null : cpPartyId.trim();
	}

	public String getCpTrader() {
		return this.cpTrader;
	}

	public void setCpTrader(String cpTrader) {
		this.cpTrader = cpTrader == null ? null : cpTrader.trim();
	}

	public BigDecimal getUnitCprice() {
		return this.unitCprice;
	}

	public void setUnitCprice(BigDecimal unitCprice) {
		this.unitCprice = unitCprice;
	}

	public BigDecimal getUnitInt() {
		return this.unitInt;
	}

	public void setUnitInt(BigDecimal unitInt) {
		this.unitInt = unitInt;
	}

	public BigDecimal getUnitFprice() {
		return this.unitFprice;
	}

	public void setUnitFprice(BigDecimal unitFprice) {
		this.unitFprice = unitFprice;
	}

	public BigDecimal getParValue() {
		return this.parValue;
	}

	public void setParValue(BigDecimal parValue) {
		this.parValue = parValue;
	}

	public BigDecimal getShareAmt() {
		return this.shareAmt;
	}

	public void setShareAmt(BigDecimal shareAmt) {
		this.shareAmt = shareAmt;
	}

	public BigDecimal getPrinAmt() {
		return this.prinAmt;
	}

	public void setPrinAmt(BigDecimal prinAmt) {
		this.prinAmt = prinAmt;
	}

	public BigDecimal getParValueAmt() {
		return this.parValueAmt;
	}

	public void setParValueAmt(BigDecimal parValueAmt) {
		this.parValueAmt = parValueAmt;
	}

	public BigDecimal getCpriceAmt() {
		return this.cpriceAmt;
	}

	public void setCpriceAmt(BigDecimal cpriceAmt) {
		this.cpriceAmt = cpriceAmt;
	}

	public BigDecimal getIntAmt() {
		return this.intAmt;
	}

	public void setIntAmt(BigDecimal intAmt) {
		this.intAmt = intAmt;
	}

	public BigDecimal getFpriceAmt() {
		return this.fpriceAmt;
	}

	public void setFpriceAmt(BigDecimal fpriceAmt) {
		this.fpriceAmt = fpriceAmt;
	}

	public BigDecimal getYield() {
		return this.yield;
	}

	public void setYield(BigDecimal yield) {
		this.yield = yield;
	}

	public BigDecimal getExerYield() {
		return this.exerYield;
	}

	public void setExerYield(BigDecimal exerYield) {
		this.exerYield = exerYield;
	}

	public String getTradeStatus() {
		return this.tradeStatus;
	}

	public void setTradeStatus(String tradeStatus) {
		this.tradeStatus = tradeStatus == null ? null : tradeStatus.trim();
	}

	public String getSendStatus() {
		return this.sendStatus;
	}

	public void setSendStatus(String sendStatus) {
		this.sendStatus = sendStatus == null ? null : sendStatus.trim();
	}

	public String getExecid() {
		return this.execid;
	}

	public void setExecid(String execid) {
		this.execid = execid == null ? null : execid.trim();
	}

	public Date getReEffTime() {
		return this.reEffTime;
	}

	@JsonDeserialize(using = CustomDateDeserialize.class)
	public void setReEffTime(Date reEffTime) {
		this.reEffTime = reEffTime;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	public String getCalculateField() {
		return this.calculateField;
	}

	public void setCalculateField(String calculateField) {
		this.calculateField = calculateField == null ? null : calculateField.trim();
	}

	
	
	
	
	private String cpPartyName;
	
	private String cpTraderName;

	public String getCpPartyName() {
		return this.cpPartyName;
	}

	public void setCpPartyName(String cpPartyName) {
		this.cpPartyName = cpPartyName;
	}

	public String getCpTraderName() {
		return this.cpTraderName;
	}

	public void setCpTraderName(String cpTraderName) {
		this.cpTraderName = cpTraderName;
	}

}
