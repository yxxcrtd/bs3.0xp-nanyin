package com.joyintech.tams.settle.batch.datacreate.dto;

/**
 * 金融产品交易临时表.交易方向是买入、卖出、正回购、逆回购的检索结果DTO
 * @author 陈卓
 * @date 2020/11/24
 * @see StlDataCreateDto
 * @since 1.0
 */
public class StlDataCreateDto {

    /**
     * 交易编号
     */
	private String tradeId;
	
	/**
	 * 申请编号
	 */
	private String orderNo;
	
	/**
	 * 交易方向
	 */
	private String ps;
	
	/**
	 * 金融产品类型（投管）
	 */
	private String finprodTypeTg;
	
	/**
	 * 金融产品代码
	 */
	private String finprodId;
	
	/**
	 * 交易日期
	 */
	private String tradeDate;
	
	/**
	 * 交割日
	 */
	private String settleDate;
	
	/**
	 * 交易份额
	 */
	private String shareAmt;
	
	/**
	 * 单位净价
	 */
	private String unitCprice;
	
	/**
	 * 单位全价
	 */
	private String unitFprice;
	
	/**
	 * 全价总额
	 */
	private String fpriceAmt;
	
	/**
	 * 净价总额
	 */
	private String cpriceAmt;
	
	/**
	 * 交易总额
	 */
	private String tradeAmt;
	
	/**
	 * 币种
	 */
	private String ccy;
	
	/**
	 * 投资组合代码
	 */
	private String portfolioId;
	
	/**
	 * 交易场所
	 */
	private String tradeMarket;
	
    /**
     * 是否纳入净额结算
     */
    private String isNetSett;
    
    /**
     * 交易所基金类型
     */
    private String trmFundType;
    
    /**
     * 上市板
     */
    private String board;

    /**
     * 市场代码
     */
    private String finprodMarketId;

    /**
     * 到期交付日
     */
    private String mPayDate;
    
    /**
     * 固定利率
     */
    private String intRate;

    /**
     * 质押券代码
     */
    private String pledgeFinprodId;

    /**
     * 证券账户代码
     */
    private String secAcctId;
    
    /**
     * 股东代码
     */
    private String bondId;
    
    /**
     * 资金账户
     */
    private String trdAcctId;
    
    /**
     * 上交所席位号
     */
    private String shSeatNo;
    
    /**
     * 深交所席位号
     */
    private String szSeatNo;
    
    /**
     * 回购类型
     */
    private String type6;
    
    /**
     * 回购方向
     */
    private String type7;
    
    /**
     * 一级分类
     */
    private String type9;
	
	/**
	 * 交易本金
	 */
	private String prinAmt;
    
    /**
     * 交易平台
     */
    private String tradePlat;
    
    /**
     * 交易所金融产品代码
     */
    private String tradeFinprodId;

	public String getTradeId() {
		return tradeId;
	}

	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getPs() {
		return ps;
	}

	public void setPs(String ps) {
		this.ps = ps;
	}

	public String getFinprodTypeTg() {
		return finprodTypeTg;
	}

	public void setFinprodTypeTg(String finprodTypeTg) {
		this.finprodTypeTg = finprodTypeTg;
	}

	public String getFinprodId() {
		return finprodId;
	}

	public void setFinprodId(String finprodId) {
		this.finprodId = finprodId;
	}

	public String getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(String tradeDate) {
		this.tradeDate = tradeDate;
	}

	public String getSettleDate() {
		return settleDate;
	}

	public void setSettleDate(String settleDate) {
		this.settleDate = settleDate;
	}

	public String getShareAmt() {
		return shareAmt;
	}

	public void setShareAmt(String shareAmt) {
		this.shareAmt = shareAmt;
	}

	public String getUnitCprice() {
		return unitCprice;
	}

	public void setUnitCprice(String unitCprice) {
		this.unitCprice = unitCprice;
	}

	public String getUnitFprice() {
		return unitFprice;
	}

	public void setUnitFprice(String unitFprice) {
		this.unitFprice = unitFprice;
	}

	public String getFpriceAmt() {
		return fpriceAmt;
	}

	public void setFpriceAmt(String fpriceAmt) {
		this.fpriceAmt = fpriceAmt;
	}

	public String getCpriceAmt() {
		return cpriceAmt;
	}

	public void setCpriceAmt(String cpriceAmt) {
		this.cpriceAmt = cpriceAmt;
	}

	public String getTradeAmt() {
		return tradeAmt;
	}

	public void setTradeAmt(String tradeAmt) {
		this.tradeAmt = tradeAmt;
	}

	public String getCcy() {
		return ccy;
	}

	public void setCcy(String ccy) {
		this.ccy = ccy;
	}

	public String getPortfolioId() {
		return portfolioId;
	}

	public void setPortfolioId(String portfolioId) {
		this.portfolioId = portfolioId;
	}

	public String getTradeMarket() {
		return tradeMarket;
	}

	public void setTradeMarket(String tradeMarket) {
		this.tradeMarket = tradeMarket;
	}

	public String getIsNetSett() {
		return isNetSett;
	}

	public void setIsNetSett(String isNetSett) {
		this.isNetSett = isNetSett;
	}

	public String getTrmFundType() {
		return trmFundType;
	}

	public void setTrmFundType(String trmFundType) {
		this.trmFundType = trmFundType;
	}

	public String getBoard() {
		return board;
	}

	public void setBoard(String board) {
		this.board = board;
	}

	public String getFinprodMarketId() {
		return finprodMarketId;
	}

	public void setFinprodMarketId(String finprodMarketId) {
		this.finprodMarketId = finprodMarketId;
	}

	public String getmPayDate() {
		return mPayDate;
	}

	public void setmPayDate(String mPayDate) {
		this.mPayDate = mPayDate;
	}

	public String getIntRate() {
		return intRate;
	}

	public void setIntRate(String intRate) {
		this.intRate = intRate;
	}

	public String getPledgeFinprodId() {
		return pledgeFinprodId;
	}

	public void setPledgeFinprodId(String pledgeFinprodId) {
		this.pledgeFinprodId = pledgeFinprodId;
	}

	public String getSecAcctId() {
		return secAcctId;
	}

	public void setSecAcctId(String secAcctId) {
		this.secAcctId = secAcctId;
	}

	public String getBondId() {
		return bondId;
	}

	public void setBondId(String bondId) {
		this.bondId = bondId;
	}

	public String getTrdAcctId() {
		return trdAcctId;
	}

	public void setTrdAcctId(String trdAcctId) {
		this.trdAcctId = trdAcctId;
	}

	public String getShSeatNo() {
		return shSeatNo;
	}

	public void setShSeatNo(String shSeatNo) {
		this.shSeatNo = shSeatNo;
	}

	public String getSzSeatNo() {
		return szSeatNo;
	}

	public void setSzSeatNo(String szSeatNo) {
		this.szSeatNo = szSeatNo;
	}

	public String getType6() {
		return type6;
	}

	public void setType6(String type6) {
		this.type6 = type6;
	}

	public String getType7() {
		return type7;
	}

	public void setType7(String type7) {
		this.type7 = type7;
	}

	public String getType9() {
		return type9;
	}

	public void setType9(String type9) {
		this.type9 = type9;
	}

	public String getTradePlat() {
		return tradePlat;
	}

	public void setTradePlat(String tradePlat) {
		this.tradePlat = tradePlat;
	}

	public String getPrinAmt() {
		return prinAmt;
	}

	public void setPrinAmt(String prinAmt) {
		this.prinAmt = prinAmt;
	}

	public String getTradeFinprodId() {
		return tradeFinprodId;
	}

	public void setTradeFinprodId(String tradeFinprodId) {
		this.tradeFinprodId = tradeFinprodId;
	}
}
