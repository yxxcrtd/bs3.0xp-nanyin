package com.joyintech.tams.invest.portfolio.ptlanalyzeafterinvest.dto;

import java.math.BigDecimal;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.export.paginator.domain.ITableRow;

/**
 * 〈一句话功能简述〉
 *
 * @author 王贵奇
 * @version 1.0 2019/10/23
 * @since 1.0
 */
@PageId("PtlAnalyzeAfterInvest002")
public class PtlAnalyzeAfterInvest002Dto extends BaseEntity implements ITableRow {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6804042279445705230L;

	private String dealDate;
	
	private BigDecimal openPrice;
	
	private BigDecimal closePrice;
	
	private BigDecimal lowPrice;
	
	private BigDecimal highPrice;

	public String getDealDate() {
		return dealDate;
	}

	public void setDealDate(String dealDate) {
		this.dealDate = dealDate;
	}

	public BigDecimal getOpenPrice() {
		return openPrice;
	}

	public void setOpenPrice(BigDecimal openPrice) {
		this.openPrice = openPrice;
	}

	public BigDecimal getClosePrice() {
		return closePrice;
	}

	public void setClosePrice(BigDecimal closePrice) {
		this.closePrice = closePrice;
	}

	public BigDecimal getLowPrice() {
		return lowPrice;
	}

	public void setLowPrice(BigDecimal lowPrice) {
		this.lowPrice = lowPrice;
	}

	public BigDecimal getHighPrice() {
		return highPrice;
	}

	public void setHighPrice(BigDecimal highPrice) {
		this.highPrice = highPrice;
	}
}
