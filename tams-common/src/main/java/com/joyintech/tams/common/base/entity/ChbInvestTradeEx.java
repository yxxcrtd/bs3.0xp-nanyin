package com.joyintech.tams.common.base.entity;

import java.math.BigDecimal;

/**  
* @Title: ChbInvestTradeEx.java
* @Package com.joyintech.tams.common.base.entity
* @Description: (主要用于计算外汇远期到期收益交易发生金额)
* @author gaojian
* @date 2019年7月23日
* @since V1.7  
* @Co.,Ltd. 安徽兆尹信息科技有限责任公司
*/
public class ChbInvestTradeEx extends ChbInvestTrade {

	/**
	 * 序列化ID
	 */
	private static final long serialVersionUID = -1878849706798601908L;
	
	/**
	 * 货币对
	 */
	private String curPair;
	
	/*
	 * 交易币种
	 */
	private String tradeCcy;
	
	/*
	 * 对应币种
	 */
	private String matchCcy;
	
	/*
	 * 外汇交易方向
	 */
	private String excPs;
	
	/*
	 * 交易方向
	 */
	private String ps;
	
	/*
	 * 全价总额 (对应货币金额)
	 */
	private BigDecimal fpriceAmt;
	
	/*
	 * 交易本金，债券券面等 (交易货币金额)
	 */
	private BigDecimal prinAmt;

	public String getCurPair() {
		return curPair;
	}

	public void setCurPair(String curPair) {
		this.curPair = curPair;
	}

	public String getTradeCcy() {
		return tradeCcy;
	}

	public void setTradeCcy(String tradeCcy) {
		this.tradeCcy = tradeCcy;
	}

	public String getMatchCcy() {
		return matchCcy;
	}

	public void setMatchCcy(String matchCcy) {
		this.matchCcy = matchCcy;
	}

	public String getExcPs() {
		return excPs;
	}

	public void setExcPs(String excPs) {
		this.excPs = excPs;
	}
	
	public String getPs() {
		return ps;
	}

	public void setPs(String ps) {
		this.ps = ps;
	}

	public BigDecimal getFpriceAmt() {
		return fpriceAmt;
	}

	public void setFpriceAmt(BigDecimal fpriceAmt) {
		this.fpriceAmt = fpriceAmt;
	}

	public BigDecimal getPrinAmt() {
		return prinAmt;
	}

	public void setPrinAmt(BigDecimal prinAmt) {
		this.prinAmt = prinAmt;
	}

	@Override
	public String toString() {
		return "ChbInvestTradeEx [curPair=" + curPair + ", tradeCcy=" + tradeCcy + ", excPs=" + excPs + ", fpriceAmt="
				+ fpriceAmt + ", prinAmt=" + prinAmt + "]";
	}
	
	

}
