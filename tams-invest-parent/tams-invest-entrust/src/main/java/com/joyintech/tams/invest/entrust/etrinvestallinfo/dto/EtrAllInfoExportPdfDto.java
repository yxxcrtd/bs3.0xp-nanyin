/*
 * 文件名：EtrInvestAllInfoDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xiexinting
 * 修改时间：2018年9月7日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.entrust.etrinvestallinfo.dto;

import java.math.BigDecimal;

/**
 * 指令查询导出pdf
 * @author zengchengfa
 * @version 1.0
 * @date 2021-08-03
 */
public class EtrAllInfoExportPdfDto {

	/**
	 * 资产代码
	 */
	private String finprodMarketId;
	/**
	 * 委托日期
	 */
	private String entrustDateStr;
	/**
	 * 债券简称
	 */
	private String finprodAbbr;
	/**
	 * 卖出方
	 */
	private String saler;
	/**
	 * 买入方
	 */
	private String buyer;
	/**
	 * 估值净价
	 */
	private BigDecimal valuePrice;
	/**
	 * 偏离度
	 */
	private String deviation;
	/**
	 * 经办审批意见
	 */
	private String jbyj;
	/**
	 * 经办审批日期
	 */
	private String jbrq;
	/**
	 * 复核审批意见
	 */
	private String fhyj;
	/**
	 * 复核审批日期
	 */
	private String fhrq;
	/**
	 * 风控审批意见
	 */
	private String fkyj;
	/**
	 * 风控审批日期
	 */
	private String fkrq;
	/**
	 * 分管负责人审批意见
	 */
	private String fgyj;
	/**
	 * 分管负责人审批日期
	 */
	private String fgrq;
	/**
	 * 资管部审批意见
	 */
	private String zgyj;
	/**
	 * 资管部审批日期
	 */
	private String zgrq;

	/**
	 * 交易对手
	 */
	private String counterName;
	/**
	 * 质押券
	 */
	private String repSecId;
	/**
	 * 折算比例
	 */
	private String conRatio;
	/**
	 * 回购期限
	 */
	private String term;
	/**
	 * 证券账户
	 */
	private String secAcctId;
	/**
	 * 清算速度
	 */
	private String settleSpreedStr;

	/**
	 * 委托数量
	 */
	private BigDecimal entrustNum;
	/**
	 * 交易净价
	 */
	private BigDecimal price;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 组合名称
	 */
	private String portfolioName;
	/**
	 * 资产类型
	 */
	private String assetType;
	/**
	 * 交易方向
	 */
	private String ps;
	/**
	 * 交易场所
	 */
	private String tradeMarket;
	/**
	 * 委托金额
	 */
	private BigDecimal entrustAmt;

	public String getEntrustDateStr() {
		return entrustDateStr;
	}

	public void setEntrustDateStr(String entrustDateStr) {
		this.entrustDateStr = entrustDateStr;
	}

	public String getFinprodAbbr() {
		return finprodAbbr;
	}

	public void setFinprodAbbr(String finprodAbbr) {
		this.finprodAbbr = finprodAbbr;
	}

	public String getSaler() {
		return saler;
	}

	public void setSaler(String saler) {
		this.saler = saler;
	}

	public String getBuyer() {
		return buyer;
	}

	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}

	public BigDecimal getValuePrice() {
		return valuePrice;
	}

	public void setValuePrice(BigDecimal valuePrice) {
		this.valuePrice = valuePrice;
	}

	public String getDeviation() {
		return deviation;
	}

	public void setDeviation(String deviation) {
		this.deviation = deviation;
	}

	public String getJbyj() {
		return jbyj;
	}

	public void setJbyj(String jbyj) {
		this.jbyj = jbyj;
	}

	public String getJbrq() {
		return jbrq;
	}

	public void setJbrq(String jbrq) {
		this.jbrq = jbrq;
	}

	public String getFhyj() {
		return fhyj;
	}

	public void setFhyj(String fhyj) {
		this.fhyj = fhyj;
	}

	public String getFhrq() {
		return fhrq;
	}

	public void setFhrq(String fhrq) {
		this.fhrq = fhrq;
	}

	public String getFkyj() {
		return fkyj;
	}

	public void setFkyj(String fkyj) {
		this.fkyj = fkyj;
	}

	public String getFkrq() {
		return fkrq;
	}

	public void setFkrq(String fkrq) {
		this.fkrq = fkrq;
	}

	public String getFgyj() {
		return fgyj;
	}

	public void setFgyj(String fgyj) {
		this.fgyj = fgyj;
	}

	public String getFgrq() {
		return fgrq;
	}

	public void setFgrq(String fgrq) {
		this.fgrq = fgrq;
	}

	public String getZgyj() {
		return zgyj;
	}

	public void setZgyj(String zgyj) {
		this.zgyj = zgyj;
	}

	public String getZgrq() {
		return zgrq;
	}

	public void setZgrq(String zgrq) {
		this.zgrq = zgrq;
	}

	public String getCounterName() {
		return counterName;
	}

	public void setCounterName(String counterName) {
		this.counterName = counterName;
	}

	public String getRepSecId() {
		return repSecId;
	}

	public void setRepSecId(String repSecId) {
		this.repSecId = repSecId;
	}

	public String getConRatio() {
		return conRatio;
	}

	public void setConRatio(String conRatio) {
		this.conRatio = conRatio;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public String getSecAcctId() {
		return secAcctId;
	}

	public void setSecAcctId(String secAcctId) {
		this.secAcctId = secAcctId;
	}

	public String getSettleSpreedStr() {
		return settleSpreedStr;
	}

	public void setSettleSpreedStr(String settleSpreedStr) {
		this.settleSpreedStr = settleSpreedStr;
	}

	public String getFinprodMarketId() {
		return finprodMarketId;
	}

	public void setFinprodMarketId(String finprodMarketId) {
		this.finprodMarketId = finprodMarketId;
	}

	public BigDecimal getEntrustNum() {
		return entrustNum;
	}

	public void setEntrustNum(BigDecimal entrustNum) {
		this.entrustNum = entrustNum;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getPortfolioName() {
		return portfolioName;
	}

	public void setPortfolioName(String portfolioName) {
		this.portfolioName = portfolioName;
	}

	public String getAssetType() {
		return assetType;
	}

	public void setAssetType(String assetType) {
		this.assetType = assetType;
	}

	public String getPs() {
		return ps;
	}

	public void setPs(String ps) {
		this.ps = ps;
	}

	public String getTradeMarket() {
		return tradeMarket;
	}

	public void setTradeMarket(String tradeMarket) {
		this.tradeMarket = tradeMarket;
	}

	public BigDecimal getEntrustAmt() {
		return entrustAmt;
	}

	public void setEntrustAmt(BigDecimal entrustAmt) {
		this.entrustAmt = entrustAmt;
	}
}
