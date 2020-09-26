package com.joyintech.tams.valuation.common.interf.howmany.dto;

import com.joyintech.tams.common.base.entity.TrdProductDeal;

public class TrdProductDealDto extends TrdProductDeal{
	private static final long serialVersionUID = 1L;

	/**
	 * 业务表
	 */
	private String busiTable;
	
	/**
	 * 四级科目尾号
	 */
	private String detailSubjectNoTail;
	
	/**
	 * 四级科目尾号名称
	 */
	private String detailSubnameTail;
	
	/**
	 * 是否转仓交易
	 */
	private String isSwapTransaction;

	public String getIsSwapTransaction() {
		return isSwapTransaction;
	}

	public void setIsSwapTransaction(String isSwapTransaction) {
		this.isSwapTransaction = isSwapTransaction;
	}

	public String getBusiTable() {
		return busiTable;
	}

	public void setBusiTable(String busiTable) {
		this.busiTable = busiTable;
	}

	public String getDetailSubjectNoTail() {
		return detailSubjectNoTail;
	}

	public void setDetailSubjectNoTail(String detailSubjectNoTail) {
		this.detailSubjectNoTail = detailSubjectNoTail;
	}

	public String getDetailSubnameTail() {
		return detailSubnameTail;
	}

	public void setDetailSubnameTail(String detailSubnameTail) {
		this.detailSubnameTail = detailSubnameTail;
	}
	
}
