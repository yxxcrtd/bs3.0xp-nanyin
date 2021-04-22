package com.joyintech.tams.exchange.common.bean;

import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yking on 2016/5/15 0015.
 */
public class TickBean {
    private String exchange;
    private String instrumentID;
    private double updateTime;            //行情时间戳
    private String strTime;             //可视化时间
    private double lastPrice;            //最新价
    private double openPrice;            //开盘价
    private double highPrice;            //最高价
    private double lowPrice;             //最低价

    private long volume;                //成交总量
    private long turnover;              //换手率
    private long position;              //合约持仓量(期)
    private double tradeBalance;
    private double upperLimitPrice;      //涨停价
    private double lowerLimitPrice;      //跌停价
    private double settlePrice;          //结算价
    private double preClosePrice;        //昨收盘价

    private List<Double> askPrice = new ArrayList<>();
    private List<Integer> askVolume = new ArrayList<>();
    private List<Double> bidPrice = new ArrayList<>();
    private List<Integer> bidVolume = new ArrayList<>();

    public void addAskPrice(double price) {
        askPrice.add(price);
    }

    public void addAskVolume(int volume) {
        askVolume.add(volume);
    }

    public void addBidPrice(double price) {
        bidPrice.add(price);
    }

    public void addBidVolume(int volume) {
        bidVolume.add(volume);
    }


    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    @Id
    public String getInstrumentID() {
        return instrumentID;
    }

    public void setInstrumentID(String instrumentID) {
        this.instrumentID = instrumentID;
    }

    public double getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(double updateTime) {
        this.updateTime = updateTime;
    }

    public String getStrTime() {
        return strTime;
    }

    public void setStrTime(String strTime) {
        this.strTime = strTime;
    }

    public double getLastPrice() {
        return lastPrice;
    }

    public void setLastPrice(double lastPrice) {
        this.lastPrice = lastPrice;
    }

    public double getOpenPrice() {
        return openPrice;
    }

    public void setOpenPrice(double openPrice) {
        this.openPrice = openPrice;
    }

    public double getHighPrice() {
        return highPrice;
    }

    public void setHighPrice(double highPrice) {
        this.highPrice = highPrice;
    }

    public double getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(double lowPrice) {
        this.lowPrice = lowPrice;
    }

    public long getVolume() {
        return volume;
    }

    public void setVolume(long volume) {
        this.volume = volume;
    }

    public long getTurnover() {
        return turnover;
    }

    public void setTurnover(long turnover) {
        this.turnover = turnover;
    }

    public long getPosition() {
        return position;
    }

    public void setPosition(long position) {
        this.position = position;
    }

    public double getTradeBalance() {
        return tradeBalance;
    }

    public void setTradeBalance(double tradeBalance) {
        this.tradeBalance = tradeBalance;
    }

    public double getUpperLimitPrice() {
        return upperLimitPrice;
    }

    public void setUpperLimitPrice(double upperLimitPrice) {
        this.upperLimitPrice = upperLimitPrice;
    }

    public double getLowerLimitPrice() {
        return lowerLimitPrice;
    }

    public void setLowerLimitPrice(double lowerLimitPrice) {
        this.lowerLimitPrice = lowerLimitPrice;
    }

    public double getSettlePrice() {
        return settlePrice;
    }

    public void setSettlePrice(double settlePrice) {
        this.settlePrice = settlePrice;
    }

    public double getPreClosePrice() {
        return preClosePrice;
    }

    public void setPreClosePrice(double preClosePrice) {
        this.preClosePrice = preClosePrice;
    }

    public List<Double> getAskPrice() {
        return askPrice;
    }

    public void setAskPrice(List<Double> askPrice) {
        this.askPrice = askPrice;
    }

    public List<Integer> getAskVolume() {
        return askVolume;
    }

    public void setAskVolume(List<Integer> askVolume) {
        this.askVolume = askVolume;
    }

    public List<Double> getBidPrice() {
        return bidPrice;
    }

    public void setBidPrice(List<Double> bidPrice) {
        this.bidPrice = bidPrice;
    }

    public List<Integer> getBidVolume() {
        return bidVolume;
    }

    public void setBidVolume(List<Integer> bidVolume) {
        this.bidVolume = bidVolume;
    }
}
