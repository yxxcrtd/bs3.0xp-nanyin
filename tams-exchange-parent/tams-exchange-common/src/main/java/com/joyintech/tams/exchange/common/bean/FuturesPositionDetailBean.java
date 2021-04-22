package com.joyintech.tams.exchange.common.bean;

import com.joyintech.tams.exchange.common.constant.EntrustBs;
import com.joyintech.tams.exchange.common.constant.HedgeFlag;

/**
 * @author liujianping
 * @ClassName FuturesPositionDetailBean
 * @Description
 * @date 2020/11/2 15:51
 * @Version
 **/

public class FuturesPositionDetailBean {
    private String accountId;

    private String financeAccount;

    private String market;

    private String stockCode;

    ///投机套保标志
    private String hedgeFlag;
    ///买卖
    private String entrustBs;
    ///开仓日期
    private String openDate;
    ///成交编号
    private String tradeId;
    ///数量
    private int volume;
    ///开仓价
    private double openPrice;
    ///交易日
    private String tradingDay;
    ///结算编号
    private String settlementID;
    ///成交类型
    private String tradeType;
    ///组合合约代码
    private String combInstrumentId;
    ///逐日盯市平仓盈亏
    private double closeProfitByDate;
    ///逐笔对冲平仓盈亏
    private double closeProfitByTrade;
    ///逐日盯市持仓盈亏
    private double positionProfitByDate;
    ///逐笔对冲持仓盈亏
    private double positionProfitByTrade;
    ///投资者保证金
    private double margin;
    ///交易所保证金
    private double exchMargin;
    ///保证金率
    private double marginRateByMoney;
    ///保证金率(按手数)
    private double marginRateByVolume;
    ///昨结算价
    private double lastSettlementPrice;
    ///结算价
    private double settlementPrice;
    ///平仓量
    private int closeVolume;
    ///平仓金额
    private double closeAmount;
    ///按照时间顺序平仓的笔数,大商所专用
    private int timeFirstVolume;
    ///投资单元代码
    private String investUnitID;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getFinanceAccount() {
        return financeAccount;
    }

    public void setFinanceAccount(String financeAccount) {
        this.financeAccount = financeAccount;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public String getHedgeFlag() {
        return hedgeFlag;
    }

    public void setHedgeFlag(String hedgeFlag) {
        this.hedgeFlag = hedgeFlag;
    }

    public String getEntrustBs() {
        return entrustBs;
    }

    public void setEntrustBs(String entrustBs) {
        this.entrustBs = entrustBs;
    }

    public String getOpenDate() {
        return openDate;
    }

    public void setOpenDate(String openDate) {
        this.openDate = openDate;
    }

    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public double getOpenPrice() {
        return openPrice;
    }

    public void setOpenPrice(double openPrice) {
        this.openPrice = openPrice;
    }

    public String getTradingDay() {
        return tradingDay;
    }

    public void setTradingDay(String tradingDay) {
        this.tradingDay = tradingDay;
    }

    public String getSettlementID() {
        return settlementID;
    }

    public void setSettlementID(String settlementID) {
        this.settlementID = settlementID;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getCombInstrumentId() {
        return combInstrumentId;
    }

    public void setCombInstrumentId(String combInstrumentId) {
        this.combInstrumentId = combInstrumentId;
    }

    public double getCloseProfitByDate() {
        return closeProfitByDate;
    }

    public void setCloseProfitByDate(double closeProfitByDate) {
        this.closeProfitByDate = closeProfitByDate;
    }

    public double getCloseProfitByTrade() {
        return closeProfitByTrade;
    }

    public void setCloseProfitByTrade(double closeProfitByTrade) {
        this.closeProfitByTrade = closeProfitByTrade;
    }

    public double getPositionProfitByDate() {
        return positionProfitByDate;
    }

    public void setPositionProfitByDate(double positionProfitByDate) {
        this.positionProfitByDate = positionProfitByDate;
    }

    public double getPositionProfitByTrade() {
        return positionProfitByTrade;
    }

    public void setPositionProfitByTrade(double positionProfitByTrade) {
        this.positionProfitByTrade = positionProfitByTrade;
    }

    public double getMargin() {
        return margin;
    }

    public void setMargin(double margin) {
        this.margin = margin;
    }

    public double getExchMargin() {
        return exchMargin;
    }

    public void setExchMargin(double exchMargin) {
        this.exchMargin = exchMargin;
    }

    public double getMarginRateByMoney() {
        return marginRateByMoney;
    }

    public void setMarginRateByMoney(double marginRateByMoney) {
        this.marginRateByMoney = marginRateByMoney;
    }

    public double getMarginRateByVolume() {
        return marginRateByVolume;
    }

    public void setMarginRateByVolume(double marginRateByVolume) {
        this.marginRateByVolume = marginRateByVolume;
    }

    public double getLastSettlementPrice() {
        return lastSettlementPrice;
    }

    public void setLastSettlementPrice(double lastSettlementPrice) {
        this.lastSettlementPrice = lastSettlementPrice;
    }

    public double getSettlementPrice() {
        return settlementPrice;
    }

    public void setSettlementPrice(double settlementPrice) {
        this.settlementPrice = settlementPrice;
    }

    public int getCloseVolume() {
        return closeVolume;
    }

    public void setCloseVolume(int closeVolume) {
        this.closeVolume = closeVolume;
    }

    public double getCloseAmount() {
        return closeAmount;
    }

    public void setCloseAmount(double closeAmount) {
        this.closeAmount = closeAmount;
    }

    public int getTimeFirstVolume() {
        return timeFirstVolume;
    }

    public void setTimeFirstVolume(int timeFirstVolume) {
        this.timeFirstVolume = timeFirstVolume;
    }

    public String getInvestUnitID() {
        return investUnitID;
    }

    public void setInvestUnitID(String investUnitID) {
        this.investUnitID = investUnitID;
    }
}
