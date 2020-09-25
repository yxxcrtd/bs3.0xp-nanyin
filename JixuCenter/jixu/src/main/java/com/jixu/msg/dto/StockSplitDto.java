package com.jixu.msg.dto;

import com.jixu.common.valid.IsNumberJixu;

/**
 * 股票分割Dto
 * @author 桃汰猪
 *
 */
public class StockSplitDto extends TranBaseMsgDto{
	
	private static final long serialVersionUID = -1654952588701364775L;
	/**
	 * 分割比例
	 */
	@IsNumberJixu(keymsg="分割比例")
	private String alRate;
	
	/**
	 * 股权登记日
	 */
	private String divDate;
	
	/**
	 * 证券账户
	 */
	private String secAcct;

	public String getAlRate() {
		return alRate;
	}

	public void setAlRate(String alRate) {
		this.alRate = alRate;
	}

	public String getDivDate() {
		return divDate;
	}

	public void setDivDate(String divDate) {
		this.divDate = divDate;
	}

	public String getSecAcct() {
		return secAcct;
	}

	public void setSecAcct(String secAcct) {
		this.secAcct = secAcct;
	}
}
