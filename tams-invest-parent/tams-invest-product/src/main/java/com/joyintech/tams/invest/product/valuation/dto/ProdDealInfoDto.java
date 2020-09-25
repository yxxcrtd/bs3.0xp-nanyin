package com.joyintech.tams.invest.product.valuation.dto;

import java.math.BigDecimal;
import java.util.Date;

public class ProdDealInfoDto extends ProdInfoDto {

	/**
	 * 日期
	 */
	private Date cdate;
	/**
	 * 交易金额
	 */
	private BigDecimal prinamt;
	/**
	 * 交易份额
	 */
	private BigDecimal prinqty;
	/**
	 * 未付利润
	 */
	private BigDecimal unpaidint;
	/**
	 * 资产规模
	 */
	private BigDecimal assetamt;

	public Date getCdate() {
		return cdate;
	}

	public void setCdate(Date cdate) {
		this.cdate = cdate;
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

	public BigDecimal getUnpaidint() {
		return unpaidint;
	}

	public void setUnpaidint(BigDecimal unpaidint) {
		this.unpaidint = unpaidint;
	}

	public BigDecimal getAssetamt() {
		return assetamt;
	}

	public void setAssetamt(BigDecimal assetamt) {
		this.assetamt = assetamt;
	}

}
