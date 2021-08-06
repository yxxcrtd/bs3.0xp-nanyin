package com.joyintech.tams.model.impairment.batch.assetimpairmentstagejudge.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 现金流计划DTO
 * @author zhangfei
 */
public class ModCashFlowDto extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	/**
     * 金融产品代码
     */
    private String finprodId;
    /**
     * 金融产品类型（投管分类）
     */
    private String finprodType2;
    /**
     * 现金流代码
     */
    private String cashId;
    /**
     * 现金流二级类型
     */
    private String cashType2;
    /**
     * 理论计息开始日
     */
    private Date vdateUnadjust;
    /**
     * 理论计息结束日
     */
    private Date mdateUnadjust;
    
    /**
     * 起息日
     */
    private Date vdate;
    /**
     * 到期日
     */
    private Date mdate;
	/**
	 * 调整后的计划支付日
	 */
	private Date payDate;
    /**
     * 计划支付本金
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal prinAmt;
    /**
     * 计划支付利息
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal intAmt;
    /**
     * 计划支付现金流
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal cashAmt;
    /**
     * 计息本金
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal intPrinAmt;
	public String getFinprodId() {
		return finprodId;
	}
	public String getFinprodType2() {
		return finprodType2;
	}
	public String getCashId() {
		return cashId;
	}
	public String getCashType2() {
		return cashType2;
	}
	public Date getVdateUnadjust() {
		return vdateUnadjust;
	}
	public Date getMdateUnadjust() {
		return mdateUnadjust;
	}
	public Date getVdate() {
		return vdate;
	}
	public Date getMdate() {
		return mdate;
	}
	public BigDecimal getPrinAmt() {
		return prinAmt;
	}
	public BigDecimal getIntAmt() {
		return intAmt;
	}
	public BigDecimal getCashAmt() {
		return cashAmt;
	}
	public BigDecimal getIntPrinAmt() {
		return intPrinAmt;
	}
	public void setFinprodId(String finprodId) {
		this.finprodId = finprodId;
	}
	public void setFinprodType2(String finprodType2) {
		this.finprodType2 = finprodType2;
	}
	public void setCashId(String cashId) {
		this.cashId = cashId;
	}
	public void setCashType2(String cashType2) {
		this.cashType2 = cashType2;
	}
	public void setVdateUnadjust(Date vdateUnadjust) {
		this.vdateUnadjust = vdateUnadjust;
	}
	public void setMdateUnadjust(Date mdateUnadjust) {
		this.mdateUnadjust = mdateUnadjust;
	}
	public void setVdate(Date vdate) {
		this.vdate = vdate;
	}
	public void setMdate(Date mdate) {
		this.mdate = mdate;
	}
	public void setPrinAmt(BigDecimal prinAmt) {
		this.prinAmt = prinAmt;
	}
	public void setIntAmt(BigDecimal intAmt) {
		this.intAmt = intAmt;
	}
	public void setCashAmt(BigDecimal cashAmt) {
		this.cashAmt = cashAmt;
	}
	public void setIntPrinAmt(BigDecimal intPrinAmt) {
		this.intPrinAmt = intPrinAmt;
	}

	public Date getPayDate() {
		return payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}
}
