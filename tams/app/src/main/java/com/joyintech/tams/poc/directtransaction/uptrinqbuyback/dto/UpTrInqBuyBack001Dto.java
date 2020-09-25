package com.joyintech.tams.poc.directtransaction.uptrinqbuyback.dto;

import com.joyintech.framework.base.BaseEntity;

import java.math.BigDecimal;

public class UpTrInqBuyBack001Dto extends BaseEntity{

	/**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -8259658424290761601L;

    /*
     * 回购期限
     */
    private String termType;
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
    private Long settleSpreed;
    /*
     * 净价
     */
    private BigDecimal price;
    /*
     * 到期收益率
     */
    private BigDecimal matureYield;
    /*
     *券面总额 
     */
    private BigDecimal entrustNum;
    /*
     * 行权收益率
     */
    private BigDecimal exerYield;
    /*
     * 结算币种
     */
    private String ccy;
    /*
     * 交易金额  
     */
    private BigDecimal entrustAmt;
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
    
    /**
     * 期限天数，到期日-起息日
     */
    private Long termDays;
    
    /**
     * 回购利率
     */
    private String repoRate;
    
    
    
	public String getRepoRate() {
		return repoRate;
	}
	public void setRepoRate(String repoRate) {
		this.repoRate = repoRate;
	}
	public Long getTermDays() {
		return termDays;
	}
	public void setTermDays(Long termDays) {
		this.termDays = termDays;
	}
	public String getPortfolioName() {
		return portfolioName;
	}
	public String getTermType() {
		return termType;
	}
	public void setTermType(String termType) {
		this.termType = termType;
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
	public Long getSettleSpreed() {
		return settleSpreed;
	}
	public void setSettleSpreed(Long settleSpreed) {
		this.settleSpreed = settleSpreed;
	}
	
	public String getCcy() {
		return ccy;
	}
	public void setCcy(String ccy) {
		this.ccy = ccy;
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
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public BigDecimal getMatureYield() {
		return matureYield;
	}
	public void setMatureYield(BigDecimal matureYield) {
		this.matureYield = matureYield;
	}
	public BigDecimal getEntrustNum() {
		return entrustNum;
	}
	public void setEntrustNum(BigDecimal entrustNum) {
		this.entrustNum = entrustNum;
	}
	public BigDecimal getExerYield() {
		return exerYield;
	}
	public void setExerYield(BigDecimal exerYield) {
		this.exerYield = exerYield;
	}
	public BigDecimal getEntrustAmt() {
		return entrustAmt;
	}
	public void setEntrustAmt(BigDecimal entrustAmt) {
		this.entrustAmt = entrustAmt;
	}
	public void setPortfolioName(String portfolioName) {
		this.portfolioName = portfolioName;
	}
	
	private BigDecimal fullPrice;
	
	private BigDecimal unitIntAmt;
	
	private BigDecimal tradeAmt;
	
	private BigDecimal intAmt;
	
	private BigDecimal settleAmt;


	public BigDecimal getFullPrice() {
		return fullPrice;
	}
	public void setFullPrice(BigDecimal fullPrice) {
		this.fullPrice = fullPrice;
	}
	public BigDecimal getUnitIntAmt() {
		return unitIntAmt;
	}
	public void setUnitIntAmt(BigDecimal unitIntAmt) {
		this.unitIntAmt = unitIntAmt;
	}
	public BigDecimal getTradeAmt() {
		return tradeAmt;
	}
	public void setTradeAmt(BigDecimal tradeAmt) {
		this.tradeAmt = tradeAmt;
	}
	public BigDecimal getIntAmt() {
		return intAmt;
	}
	public void setIntAmt(BigDecimal intAmt) {
		this.intAmt = intAmt;
	}
	public BigDecimal getSettleAmt() {
		return settleAmt;
	}
	public void setSettleAmt(BigDecimal settleAmt) {
		this.settleAmt = settleAmt;
	}

	
}
