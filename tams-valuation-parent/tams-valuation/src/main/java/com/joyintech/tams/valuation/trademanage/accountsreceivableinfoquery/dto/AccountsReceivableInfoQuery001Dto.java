/*
 * 文件名：AccountsReceivableInfoQuery001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangzhao
 * 修改时间：2018年10月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.trademanage.accountsreceivableinfoquery.dto;

import java.math.BigDecimal;

import com.joyintech.framework.base.BaseEntity;

/**
 * 应收款项信息查询
 * 
 * @author zhangzhao
 * @version 1.0 2018年10月9日
 * @see AccountsReceivableInfoQuery001Dto
 * @since 1.0
 */
@SuppressWarnings("serial")
public class AccountsReceivableInfoQuery001Dto extends BaseEntity {

	/** 账套代码 */
	private String bookSetId;
	/** 资产代码 */
	private String finprodId;
	/** 资产名称*/
	private String finprodName;
	/** 标的资产代码 */
	private String bdFinprodId;
	/** 标的资产名称 */
	private String bdFinprodName;
	/** 应收款项类别 */
	private String receivablesType;
	/** 本金金额 */
	private BigDecimal prinAmt;
	/** 利息金额 */
	private BigDecimal intAmt;
	/** 计息起始日*/
	private String vdate;
	/** 计息截止日*/
	private String mdate;
	/** 还本日 */
	private String tradeDate;
	/** 计息基础 */
	private String basis;
	/** 利率类型 */
	private String calcType;
	/** 最新利率（%）*/
	private BigDecimal yield;
	
	
	
	public String getBookSetId() {
		return bookSetId;
	}
	public void setBookSetId(String bookSetId) {
		this.bookSetId = bookSetId;
	}
	public String getFinprodId() {
		return finprodId;
	}
	public void setFinprodId(String finprodId) {
		this.finprodId = finprodId;
	}
	public String getFinprodName() {
		return finprodName;
	}
	public void setFinprodName(String finprodName) {
		this.finprodName = finprodName;
	}
	public String getBdFinprodId() {
		return bdFinprodId;
	}
	public void setBdFinprodId(String bdFinprodId) {
		this.bdFinprodId = bdFinprodId;
	}
	public String getBdFinprodName() {
		return bdFinprodName;
	}
	public void setBdFinprodName(String bdFinprodName) {
		this.bdFinprodName = bdFinprodName;
	}
	public String getReceivablesType() {
		return receivablesType;
	}
	public void setReceivablesType(String receivablesType) {
		this.receivablesType = receivablesType;
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
	public String getVdate() {
		return vdate;
	}
	public void setVdate(String vdate) {
		this.vdate = vdate;
	}
	public String getMdate() {
		return mdate;
	}
	public void setMdate(String mdate) {
		this.mdate = mdate;
	}
	public String getTradeDate() {
		return tradeDate;
	}
	public void setTradeDate(String tradeDate) {
		this.tradeDate = tradeDate;
	}
	public String getBasis() {
		return basis;
	}
	public void setBasis(String basis) {
		this.basis = basis;
	}
	public String getCalcType() {
		return calcType;
	}
	public void setCalcType(String calcType) {
		this.calcType = calcType;
	}
	public BigDecimal getYield() {
		return yield;
	}
	public void setYield(BigDecimal yield) {
		this.yield = yield;
	}

}
