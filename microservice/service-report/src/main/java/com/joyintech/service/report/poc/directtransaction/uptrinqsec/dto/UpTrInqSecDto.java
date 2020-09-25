/*
 * 文件名：TrdBondInvestTradeDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Zane.Y
 * 修改时间：2018年9月7日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.report.poc.directtransaction.uptrinqsec.dto;

import com.joyintech.framework.base.BaseEntity;

/**
 * 债券交易Dto
 *
 * @author Zane.Y
 * @version 1.0 2018年9月7日
 * @see
 * @since 1.0
 */
public class UpTrInqSecDto extends BaseEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -8259658424290761601L;

    /*
     * 交易方向
     */
    private String ps;
    /*
     * 交易对手
     */
    private String counterId;
    
    /*
     * 对手方交易员
     */
    private String counterTrader;
    /*
     * 资产代码
     */
    private String finprodId;
    /*
     * 清算速度
     */
    private String settleSpreed;
    /*
     * 净价
     */
    private String price;
    /*
     * 到期收益率
     */
    private String matureYield;
    /*
     *券面总额 
     */
    private String entrustNum;
    /*
     * 行权收益率
     */
    private String exerYield;
    /*
     * 结算币种
     */
    private String ccy;
    /*
     * 交易金额  
     */
    private String entrustAmt;
    /*
     * 委托代码
     */
    private String entrustId;
    /*
     * 组合代码
     */
    private String portfolioId;
    /*
     * 组合名称
     */
    private String portfolioName;
    
    
	public String getPortfolioName() {
		return portfolioName;
	}
	public String getPs() {
		return ps;
	}
	public void setPs(String ps) {
		this.ps = ps;
	}
	public String getCounterId() {
		return counterId;
	}
	public void setCounterId(String counterId) {
		this.counterId = counterId;
	}
	public String getCounterTrader() {
		return counterTrader;
	}
	public void setCounterTrader(String counterTrader) {
		this.counterTrader = counterTrader;
	}
	public String getFinprodId() {
		return finprodId;
	}
	public void setFinprodId(String finprodId) {
		this.finprodId = finprodId;
	}
	public String getSettleSpreed() {
		return settleSpreed;
	}
	public void setSettleSpreed(String settleSpreed) {
		this.settleSpreed = settleSpreed;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getMatureYield() {
		return matureYield;
	}
	public void setMatureYield(String matureYield) {
		this.matureYield = matureYield;
	}
	public String getEntrustNum() {
		return entrustNum;
	}
	public void setEntrustNum(String entrustNum) {
		this.entrustNum = entrustNum;
	}
	public String getExerYield() {
		return exerYield;
	}
	public void setExerYield(String exerYield) {
		this.exerYield = exerYield;
	}
	public String getCcy() {
		return ccy;
	}
	public void setCcy(String ccy) {
		this.ccy = ccy;
	}
	public String getEntrustAmt() {
		return entrustAmt;
	}
	public void setEntrustAmt(String entrustAmt) {
		this.entrustAmt = entrustAmt;
	}
	public String getEntrustId() {
		return entrustId;
	}
	public void setEntrustId(String entrustId) {
		this.entrustId = entrustId;
	}
	public String getPortfolioId() {
		return portfolioId;
	}
	public void setPortfolioId(String portfolioId) {
		this.portfolioId = portfolioId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
