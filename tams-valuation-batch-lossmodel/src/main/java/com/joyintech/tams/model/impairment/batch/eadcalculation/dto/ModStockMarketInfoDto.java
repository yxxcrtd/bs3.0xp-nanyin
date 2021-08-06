package com.joyintech.tams.model.impairment.batch.eadcalculation.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 股票行情信息DTO
 * @author zhangfei
 *
 */
public class ModStockMarketInfoDto extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	/**
	 * 股票代码
	 */
	private String conFinprodId;//CON_FINPROD_ID
	/**
	 * 市场代码
	 */
	private String finprodMarketId;
	/**
	 * 交易日
	 */
	private Date dealDate;
	
	/**
	 * 收盘价
	 */
	@JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal closePrice;
	/**
	 * 市净率
	 */
	@JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal pb;
	/**
	 * 市盈率
	 */
	@JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal pe;
	public String getConFinprodId() {
		return conFinprodId;
	}
	public String getFinprodMarketId() {
		return finprodMarketId;
	}
	public Date getDealDate() {
		return dealDate;
	}
	public BigDecimal getClosePrice() {
		return closePrice;
	}
	public BigDecimal getPb() {
		return pb;
	}
	public BigDecimal getPe() {
		return pe;
	}
	public void setConFinprodId(String conFinprodId) {
		this.conFinprodId = conFinprodId;
	}
	public void setFinprodMarketId(String finprodMarketId) {
		this.finprodMarketId = finprodMarketId;
	}
	public void setDealDate(Date dealDate) {
		this.dealDate = dealDate;
	}
	public void setClosePrice(BigDecimal closePrice) {
		this.closePrice = closePrice;
	}
	public void setPb(BigDecimal pb) {
		this.pb = pb;
	}
	public void setPe(BigDecimal pe) {
		this.pe = pe;
	}
	
}
