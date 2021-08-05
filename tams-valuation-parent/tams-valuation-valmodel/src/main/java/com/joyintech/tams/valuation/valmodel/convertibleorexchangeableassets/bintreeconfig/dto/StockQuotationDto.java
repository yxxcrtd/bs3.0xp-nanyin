
package com.joyintech.tams.valuation.valmodel.convertibleorexchangeableassets.bintreeconfig.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 股票行情Dto 模型查询投管数据用
 * 
 * @author yuluan
 * @version 1.0 2021年6月30日
 * @see StockQuotationDto
 * @since 1.0
 */
public class StockQuotationDto {
	/**
	 * 股票代码
	 */
	private String stockCode;
	/**
	 * 交易日
	 */
	private Date dealDate;
	/**
	 * 市盈率
	 */
	private BigDecimal pe;
	/**
	 * 市净率
	 */
	private BigDecimal pb;
	/**
	 * 收盘价
	 */
	private BigDecimal closePrice;
	/**
	 * 股票代码集合 查询参数
	 */
	private List<String> stockCodeList;
	public String getStockCode() {
		return stockCode;
	}
	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}
	public Date getDealDate() {
		return dealDate;
	}
	public void setDealDate(Date dealDate) {
		this.dealDate = dealDate;
	}
	public BigDecimal getPe() {
		return pe;
	}
	public void setPe(BigDecimal pe) {
		this.pe = pe;
	}
	public BigDecimal getPb() {
		return pb;
	}
	public void setPb(BigDecimal pb) {
		this.pb = pb;
	}
	public BigDecimal getClosePrice() {
		return closePrice;
	}
	public void setClosePrice(BigDecimal closePrice) {
		this.closePrice = closePrice;
	}
	public List<String> getStockCodeList() {
		return stockCodeList;
	}
	public void setStockCodeList(List<String> stockCodeList) {
		this.stockCodeList = stockCodeList;
	}
	
}
