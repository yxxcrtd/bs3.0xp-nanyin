package com.joyintech.tams.invest.trade.transfer.assign.dto;

import com.joyintech.tams.constant.DictCodeList;

import java.math.BigDecimal;
import java.util.Date;

public class TrdAssetAssignDto {

	/**
	 * 组合代码
	 */
	private String portfolioId;
	/**
	 * 资产代码
	 */
	private String finprodId;
	/**
	 * 分支序号
	 */
	private Long liBranch;
	/**
	 * 交易日期
	 */
	private Date tradeDate;
	/**
	 * 交割日期
	 */
	private Date settleDate;
	/**
	 * 剩余本金
	 */
	private BigDecimal remainPrinamt;
	/**
	 * 理论付息金额
	 */
	private BigDecimal theoryamt;
	/**
	 * 付息金额
	 */
	private BigDecimal intamt;
	/**
	 * 未付利息金额
	 */
	private BigDecimal unPaidIntamt;
	/**
	 * 是否结清
	 */
	private String isClean;
	/**
	 * 待转让本金
	 */
	private BigDecimal assignPrinamt;
	/**
	 * 待转让利息
	 */
	private BigDecimal assignIntamt;
	/**
	 * 需要生产转让交易
	 */
	private String isNeedAssign = DictCodeList.是否_否.getValue();

	public String getPortfolioId() {
		return portfolioId;
	}

	public void setPortfolioId(String portfolioId) {
		this.portfolioId = portfolioId;
	}

	public String getFinprodId() {
		return finprodId;
	}

	public void setFinprodId(String finprodId) {
		this.finprodId = finprodId;
	}

	public Long getLiBranch() {
		return liBranch;
	}

	public void setLiBranch(Long liBranch) {
		this.liBranch = liBranch;
	}

	public Date getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(Date tradeDate) {
		this.tradeDate = tradeDate;
	}

	public Date getSettleDate() {
		return settleDate;
	}

	public void setSettleDate(Date settleDate) {
		this.settleDate = settleDate;
	}

	public BigDecimal getRemainPrinamt() {
		return remainPrinamt == null ? BigDecimal.ZERO : remainPrinamt;
	}

	public void setRemainPrinamt(BigDecimal remainPrinamt) {
		this.remainPrinamt = remainPrinamt;
	}

	public BigDecimal getTheoryamt() {
		return theoryamt == null ? BigDecimal.ZERO : theoryamt;
	}

	public void setTheoryamt(BigDecimal theoryamt) {
		this.theoryamt = theoryamt;
	}

	public BigDecimal getIntamt() {
		return intamt == null ? BigDecimal.ZERO : theoryamt;
	}

	public void setIntamt(BigDecimal intamt) {
		this.intamt = intamt;
	}

	public BigDecimal getUnPaidIntamt() {
		return unPaidIntamt == null ? BigDecimal.ZERO : unPaidIntamt;
	}

	public void setUnPaidIntamt(BigDecimal unPaidIntamt) {
		this.unPaidIntamt = unPaidIntamt;
	}

	public String getIsClean() {
		return isClean;
	}

	public void setIsClean(String isClean) {
		this.isClean = isClean;
	}

	public BigDecimal getAssignPrinamt() {
		return assignPrinamt == null ? BigDecimal.ZERO : assignPrinamt;
	}

	public void setAssignPrinamt(BigDecimal assignPrinamt) {
		this.assignPrinamt = assignPrinamt;
	}

	public BigDecimal getAssignIntamt() {
		return assignIntamt == null ? BigDecimal.ZERO : assignIntamt;
	}

	public void setAssignIntamt(BigDecimal assignIntamt) {
		this.assignIntamt = assignIntamt;
	}

	public String getIsNeedAssign() {
		return isNeedAssign;
	}

	public void setIsNeedAssign(String isNeedAssign) {
		this.isNeedAssign = isNeedAssign;
	}

}
