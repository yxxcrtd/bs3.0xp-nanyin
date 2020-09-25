package com.joyintech.tams.component.business.riskrds.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.constant.DictCodeList;

import java.math.BigDecimal;

/**
 * 交易基础实体(交易符合TrdProductDeal实体的那些类)
 * @author 裴家俊
 *
 */
public class TradeBaseDto extends TrdProductDeal {

	private static final long serialVersionUID = 1806018338501643163L;

	/**
	 * 汇总交易编号
	 */
	private String totleTradeId;
	/**
	 * 子组合代码
	 */
	private String actPortfolioId;
	/**
	 * 委托代码
	 */
	private String entrustId;

	/**
	 * 组合代码
	 */
	private String portfolioId;

	/**
	 * 回购/拆借方向，正回购、逆回购、拆入、拆出
	 */
	private String busiPs;

	/**
	 * 名义本金(拆借专用)
	 */
	@JsonSerialize(using= BigDecimalSerializer.class)
	private BigDecimal prin;

	/**
	 * 首次结算金额(买断式专用)
	 */
	@JsonSerialize(using = BigDecimalSerializer.class)
	private BigDecimal fSettleAmt;

	@Override
	public String getTotleTradeId() {
		return totleTradeId;
	}

	@Override
	public void setTotleTradeId(String totleTradeId) {
		this.totleTradeId = totleTradeId;
	}

	public String getEntrustId() {
		return entrustId;
	}

	public void setEntrustId(String entrustId) {
		this.entrustId = entrustId;
	}

	public String getActPortfolioId() {
		return actPortfolioId;
	}

	public void setActPortfolioId(String actPortfolioId) {
		this.actPortfolioId = actPortfolioId;
	}

	public String getPortfolioId() {
		return portfolioId;
	}

	public void setPortfolioId(String portfolioId) {
		this.portfolioId = portfolioId;
	}

	public String getBusiPs() {
		return busiPs;
	}

	public void setBusiPs(String busiPs) {
		this.busiPs = busiPs;
	}

	public BigDecimal getPrin() {
		return prin;
	}

	public void setPrin(BigDecimal prin) {
		this.prin = prin;
	}

	public BigDecimal getfSettleAmt() {
		return fSettleAmt;
	}

	public void setfSettleAmt(BigDecimal fSettleAmt) {
		this.fSettleAmt = fSettleAmt;
	}

	//--------------存续期部分字段---------------------
	 /*
	 * 交易编号
	 */
	private String busiCode;

	/**
	 * 计息开始日
	 */
	private String vDate;

	/**
	 * 计息结束日
	 */
	private String mDate;

	public String getBusiCode() {
		return busiCode;
	}

	public void setBusiCode(String busiCode) {
		this.busiCode = busiCode;
	}

	public String getvDate() {
		return vDate;
	}

	public void setvDate(String vDate) {
		this.vDate = vDate;
	}

	public String getmDate() {
		return mDate;
	}

	public void setmDate(String mDate) {
		this.mDate = mDate;
	}
}
