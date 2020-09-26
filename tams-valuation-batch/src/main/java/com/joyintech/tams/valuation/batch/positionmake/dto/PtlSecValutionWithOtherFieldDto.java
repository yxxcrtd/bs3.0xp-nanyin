package com.joyintech.tams.valuation.batch.positionmake.dto;

import com.joyintech.tams.common.base.entity.PtlSecValution;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 包含其他字段的【组合资产估值】实体类
 * 
 * @author Administrator
 *
 */
public class PtlSecValutionWithOtherFieldDto extends PtlSecValution {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 账套代码
	 */
	private String booksetId;

	/**
	 * 账务明细代码
	 */
	private String bokDetailId;

	/**
	 * 业务明细代码
	 */
	private String busiId;

	/**
	 * 金融产品类型
	 */
	private String finprodType;

	/**
	 * 收益类型
	 */
	private String profitType;


	/**
	 * 名义本金
	 */
	private BigDecimal prin;

	/**
	 * 到期金额
	 */
	private BigDecimal mTradeAmt;

	/**
	 * 交易市场
	 */
	private String tradeMarket;

	/**
	 * 到期日
	 */
	private Date mdate;

	/**
	 * 到期交付日
	 */
	private Date mpayDate;

	/**
	 * 交易所回购天数方式
	 */
	private String seRepDayType;

	/**
	 * 入账日
	 */
	private Date bookDate;

	/**
	 * 发生日
	 */
	private Date happenDate;



	public String getBooksetId() {
		return booksetId;
	}

	public void setBooksetId(String booksetId) {
		this.booksetId = booksetId;
	}

	public String getBokDetailId() {
		return bokDetailId;
	}

	public void setBokDetailId(String bokDetailId) {
		this.bokDetailId = bokDetailId;
	}

	public String getBusiId() {
		return busiId;
	}

	public void setBusiId(String busiId) {
		this.busiId = busiId;
	}

	public String getFinprodType() {
		return finprodType;
	}

	public void setFinprodType(String finprodType) {
		this.finprodType = finprodType;
	}

	public String getProfitType() {
		return profitType;
	}

	public void setProfitType(String profitType) {
		this.profitType = profitType;
	}

	public BigDecimal getPrin() {
		return prin;
	}

	public void setPrin(BigDecimal prin) {
		this.prin = prin;
	}

	public BigDecimal getmTradeAmt() {
		return mTradeAmt;
	}

	public void setmTradeAmt(BigDecimal mTradeAmt) {
		this.mTradeAmt = mTradeAmt;
	}

	public String getTradeMarket() {
		return tradeMarket;
	}

	public void setTradeMarket(String tradeMarket) {
		this.tradeMarket = tradeMarket;
	}

	public Date getMdate() {
		return mdate;
	}

	public void setMdate(Date mdate) {
		this.mdate = mdate;
	}

	public Date getMpayDate() {
		return mpayDate;
	}

	public void setMpayDate(Date mpayDate) {
		this.mpayDate = mpayDate;
	}

	public String getSeRepDayType() {
		return seRepDayType;
	}

	public void setSeRepDayType(String seRepDayType) {
		this.seRepDayType = seRepDayType;
	}

	public Date getBookDate() {
		return bookDate;
	}

	public void setBookDate(Date bookDate) {
		this.bookDate = bookDate;
	}

	public Date getHappenDate() {
		return happenDate;
	}

	public void setHappenDate(Date happenDate) {
		this.happenDate = happenDate;
	}
}
