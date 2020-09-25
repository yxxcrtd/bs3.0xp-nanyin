package com.joyintech.tams.invest.product.valuation.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.product.valuation.validator.FinProductValuation001DtoValidator;

/**
 * 试算结果
 */
@ValidLogic(targetClass = FinProductValuation001DtoValidator.class)
public class FinProductValuation001Dto extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8631515355670984724L;
	/**
	 * 主键
	 */
	private Long valueid;
	/**
	 * 产品代码
	 */
	private String prodcode;
	/**
	 * 产品简称
	 */
	private String prod4short;
	/**
	 * 收益类型
	 */
	private String profitType;
	/**
	 * 估值日
	 */
	private Date vldate;
	/**
	 * 产品净资产
	 */
	private BigDecimal prodNetAsset;
	/**
	 * 产品总份额
	 */
	private BigDecimal prodTotQty;
	/**
	 * 未分配利润
	 */
	private BigDecimal unpayProfit;
	/**
	 * 万份收益
	 */
	private BigDecimal yield;
	/**
	 * 累计万份收益
	 */
	private BigDecimal totYield;
	/**
	 * 单位净值
	 */
	private BigDecimal netValue;
	/**
	 * 累计净值
	 */
	private BigDecimal totNetValue;
	/**
	 * 当前周期收益率（%）
	 */
	private BigDecimal curYield;
	/**
	 * 累计收益率（%）
	 */
	private BigDecimal totCurYield;
	/**
	 * 7日年化收益率（%）
	 */
	private BigDecimal yield7;
	/**
	 * 估值开始日
	 */
	private Date vldateStart;
	/**
	 * 估值结束日
	 */
	private Date vldateEnd;
	/**
	 * 币种
	 */
	private String ccy;

	public Long getValueid() {
		return valueid;
	}

	public void setValueid(Long valueid) {
		this.valueid = valueid;
	}

	public String getProdcode() {
		return prodcode;
	}

	public void setProdcode(String prodcode) {
		this.prodcode = prodcode;
	}

	public String getProd4short() {
		return prod4short;
	}

	public void setProd4short(String prod4short) {
		this.prod4short = prod4short;
	}

	public String getProfitType() {
		return profitType;
	}

	public String getProfitTypeStr() {
		return SysDataUtil.getNameFromDataDict(DictCode.收益类型, profitType);
	}

	public void setProfitType(String profitType) {
		this.profitType = profitType;
	}

	public Date getVldate() {
		return vldate;
	}

	public void setVldate(Date vldate) {
		this.vldate = vldate;
	}

	public BigDecimal getProdNetAsset() {
		return prodNetAsset;
	}

	public void setProdNetAsset(BigDecimal prodNetAsset) {
		this.prodNetAsset = prodNetAsset;
	}

	public BigDecimal getProdTotQty() {
		return prodTotQty;
	}

	public void setProdTotQty(BigDecimal prodTotQty) {
		this.prodTotQty = prodTotQty;
	}

	public BigDecimal getUnpayProfit() {
		return unpayProfit;
	}

	public void setUnpayProfit(BigDecimal unpayProfit) {
		this.unpayProfit = unpayProfit;
	}

	public BigDecimal getYield() {
		return yield;
	}

	public void setYield(BigDecimal yield) {
		this.yield = yield;
	}

	public BigDecimal getTotYield() {
		return totYield;
	}

	public void setTotYield(BigDecimal totYield) {
		this.totYield = totYield;
	}

	public BigDecimal getNetValue() {
		return netValue;
	}

	public void setNetValue(BigDecimal netValue) {
		this.netValue = netValue;
	}

	public BigDecimal getTotNetValue() {
		return totNetValue;
	}

	public void setTotNetValue(BigDecimal totNetValue) {
		this.totNetValue = totNetValue;
	}

	public BigDecimal getCurYield() {
		return curYield;
	}

	public void setCurYield(BigDecimal curYield) {
		this.curYield = curYield;
	}

	public BigDecimal getTotCurYield() {
		return totCurYield;
	}

	public void setTotCurYield(BigDecimal totCurYield) {
		this.totCurYield = totCurYield;
	}

	public BigDecimal getYield7() {
		return yield7;
	}

	public void setYield7(BigDecimal yield7) {
		this.yield7 = yield7;
	}

	public Date getVldateStart() {
		return vldateStart;
	}

	public void setVldateStart(Date vldateStart) {
		this.vldateStart = vldateStart;
	}

	public Date getVldateEnd() {
		return vldateEnd;
	}

	public void setVldateEnd(Date vldateEnd) {
		this.vldateEnd = vldateEnd;
	}

	public String getCcy() {
		return ccy;
	}

	public void setCcy(String ccy) {
		this.ccy = ccy;
	}

	public String getCcyStr() {
		return SysDataUtil.getNameFromDataDict(DictCode.币种, ccy);
	}

	public String getVldateStr() {
		return DateUtil.format(vldate);
	}

}
