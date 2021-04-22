package com.joyintech.tams.exchange.common.bean;

import com.joyintech.tams.exchange.common.constant.EntrustBs;
import com.joyintech.tams.exchange.common.constant.HedgeFlag;

/**
 * @author liujianping
 * @ClassName FuturesPositionBean
 * @Description
 * @date 2020/11/2 16:08
 * @Version
 **/

public class FuturesPositionBean {
    private String accountId;

    private String financeAccount;

    private String market;

    private String stockCode;

    ///投机套保标志
    private String hedgeFlag;
    ///买卖
    private String entrustBs;
    ///持仓日期
    private String positionDate;
    ///上日持仓
    private int ydPosition;
    ///今日持仓
    private int position;
    ///多头冻结
    private double longFrozen;
    ///空头冻结
    private double shortFrozen;
    ///开仓冻结金额
    private double longFrozenAmount;
    ///开仓冻结金额
    private double shortFrozenAmount;
    ///开仓量
    private int openVolume;
    ///平仓量
    private int closeVolume;
    ///开仓金额
    private double openAmount;
    ///平仓金额
    private double closeAmount;
    ///持仓成本
    private double positionCost;
    ///上次占用的保证金
    private double preMargin;
    ///占用的保证金
    private double useMargin;
    ///冻结的保证金
    private double frozenMargin;
    ///冻结的资金
    private double frozenCash;
    ///冻结的手续费
    private double frozenCommission;
    ///资金差额
    private double cashIn;
    ///手续费
    private double commission;
    ///平仓盈亏
    private double closeProfit;
    ///持仓盈亏
    private double positionProfit;
    ///上次结算价
    private double preSettlementPrice;
    ///本次结算价
    private double settlementPrice;
    ///交易日
    private String tradingDay;
    ///结算编号
    private String settlementID;
    ///开仓成本
    private double openCost;
    ///交易所保证金
    private double exchangeMargin;
    ///组合成交形成的持仓
    private int combPosition;
    ///组合多头冻结
    private double combLongFrozen;
    ///组合空头冻结
    private double combShortFrozen;
    ///逐日盯市平仓盈亏
    private double closeProfitByDate;
    ///逐笔对冲平仓盈亏
    private double closeProfitByTrade;
    ///今日持仓
    private int todayPosition;
    ///保证金率
    private double marginRateByMoney;
    ///保证金率(按手数)
    private double marginRateByVolume;
    ///执行冻结
    private double strikeFrozen;
    ///执行冻结金额
    private double strikeFrozenAmount;
    ///放弃执行冻结
    private double abandonFrozen;
    ///执行冻结的昨仓
    private double ydStrikeFrozen;
    ///投资单元代码
    private String investUnitID;
    ///大商所持仓成本差值，只有大商所使用
    private double positionCostOffset;

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

    public String getPositionDate() {
        return positionDate;
    }

    public void setPositionDate(String positionDate) {
        this.positionDate = positionDate;
    }

    public int getYdPosition() {
        return ydPosition;
    }

    public void setYdPosition(int ydPosition) {
        this.ydPosition = ydPosition;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public double getLongFrozen() {
        return longFrozen;
    }

    public void setLongFrozen(double longFrozen) {
        this.longFrozen = longFrozen;
    }

    public double getShortFrozen() {
        return shortFrozen;
    }

    public void setShortFrozen(double shortFrozen) {
        this.shortFrozen = shortFrozen;
    }

    public double getLongFrozenAmount() {
        return longFrozenAmount;
    }

    public void setLongFrozenAmount(double longFrozenAmount) {
        this.longFrozenAmount = longFrozenAmount;
    }

    public double getShortFrozenAmount() {
        return shortFrozenAmount;
    }

    public void setShortFrozenAmount(double shortFrozenAmount) {
        this.shortFrozenAmount = shortFrozenAmount;
    }

    public int getOpenVolume() {
        return openVolume;
    }

    public void setOpenVolume(int openVolume) {
        this.openVolume = openVolume;
    }

    public int getCloseVolume() {
        return closeVolume;
    }

    public void setCloseVolume(int closeVolume) {
        this.closeVolume = closeVolume;
    }

    public double getOpenAmount() {
        return openAmount;
    }

    public void setOpenAmount(double openAmount) {
        this.openAmount = openAmount;
    }

    public double getCloseAmount() {
        return closeAmount;
    }

    public void setCloseAmount(double closeAmount) {
        this.closeAmount = closeAmount;
    }

    public double getPositionCost() {
        return positionCost;
    }

    public void setPositionCost(double positionCost) {
        this.positionCost = positionCost;
    }

    public double getPreMargin() {
        return preMargin;
    }

    public void setPreMargin(double preMargin) {
        this.preMargin = preMargin;
    }

    public double getUseMargin() {
        return useMargin;
    }

    public void setUseMargin(double useMargin) {
        this.useMargin = useMargin;
    }

    public double getFrozenMargin() {
        return frozenMargin;
    }

    public void setFrozenMargin(double frozenMargin) {
        this.frozenMargin = frozenMargin;
    }

    public double getFrozenCash() {
        return frozenCash;
    }

    public void setFrozenCash(double frozenCash) {
        this.frozenCash = frozenCash;
    }

    public double getFrozenCommission() {
        return frozenCommission;
    }

    public void setFrozenCommission(double frozenCommission) {
        this.frozenCommission = frozenCommission;
    }

    public double getCashIn() {
        return cashIn;
    }

    public void setCashIn(double cashIn) {
        this.cashIn = cashIn;
    }

    public double getCommission() {
        return commission;
    }

    public void setCommission(double commission) {
        this.commission = commission;
    }

    public double getCloseProfit() {
        return closeProfit;
    }

    public void setCloseProfit(double closeProfit) {
        this.closeProfit = closeProfit;
    }

    public double getPositionProfit() {
        return positionProfit;
    }

    public void setPositionProfit(double positionProfit) {
        this.positionProfit = positionProfit;
    }

    public double getPreSettlementPrice() {
        return preSettlementPrice;
    }

    public void setPreSettlementPrice(double preSettlementPrice) {
        this.preSettlementPrice = preSettlementPrice;
    }

    public double getSettlementPrice() {
        return settlementPrice;
    }

    public void setSettlementPrice(double settlementPrice) {
        this.settlementPrice = settlementPrice;
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

    public double getOpenCost() {
        return openCost;
    }

    public void setOpenCost(double openCost) {
        this.openCost = openCost;
    }

    public double getExchangeMargin() {
        return exchangeMargin;
    }

    public void setExchangeMargin(double exchangeMargin) {
        this.exchangeMargin = exchangeMargin;
    }

    public int getCombPosition() {
        return combPosition;
    }

    public void setCombPosition(int combPosition) {
        this.combPosition = combPosition;
    }

    public double getCombLongFrozen() {
        return combLongFrozen;
    }

    public void setCombLongFrozen(double combLongFrozen) {
        this.combLongFrozen = combLongFrozen;
    }

    public double getCombShortFrozen() {
        return combShortFrozen;
    }

    public void setCombShortFrozen(double combShortFrozen) {
        this.combShortFrozen = combShortFrozen;
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

    public int getTodayPosition() {
        return todayPosition;
    }

    public void setTodayPosition(int todayPosition) {
        this.todayPosition = todayPosition;
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

    public double getStrikeFrozen() {
        return strikeFrozen;
    }

    public void setStrikeFrozen(double strikeFrozen) {
        this.strikeFrozen = strikeFrozen;
    }

    public double getStrikeFrozenAmount() {
        return strikeFrozenAmount;
    }

    public void setStrikeFrozenAmount(double strikeFrozenAmount) {
        this.strikeFrozenAmount = strikeFrozenAmount;
    }

    public double getAbandonFrozen() {
        return abandonFrozen;
    }

    public void setAbandonFrozen(double abandonFrozen) {
        this.abandonFrozen = abandonFrozen;
    }

    public double getYdStrikeFrozen() {
        return ydStrikeFrozen;
    }

    public void setYdStrikeFrozen(double ydStrikeFrozen) {
        this.ydStrikeFrozen = ydStrikeFrozen;
    }

    public String getInvestUnitID() {
        return investUnitID;
    }

    public void setInvestUnitID(String investUnitID) {
        this.investUnitID = investUnitID;
    }

    public double getPositionCostOffset() {
        return positionCostOffset;
    }

    public void setPositionCostOffset(double positionCostOffset) {
        this.positionCostOffset = positionCostOffset;
    }
}
