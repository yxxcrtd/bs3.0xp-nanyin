package com.joyintech.tams.invest.batch.assetintdatecarryover.dto;

import java.math.BigDecimal;
import java.util.Date;

public class AssetIntdateCarryForwardDto {
	/**
     * 金融产品代码
     */
    private String finprodId;

    /**
     * 金融产品类型
     */
    private String finprodType;
    /**
     * 金融产品类型
     */
    private String finprodType2;

    /**
     * 调整后的计算开始日
     */
    private  Date vdate;

    /**
     * 调整后的计算结束日
     */
    private Date mdate;

    /**
     * 计划支付本金，针对现金流类型为本金的
     */
    private BigDecimal prinAmt;

    /**
     * 计划支付利息，还本计息区间对应的利息
     */
    private BigDecimal intAmt;


    /**
     * 现金流一级类型，本金、利息、费用
     */
    private String cashType1;

    /**
     * 币种
     */
    private String ccy;


    /**
     * 发行规模
     */
    private BigDecimal issueAmt;


    /**
     * 通道代码
     */
    private String chlFinprodId;

    /**
     * 计划支付日
     */
    private Date payDate;


	public String getFinprodId() {
		return finprodId;
	}


	public void setFinprodId(String finprodId) {
		this.finprodId = finprodId;
	}


	public String getFinprodType() {
		return finprodType;
	}


	public void setFinprodType(String finprodType) {
		this.finprodType = finprodType;
	}


	public Date getVdate() {
		return vdate;
	}


	public void setVdate(Date vdate) {
		this.vdate = vdate;
	}


	public Date getMdate() {
		return mdate;
	}


	public void setMdate(Date mdate) {
		this.mdate = mdate;
	}


	public BigDecimal getPrinAmt() {
		return prinAmt;
	}


	public void setPrinAmt(BigDecimal prinAmt) {
		this.prinAmt = prinAmt;
	}


	public BigDecimal getIntAmt() {
		return intAmt;
	}


	public void setIntAmt(BigDecimal intAmt) {
		this.intAmt = intAmt;
	}


	public String getCashType1() {
		return cashType1;
	}


	public void setCashType1(String cashType1) {
		this.cashType1 = cashType1;
	}


	public String getCcy() {
		return ccy;
	}


	public void setCcy(String ccy) {
		this.ccy = ccy;
	}


	public BigDecimal getIssueAmt() {
		return issueAmt;
	}


	public void setIssueAmt(BigDecimal issueAmt) {
		this.issueAmt = issueAmt;
	}


	public String getChlFinprodId() {
		return chlFinprodId;
	}


	public void setChlFinprodId(String chlFinprodId) {
		this.chlFinprodId = chlFinprodId;
	}


	public Date getPayDate() {
		return payDate;
	}


	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}

	public String getFinprodType2() {
		return finprodType2;
	}

	public void setFinprodType2(String finprodType2) {
		this.finprodType2 = finprodType2;
	}
}
