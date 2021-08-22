
package com.joyintech.tams.invest.trade.trdrepotrade.dto;

import java.math.BigDecimal;

public class TrdRepoTradeExportPdfDto {

    /**
     * 成交编号
     */
    private String tradeId;

    /**
     * 组合代码
     */
    private String portfolioId;
    /**
     * 组合名称
     */
    private String portfolioName;
    /**
     * 回购类型
     */
    private String repType;
    /**
     * 本方方向
     */
    private String psStr;
    /**
     * 本方
     */
    private String bf;
    /**
     * 对手方
     */
    private String counterName;
    /**
     * 首次结算日
     */
    private String vpayDate;
    /**
     * 到期结算日
     */
    private String mpayDate;
    /**
     * 回购期限
     */
    private String term;
    /**
     * 实际占款天数
     */
    private String actCapDays;
    /**
     * 回购利率
     */
    private BigDecimal intRate;
    /**
     * 债券代码
     */
    private String repSecId;
    /**
     * 债券名称
     */
    private String repSecName;
    /**
     * 交易金额
     */
    private BigDecimal tradeAmt;
    /**
     * 质押率
     */
    private String pledgeRatio;
    /**
     * 质押券面
     */
    private String pledgeAmt;
    /**
     * 回购利息
     */
    private BigDecimal mIntAmt;
    /**
     * 到期金额
     */
    private BigDecimal mTradeAmt;
    /**
     * 清算速度
     */
    private String sttleSpeed;
    /**
     * 清算类型
     */
    private String clearType;
    /**
     * 成交时间
     */
    private String tradeDateStr;
    /**
     * 结算状态
     */
    private String state;

    /**
     * 资金账号
     */
    private String busiAcctNo;
    /**
     * 证券代码
     */
    private String finprodMarketId;
    /**
     * 证券名称
     */
    private String finprodAbbr;
    /**
     * 证券类别
     */
    private String secType;
    /**
     * 委托状态
     */
    private String entrustStatus;
    /**
     * 业务分类
     */
    private String tradePlat;
    /**
     * 投资类型
     */
    private String entrustType;
    /**
     * 价格类型
     */
    private String priceModel;
    /**
     * 委托价格
     */
    private BigDecimal price;
    /**
     * 委托数量
     */
    private BigDecimal entrustNum;
    /**
     * 委托金额
     */
    private BigDecimal entrustAmt;
    /**
     * 成交度
     */
    private String transactioncom;
    /**
     * 成交均价
     */
    private BigDecimal unitCprice;
    /**
     * 成交全价
     */
    private BigDecimal unitFprice;
    /**
     * 成交数量
     */
    private BigDecimal shareAmt;
    /**
     * 下达人姓名
     */
    private String traderName;
    /**
     * 指令下达人姓名
     */
    private String invManagerName;
    /**
     * 对手席号
     */
    private String counterSeat;
    /**
     * 约定号
     */
    private String counterConNo;
    /**
     *  对手交易员
     */
    private String counterTrader;

    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    public String getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }

    public String getPortfolioName() {
        return portfolioName;
    }

    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
    }

    public String getRepType() {
        return repType;
    }

    public void setRepType(String repType) {
        this.repType = repType;
    }

    public String getPsStr() {
        return psStr;
    }

    public void setPsStr(String psStr) {
        this.psStr = psStr;
    }

    public String getBf() {
        return bf;
    }

    public void setBf(String bf) {
        this.bf = bf;
    }

    public String getCounterName() {
        return counterName;
    }

    public void setCounterName(String counterName) {
        this.counterName = counterName;
    }

    public String getVpayDate() {
        return vpayDate;
    }

    public void setVpayDate(String vpayDate) {
        this.vpayDate = vpayDate;
    }

    public String getMpayDate() {
        return mpayDate;
    }

    public void setMpayDate(String mpayDate) {
        this.mpayDate = mpayDate;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getActCapDays() {
        return actCapDays;
    }

    public void setActCapDays(String actCapDays) {
        this.actCapDays = actCapDays;
    }

    public BigDecimal getIntRate() {
        return intRate;
    }

    public void setIntRate(BigDecimal intRate) {
        this.intRate = intRate;
    }

    public String getRepSecId() {
        return repSecId;
    }

    public void setRepSecId(String repSecId) {
        this.repSecId = repSecId;
    }

    public String getRepSecName() {
        return repSecName;
    }

    public void setRepSecName(String repSecName) {
        this.repSecName = repSecName;
    }

    public BigDecimal getTradeAmt() {
        return tradeAmt == null?BigDecimal.ZERO:tradeAmt;
    }

    public void setTradeAmt(BigDecimal tradeAmt) {
        this.tradeAmt = tradeAmt;
    }

    public String getPledgeRatio() {
        return pledgeRatio;
    }

    public void setPledgeRatio(String pledgeRatio) {
        this.pledgeRatio = pledgeRatio;
    }

    public String getPledgeAmt() {
        return pledgeAmt;
    }

    public void setPledgeAmt(String pledgeAmt) {
        this.pledgeAmt = pledgeAmt;
    }

    public BigDecimal getmIntAmt() {
        return mIntAmt;
    }

    public void setmIntAmt(BigDecimal mIntAmt) {
        this.mIntAmt = mIntAmt;
    }

    public BigDecimal getmTradeAmt() {
        return mTradeAmt;
    }

    public void setmTradeAmt(BigDecimal mTradeAmt) {
        this.mTradeAmt = mTradeAmt;
    }

    public String getSttleSpeed() {
        return sttleSpeed;
    }

    public void setSttleSpeed(String sttleSpeed) {
        this.sttleSpeed = sttleSpeed;
    }

    public String getClearType() {
        return clearType;
    }

    public void setClearType(String clearType) {
        this.clearType = clearType;
    }

    public String getTradeDateStr() {
        return tradeDateStr;
    }

    public void setTradeDateStr(String tradeDateStr) {
        this.tradeDateStr = tradeDateStr;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getBusiAcctNo() {
        return busiAcctNo;
    }

    public void setBusiAcctNo(String busiAcctNo) {
        this.busiAcctNo = busiAcctNo;
    }

    public String getFinprodMarketId() {
        return finprodMarketId;
    }

    public void setFinprodMarketId(String finprodMarketId) {
        this.finprodMarketId = finprodMarketId;
    }

    public String getFinprodAbbr() {
        return finprodAbbr;
    }

    public void setFinprodAbbr(String finprodAbbr) {
        this.finprodAbbr = finprodAbbr;
    }

    public String getSecType() {
        return secType;
    }

    public void setSecType(String secType) {
        this.secType = secType;
    }

    public String getEntrustStatus() {
        return entrustStatus;
    }

    public void setEntrustStatus(String entrustStatus) {
        this.entrustStatus = entrustStatus;
    }

    public String getTradePlat() {
        return tradePlat;
    }

    public void setTradePlat(String tradePlat) {
        this.tradePlat = tradePlat;
    }

    public String getEntrustType() {
        return entrustType;
    }

    public void setEntrustType(String entrustType) {
        this.entrustType = entrustType;
    }

    public String getPriceModel() {
        return priceModel;
    }

    public void setPriceModel(String priceModel) {
        this.priceModel = priceModel;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getEntrustNum() {
        return entrustNum == null?BigDecimal.ZERO:entrustNum;
    }

    public void setEntrustNum(BigDecimal entrustNum) {
        this.entrustNum = entrustNum;
    }

    public BigDecimal getEntrustAmt() {
        return entrustAmt == null?BigDecimal.ZERO:entrustAmt;
    }

    public void setEntrustAmt(BigDecimal entrustAmt) {
        this.entrustAmt = entrustAmt;
    }

    public String getTransactioncom() {
        return transactioncom;
    }

    public void setTransactioncom(String transactioncom) {
        this.transactioncom = transactioncom;
    }

    public BigDecimal getUnitCprice() {
        return unitCprice;
    }

    public void setUnitCprice(BigDecimal unitCprice) {
        this.unitCprice = unitCprice;
    }

    public BigDecimal getUnitFprice() {
        return unitFprice;
    }

    public void setUnitFprice(BigDecimal unitFprice) {
        this.unitFprice = unitFprice;
    }

    public BigDecimal getShareAmt() {
        return shareAmt == null?BigDecimal.ZERO:shareAmt;
    }

    public void setShareAmt(BigDecimal shareAmt) {
        this.shareAmt = shareAmt;
    }

    public String getTraderName() {
        return traderName;
    }

    public void setTraderName(String traderName) {
        this.traderName = traderName;
    }

    public String getInvManagerName() {
        return invManagerName;
    }

    public void setInvManagerName(String invManagerName) {
        this.invManagerName = invManagerName;
    }

    public String getCounterSeat() {
        return counterSeat;
    }

    public void setCounterSeat(String counterSeat) {
        this.counterSeat = counterSeat;
    }

    public String getCounterConNo() {
        return counterConNo;
    }

    public void setCounterConNo(String counterConNo) {
        this.counterConNo = counterConNo;
    }

    public String getCounterTrader() {
        return counterTrader;
    }

    public void setCounterTrader(String counterTrader) {
        this.counterTrader = counterTrader;
    }
}
