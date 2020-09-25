package com.joyintech.tams.invest.product.valuation.dto;

import java.math.BigDecimal;
import java.util.Date;

public class ProdFeeInfoDto {
	/**
	 * 现金流代码
	 */
	private String cashid;
	/**
	 * 产品代码
	 */
	private String prodcode;
	/**
	 * 费用品种代码
	 */
	private String feeid;
	/**
	 * 关联范围，产品、通道、其他
	 */
	private String applytype;
	/**
	 * 费用名称
	 */
	private String feename;
	/**
	 * 费用类型
	 */
	private String feetype;
	/**
	 * 计费基数，存续本金、资产规模
	 */
	private String basicbill;
	/**
	 * 收费类型，计提费用，不规则费用
	 */
	private String chargetype;
	/**
	 * 计费基础
	 */
	private String basis;
	/**
	 * 费率
	 */
	private BigDecimal yield;
	/**
	 * 计费开始日
	 */
	private Date vdate;
	/**
	 * 计费结束日
	 */
	private Date mdate;
	/**
	 * 支付日
	 */
	private Date paydate;

	public String getCashid() {
		return cashid;
	}

	public void setCashid(String cashid) {
		this.cashid = cashid;
	}

	public String getProdcode() {
		return prodcode;
	}

	public void setProdcode(String prodcode) {
		this.prodcode = prodcode;
	}

	public String getFeeid() {
		return feeid;
	}

	public void setFeeid(String feeid) {
		this.feeid = feeid;
	}

	public String getApplytype() {
		return applytype;
	}

	public void setApplytype(String applytype) {
		this.applytype = applytype;
	}

	public String getFeename() {
		return feename;
	}

	public void setFeename(String feename) {
		this.feename = feename;
	}

	public String getFeetype() {
		return feetype;
	}

	public void setFeetype(String feetype) {
		this.feetype = feetype;
	}

	public String getBasicbill() {
		return basicbill;
	}

	public void setBasicbill(String basicbill) {
		this.basicbill = basicbill;
	}

	public String getChargetype() {
		return chargetype;
	}

	public void setChargetype(String chargetype) {
		this.chargetype = chargetype;
	}

	public String getBasis() {
		return basis;
	}

	public void setBasis(String basis) {
		this.basis = basis;
	}

	public BigDecimal getYield() {
		return yield;
	}

	public void setYield(BigDecimal yield) {
		this.yield = yield;
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

	public Date getPaydate() {
		return paydate;
	}

	public void setPaydate(Date paydate) {
		this.paydate = paydate;
	}

}
