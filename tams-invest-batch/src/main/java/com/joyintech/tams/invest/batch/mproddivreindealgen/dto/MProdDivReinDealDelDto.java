/*
 * 文件名：ResetBaseRuleInfoDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2019年2月16日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.batch.mproddivreindealgen.dto;


import com.joyintech.framework.base.BaseEntity;


/**
 *  业绩基准重置
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2019年2月16日
 * @see MProdDivReinDealDelDto
 * @since 1.0
 */
public class MProdDivReinDealDelDto extends BaseEntity{

    
    /**
	 * 
	 */
	private static final long serialVersionUID = -8176222541764676400L;
	/**
	 * 帐套代码
	 */
	private String booksetId;
	/**
	 * 业务编号
	 */
	private String busiNo;
	/**
	 * 业务表
	 */
	private String busiTable;
	
	/**
	 * 交易编号
	 */
	private String tradeId;
	
	/**
	 * 组合 业务表
	 */
	private String plBusiTable;
	
	/**
	 * 汇总交易编号
	 */
	private String totleTradeId;
	/**
	 * 组合代码
	 */
	private String portfolioId;
	public String getBooksetId() {
		return booksetId;
	}
	public void setBooksetId(String booksetId) {
		this.booksetId = booksetId;
	}
	public String getBusiNo() {
		return busiNo;
	}
	public void setBusiNo(String busiNo) {
		this.busiNo = busiNo;
	}
	public String getBusiTable() {
		return busiTable;
	}
	public void setBusiTable(String busiTable) {
		this.busiTable = busiTable;
	}
	public String getTradeId() {
		return tradeId;
	}
	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}
	public String getPlBusiTable() {
		return plBusiTable;
	}
	public void setPlBusiTable(String plBusiTable) {
		this.plBusiTable = plBusiTable;
	}
	public String getTotleTradeId() {
		return totleTradeId;
	}
	public void setTotleTradeId(String totleTradeId) {
		this.totleTradeId = totleTradeId;
	}
	public String getPortfolioId() {
		return portfolioId;
	}
	public void setPortfolioId(String portfolioId) {
		this.portfolioId = portfolioId;
	}
	
	

}
