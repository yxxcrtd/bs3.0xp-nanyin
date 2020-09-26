package com.joyintech.tams.valuation.feetrade.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 费用交易明细dto
 * @version 1.0 20200120
 * @see FeeTradeDetailDto
 * @since 1.0
 */
public class FeeTradeDetailDto{
    /**
     * 账套代码
     */
    private String bookSetId;
    /**
     * 账套名称
     */
    private String bookSetName;
    /**
     * 子账套代码
     */
    private String subBookSetId;
    /**
     * 子账套名称
     */
    private String subBookSetName;
    /**
     * 登记托管机构
     */
    private String registOrg;
    /**
     * 计费截止日
     */
    private Date feeEndDate;
    /**
     * 理论费用
     */
    private BigDecimal shouldPayAmt;
    /**
     * 实付金额
     */
    private BigDecimal payAmt;
    /**
     * 是否结清
     */
    private String isCountOver;
    /**
     * 是否待摊
     */
    private String isNeedTan;
    /**
     * 备注
     */
    private String remark;
	public String getBookSetId() {
		return bookSetId;
	}
	public void setBookSetId(String bookSetId) {
		this.bookSetId = bookSetId;
	}
	public String getBookSetName() {
		return bookSetName;
	}
	public void setBookSetName(String bookSetName) {
		this.bookSetName = bookSetName;
	}
	public String getSubBookSetId() {
		return subBookSetId;
	}
	public void setSubBookSetId(String subBookSetId) {
		this.subBookSetId = subBookSetId;
	}
	public String getSubBookSetName() {
		return subBookSetName;
	}
	public void setSubBookSetName(String subBookSetName) {
		this.subBookSetName = subBookSetName;
	}
	public String getRegistOrg() {
		return registOrg;
	}
	public void setRegistOrg(String registOrg) {
		this.registOrg = registOrg;
	}
	public Date getFeeEndDate() {
		return feeEndDate;
	}
	public void setFeeEndDate(Date feeEndDate) {
		this.feeEndDate = feeEndDate;
	}
	public BigDecimal getShouldPayAmt() {
		return shouldPayAmt;
	}
	public void setShouldPayAmt(BigDecimal shouldPayAmt) {
		this.shouldPayAmt = shouldPayAmt;
	}
	public BigDecimal getPayAmt() {
		return payAmt;
	}
	public void setPayAmt(BigDecimal payAmt) {
		this.payAmt = payAmt;
	}
	public String getIsCountOver() {
		return isCountOver;
	}
	public void setIsCountOver(String isCountOver) {
		this.isCountOver = isCountOver;
	}
	public String getIsNeedTan() {
		return isNeedTan;
	}
	public void setIsNeedTan(String isNeedTan) {
		this.isNeedTan = isNeedTan;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
