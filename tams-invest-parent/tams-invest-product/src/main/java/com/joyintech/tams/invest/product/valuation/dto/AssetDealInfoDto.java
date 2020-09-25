package com.joyintech.tams.invest.product.valuation.dto;

import java.math.BigDecimal;
import java.util.Date;

public class AssetDealInfoDto {
	/**
	 * 本方账户
	 */
	private String accountid;
	/**
	 * 组合代码
	 */
	private String ptlid;
	/**
	 * 金融产品代码
	 */
	private String finprodid;
	/**
	 * 资产代码
	 */
	private String assetcode;
	/**
	 * 资产名称
	 */
	private String assetname;
	/**
	 * 资产类型
	 */
	private String assettype;
	/**
	 * 资产一级分类
	 */
	private String firsttype;
	/**
	 * 投资目的
	 */
	private String target;
	/**
	 * 收益类型
	 */
	private String profittype;
	/**
	 * 通道
	 */
	private String channel;
	/**
	 * 交易方向
	 */
	private String ps;
	/**
	 * 计息基础
	 */
	private String basis;
	/**
	 * 收益率
	 */
	private BigDecimal yield;
	/**
	 * 起息日
	 */
	private Date vdate;
	/**
	 * 到期日
	 */
	private Date mdate;
	/**
	 * 币种
	 */
	private String ccy;
	/**
	 * 交易金额
	 */
	private BigDecimal prinamt;
	/**
	 * 交易份额
	 */
	private BigDecimal prinqty;
	/**
	 * 利息
	 */
	private BigDecimal intamt;
	/**
	 * 证券托管户
	 */
	private String bondacct;

	public String getAccountid() {
		return accountid;
	}

	public void setAccountid(String accountid) {
		this.accountid = accountid;
	}

	public String getPtlid() {
		return ptlid;
	}

	public void setPtlid(String ptlid) {
		this.ptlid = ptlid;
	}

	public String getFinprodid() {
		return finprodid;
	}

	public void setFinprodid(String finprodid) {
		this.finprodid = finprodid;
	}

	public String getAssetcode() {
		return assetcode;
	}

	public void setAssetcode(String assetcode) {
		this.assetcode = assetcode;
	}

	public String getAssetname() {
		return assetname;
	}

	public void setAssetname(String assetname) {
		this.assetname = assetname;
	}

	public String getAssettype() {
		return assettype;
	}

	public void setAssettype(String assettype) {
		this.assettype = assettype;
	}

	public String getFirsttype() {
		return firsttype;
	}

	public void setFirsttype(String firsttype) {
		this.firsttype = firsttype;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getProfittype() {
		return profittype;
	}

	public void setProfittype(String profittype) {
		this.profittype = profittype;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getPs() {
		return ps;
	}

	public void setPs(String ps) {
		this.ps = ps;
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

	public String getCcy() {
		return ccy;
	}

	public void setCcy(String ccy) {
		this.ccy = ccy;
	}

	public BigDecimal getPrinamt() {
		return prinamt;
	}

	public void setPrinamt(BigDecimal prinamt) {
		this.prinamt = prinamt;
	}

	public BigDecimal getPrinqty() {
		return prinqty;
	}

	public void setPrinqty(BigDecimal prinqty) {
		this.prinqty = prinqty;
	}

	public BigDecimal getIntamt() {
		return intamt;
	}

	public void setIntamt(BigDecimal intamt) {
		this.intamt = intamt;
	}

	public String getBondacct() {
		return bondacct;
	}

	public void setBondacct(String bondacct) {
		this.bondacct = bondacct;
	}

}
