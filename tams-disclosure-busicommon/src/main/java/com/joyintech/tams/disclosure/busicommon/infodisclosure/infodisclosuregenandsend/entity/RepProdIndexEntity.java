package com.joyintech.tams.disclosure.busicommon.infodisclosure.infodisclosuregenandsend.entity;

import java.math.BigDecimal;
import java.util.Date;

public class RepProdIndexEntity {
	/**
	 * 估值日
	 */
	private Date valueDate;
	/**
	 * 七日年化
	 */
	private BigDecimal yield7;
	/**
	 * 万分收益
	 */
	private BigDecimal wYield;
	/**
	 * 净值
	 */
	private BigDecimal unitValue;
	
	private BigDecimal unitValueT;
	/**
	 * 产品名称
	 */
	private String prodName;
	/**
	 * 募集结束日
	 */
	private Date raiseEnddate;
	/**
	 * 
	 */
	private Date setDate;
	/**
	 * 起息日
	 */
	private Date vdate;
	/**
	 * 募集币种
	 */
	private String raiseCcy;
	/**
	 * 募集上线
	 */
	private BigDecimal raiseMin;
	/**
	 * 募集下线
	 */
	private BigDecimal raiseMax;
	/**
	 * 募集金额
	 */
	private BigDecimal raiseAmt;

	/**
	 * 资产名称
	 */
	private String dictValue;
	/**
	 * 资产金额
	 */
	private BigDecimal amt;
	/**
	 * 资产所占比例
	 */
	private BigDecimal precent;
	/**
	 * 产品代码
	 */
	private String prodId;
	/**
	 * 年化收益率
	 */
	private BigDecimal yieldE;
	/**
	 * 销售手续费率
	 */
	private BigDecimal sFeeRate;
	/**
	 * 托管费率
	 */
	private BigDecimal tFeeRate;
	/**
	 * 固定管理费率
	 */
	private BigDecimal mFeeRate;
	/**
	 * 其他费率
	 */
	private BigDecimal otherFeeRate;
	
	
	public Date getValueDate() {
		return valueDate;
	}

	public void setValueDate(Date valueDate) {
		this.valueDate = valueDate;
	}

	public BigDecimal getYield7() {
		return yield7;
	}

	public void setYield7(BigDecimal yield7) {
		this.yield7 = yield7;
	}

	public BigDecimal getwYield() {
		return wYield;
	}

	public void setwYield(BigDecimal wYield) {
		this.wYield = wYield;
	}

	public BigDecimal getUnitValue() {
		return unitValue;
	}

	public void setUnitValue(BigDecimal unitValue) {
		this.unitValue = unitValue;
	}

	public BigDecimal getUnitValueT() {
		return unitValueT;
	}

	public void setUnitValueT(BigDecimal unitValueT) {
		this.unitValueT = unitValueT;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public Date getRaiseEnddate() {
		return raiseEnddate;
	}

	public void setRaiseEnddate(Date raiseEnddate) {
		this.raiseEnddate = raiseEnddate;
	}

	public Date getSetDate() {
		return setDate;
	}

	public void setSetDate(Date setDate) {
		this.setDate = setDate;
	}

	public Date getVdate() {
		return vdate;
	}

	public void setVdate(Date vdate) {
		this.vdate = vdate;
	}

	public String getRaiseCcy() {
		return raiseCcy;
	}

	public void setRaiseCcy(String raiseCcy) {
		this.raiseCcy = raiseCcy;
	}

	public BigDecimal getRaiseMin() {
		return raiseMin;
	}

	public void setRaiseMin(BigDecimal raiseMin) {
		this.raiseMin = raiseMin;
	}

	public BigDecimal getRaiseMax() {
		return raiseMax;
	}

	public void setRaiseMax(BigDecimal raiseMax) {
		this.raiseMax = raiseMax;
	}

	public BigDecimal getRaiseAmt() {
		return raiseAmt;
	}

	public void setRaiseAmt(BigDecimal raiseAmt) {
		this.raiseAmt = raiseAmt;
	}

	public String getDictValue() {
		return dictValue;
	}

	public void setDictValue(String dictValue) {
		this.dictValue = dictValue;
	}

	public BigDecimal getAmt() {
		return amt;
	}

	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}

	public BigDecimal getPrecent() {
		return precent;
	}

	public void setPrecent(BigDecimal precent) {
		this.precent = precent;
	}

	public String getProdId() {
		return prodId;
	}

	public void setProdId(String prodId) {
		this.prodId = prodId;
	}

	public BigDecimal getYieldE() {
		return yieldE;
	}

	public void setYieldE(BigDecimal yieldE) {
		this.yieldE = yieldE;
	}

	public BigDecimal getsFeeRate() {
		return sFeeRate;
	}

	public void setsFeeRate(BigDecimal sFeeRate) {
		this.sFeeRate = sFeeRate;
	}

	public BigDecimal gettFeeRate() {
		return tFeeRate;
	}

	public void settFeeRate(BigDecimal tFeeRate) {
		this.tFeeRate = tFeeRate;
	}

	public BigDecimal getmFeeRate() {
		return mFeeRate;
	}

	public void setmFeeRate(BigDecimal mFeeRate) {
		this.mFeeRate = mFeeRate;
	}

	public BigDecimal getOtherFeeRate() {
		return otherFeeRate;
	}

	public void setOtherFeeRate(BigDecimal otherFeeRate) {
		this.otherFeeRate = otherFeeRate;
	}
	
}
