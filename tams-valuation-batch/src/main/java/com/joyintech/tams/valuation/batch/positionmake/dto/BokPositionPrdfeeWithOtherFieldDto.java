package com.joyintech.tams.valuation.batch.positionmake.dto;

import com.joyintech.tams.common.base.entity.BokPositionPrdfee;

import java.math.BigDecimal;

/**
 * 包含其他字段的【产品费用头寸】实体类
 * 
 * @author Administrator
 *
 */
public class BokPositionPrdfeeWithOtherFieldDto extends BokPositionPrdfee {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 账套代码
	 */
	private String booksetId;

	/**
	 * 级别序号
	 */
	private String lev;
	
	/**
	 * 账务明细代码
	 * ADD BY WUJIE ON 20190629
	 * @return
	 */
	private String bokDetailId;

	/**
	 *当日新增费用_本位币
	 */
	private BigDecimal tdyIntincexpAddB;

	/**
	 * 本位币
	 */
	private String bBccy;

	/**
	 * 原币
	 */
	private String ccy;

	public String getBooksetId() {
		return booksetId;
	}

	public void setBooksetId(String booksetId) {
		this.booksetId = booksetId;
	}

	public String getLev() {
		return lev;
	}

	public void setLev(String lev) {
		this.lev = lev;
	}

	public String getBokDetailId() {
		return bokDetailId;
	}

	public void setBokDetailId(String bokDetailId) {
		this.bokDetailId = bokDetailId;
	}

	public BigDecimal getTdyIntincexpAddB() {
		return tdyIntincexpAddB;
	}

	public void setTdyIntincexpAddB(BigDecimal tdyIntincexpAddB) {
		this.tdyIntincexpAddB = tdyIntincexpAddB;
	}

	public String getbBccy() {
		return bBccy;
	}

	public void setbBccy(String bBccy) {
		this.bBccy = bBccy;
	}

	public String getCcy() {
		return ccy;
	}

	public void setCcy(String ccy) {
		this.ccy = ccy;
	}
}
