package com.joyintech.service.valuation.transformation.dto;

import java.math.BigDecimal;
import java.util.Date;

public class ProdBalanceDto {
	/**
	 * 余额日期
	 */
	private Date cdate;
	
	/**
	 * 账套代码
	 */
	private String booksetId;
	
	/**
	 * 产品代码
	 */
	private String prodId;
	
	/**
	 * 是否周期型子产品
	 */
	private String isZq;
	
	/**
	 * 原币
	 */
	private String oCcy;
	
	/**
	 * 当前份额/当前本金
	 */
	private BigDecimal share;
	
	/**
	 * 当前净资产
	 */
	private BigDecimal principal;
	
	/**
	 * 起息日
	 */
	private Date vdate;
	
	/**
	 * 到期日
	 */
	private Date mdate;
	
	/**
	 * 客户收益
	 */
	private BigDecimal intAmt;
	
	
	/**
	 * 应付固定管理费
	 */
	private BigDecimal feeAmtGD;
	
	
	/**
	 * 应付托管费
	 */
	private BigDecimal feeAmtTG;
	
	
	/**
	 * 应付销售费
	 */
	private BigDecimal feeAmtXS;
	
	/**
	 * 应付其他费用
	 */
	private BigDecimal feeAmtQT;
	
	
	/**
	 * 超额业绩收益
	 */
	private BigDecimal profitAmt;
	
	
	/**
	 * 其他资本公积
	 */
	private BigDecimal capitalQT;
	
	/**
	 * 未分配利润
	 */
	private BigDecimal unIntAmt;


	/**
	 * @return the prodId
	 */
	public String getProdId() {
		return prodId;
	}


	/**
	 * @param prodId the prodId to set
	 */
	public void setProdId(String prodId) {
		this.prodId = prodId;
	}


	/**
	 * @return the share
	 */
	public BigDecimal getShare() {
		return share;
	}


	/**
	 * @param share the share to set
	 */
	public void setShare(BigDecimal share) {
		this.share = share;
	}


	/**
	 * @return the principal
	 */
	public BigDecimal getPrincipal() {
		return principal;
	}


	/**
	 * @param principal the principal to set
	 */
	public void setPrincipal(BigDecimal principal) {
		this.principal = principal;
	}


	/**
	 * @return the vdate
	 */
	public Date getVdate() {
		return vdate;
	}


	/**
	 * @param vdate the vdate to set
	 */
	public void setVdate(Date vdate) {
		this.vdate = vdate;
	}


	/**
	 * @return the mdate
	 */
	public Date getMdate() {
		return mdate;
	}


	/**
	 * @param mdate the mdate to set
	 */
	public void setMdate(Date mdate) {
		this.mdate = mdate;
	}


	/**
	 * @return the intAmt
	 */
	public BigDecimal getIntAmt() {
		return intAmt;
	}


	/**
	 * @param intAmt the intAmt to set
	 */
	public void setIntAmt(BigDecimal intAmt) {
		this.intAmt = intAmt;
	}


	/**
	 * @return the feeAmtGD
	 */
	public BigDecimal getFeeAmtGD() {
		return feeAmtGD;
	}


	/**
	 * @param feeAmtGD the feeAmtGD to set
	 */
	public void setFeeAmtGD(BigDecimal feeAmtGD) {
		this.feeAmtGD = feeAmtGD;
	}


	/**
	 * @return the feeAmtTG
	 */
	public BigDecimal getFeeAmtTG() {
		return feeAmtTG;
	}


	/**
	 * @param feeAmtTG the feeAmtTG to set
	 */
	public void setFeeAmtTG(BigDecimal feeAmtTG) {
		this.feeAmtTG = feeAmtTG;
	}


	/**
	 * @return the feeAmtXS
	 */
	public BigDecimal getFeeAmtXS() {
		return feeAmtXS;
	}


	/**
	 * @param feeAmtXS the feeAmtXS to set
	 */
	public void setFeeAmtXS(BigDecimal feeAmtXS) {
		this.feeAmtXS = feeAmtXS;
	}


	/**
	 * @return the feeAmtQT
	 */
	public BigDecimal getFeeAmtQT() {
		return feeAmtQT;
	}


	/**
	 * @param feeAmtQT the feeAmtQT to set
	 */
	public void setFeeAmtQT(BigDecimal feeAmtQT) {
		this.feeAmtQT = feeAmtQT;
	}


	/**
	 * @return the profitAmt
	 */
	public BigDecimal getProfitAmt() {
		return profitAmt;
	}


	/**
	 * @param profitAmt the profitAmt to set
	 */
	public void setProfitAmt(BigDecimal profitAmt) {
		this.profitAmt = profitAmt;
	}

	/**
	 * @return the capitalQT
	 */
	public BigDecimal getCapitalQT() {
		return capitalQT;
	}


	/**
	 * @param capitalQT the capitalQT to set
	 */
	public void setCapitalQT(BigDecimal capitalQT) {
		this.capitalQT = capitalQT;
	}


	/**
	 * @return the cdate
	 */
	public Date getCdate() {
		return cdate;
	}


	/**
	 * @param cdate the cdate to set
	 */
	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}


	/**
	 * @return the booksetId
	 */
	public String getBooksetId() {
		return booksetId;
	}


	/**
	 * @param booksetId the booksetId to set
	 */
	public void setBooksetId(String booksetId) {
		this.booksetId = booksetId;
	}


	/**
	 * @return the isZq
	 */
	public String getIsZq() {
		return isZq;
	}


	/**
	 * @param isZq the isZq to set
	 */
	public void setIsZq(String isZq) {
		this.isZq = isZq;
	}


	/**
	 * @return the oCcy
	 */
	public String getoCcy() {
		return oCcy;
	}


	/**
	 * @param oCcy the oCcy to set
	 */
	public void setoCcy(String oCcy) {
		this.oCcy = oCcy;
	}
	
	/**
	 * 模板账套ID
	 */
	private String booksetIdTmpl;


	/**
	 * @return the booksetIdTmpl
	 */
	public String getBooksetIdTmpl() {
		return booksetIdTmpl;
	}


	/**
	 * @param booksetIdTmpl the booksetIdTmpl to set
	 */
	public void setBooksetIdTmpl(String booksetIdTmpl) {
		this.booksetIdTmpl = booksetIdTmpl;
	}


	/**
	 * @return the unIntAmt
	 */
	public BigDecimal getUnIntAmt() {
		return unIntAmt;
	}


	/**
	 * @param unIntAmt the unIntAmt to set
	 */
	public void setUnIntAmt(BigDecimal unIntAmt) {
		this.unIntAmt = unIntAmt;
	}
	
	
}
