package com.joyintech.tams.exchange.quoteterminal.sse.file.bean;

import java.time.LocalTime;

/**
 * @Author: think
 * @Description:
 * @Date: Created in 7:46 2021/8/14
 */
public abstract class SSEFileQuote {
    private String mdStreamID;              //行情数据类型
    private String securityID;              //产品代码
    private String symbol;                  //产品名称
    private Long tradeVolume;               //成交数量
    private Double totalValueTraded;        //成交金额
    private Double preClosePx;              //昨日收盘价
    private Double openPrice;               //今日开盘价
    private Double highPrice;               //最高价
    private Double lowPrice;                //最低价
    private Double tradePrice;              //最新价
    private Double closePx;                 //今收盘价
    private Double buyPrice1;               //申买价一
    private Integer buyVolume1;             //申买量一
    private Double sellPrice1;              //申卖价一
    private Integer sellVolume1;            //申卖量一
    private Double buyPrice2;               //申买价二
    private Integer buyVolume2;             //申买量二
    private Double sellPrice2;              //申卖价二
    private Integer sellVolume2;            //申卖量二
    private Double buyPrice3;               //申买价三
    private Integer buyVolume3;             //申买量三
    private Double sellPrice3;              //申卖价三
    private Integer sellVolume3;            //申卖量三
    private Double buyPrice4;               //申买价四
    private Integer buyVolume4;             //申买量四
    private Double sellPrice4;              //申卖价四
    private Integer sellVolume4;            //申卖量四
    private Double buyPrice5;               //申买价五
    private Integer buyVolume5;             //申买量五
    private Double sellPrice5;              //申卖价五
    private Integer sellVolume5;            //申卖量五

    public String getMdStreamID() {
        return mdStreamID;
    }

    public void setMdStreamID(String mdStreamID) {
        this.mdStreamID = mdStreamID;
    }

    public String getSecurityID() {
        return securityID;
    }

    public void setSecurityID(String securityID) {
        this.securityID = securityID;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Long getTradeVolume() {
        return tradeVolume;
    }

    public void setTradeVolume(Long tradeVolume) {
        this.tradeVolume = tradeVolume;
    }

    public Double getTotalValueTraded() {
        return totalValueTraded;
    }

    public void setTotalValueTraded(Double totalValueTraded) {
        this.totalValueTraded = totalValueTraded;
    }

    public Double getPreClosePx() {
        return preClosePx;
    }

    public void setPreClosePx(Double preClosePx) {
        this.preClosePx = preClosePx;
    }

    public Double getOpenPrice() {
        return openPrice;
    }

    public void setOpenPrice(Double openPrice) {
        this.openPrice = openPrice;
    }

    public Double getHighPrice() {
        return highPrice;
    }

    public void setHighPrice(Double highPrice) {
        this.highPrice = highPrice;
    }

    public Double getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(Double lowPrice) {
        this.lowPrice = lowPrice;
    }

    public Double getTradePrice() {
        return tradePrice;
    }

    public void setTradePrice(Double tradePrice) {
        this.tradePrice = tradePrice;
    }

    public Double getClosePx() {
        return closePx;
    }

    public void setClosePx(Double closePx) {
        this.closePx = closePx;
    }

    public Double getBuyPrice1() {
        return buyPrice1;
    }

    public void setBuyPrice1(Double buyPrice1) {
        this.buyPrice1 = buyPrice1;
    }

    public Integer getBuyVolume1() {
        return buyVolume1;
    }

    public void setBuyVolume1(Integer buyVolume1) {
        this.buyVolume1 = buyVolume1;
    }

    public Double getSellPrice1() {
        return sellPrice1;
    }

    public void setSellPrice1(Double sellPrice1) {
        this.sellPrice1 = sellPrice1;
    }

    public Integer getSellVolume1() {
        return sellVolume1;
    }

    public void setSellVolume1(Integer sellVolume1) {
        this.sellVolume1 = sellVolume1;
    }

    public Double getBuyPrice2() {
        return buyPrice2;
    }

    public void setBuyPrice2(Double buyPrice2) {
        this.buyPrice2 = buyPrice2;
    }

    public Integer getBuyVolume2() {
        return buyVolume2;
    }

    public void setBuyVolume2(Integer buyVolume2) {
        this.buyVolume2 = buyVolume2;
    }

    public Double getSellPrice2() {
        return sellPrice2;
    }

    public void setSellPrice2(Double sellPrice2) {
        this.sellPrice2 = sellPrice2;
    }

    public Integer getSellVolume2() {
        return sellVolume2;
    }

    public void setSellVolume2(Integer sellVolume2) {
        this.sellVolume2 = sellVolume2;
    }

    public Double getBuyPrice3() {
        return buyPrice3;
    }

    public void setBuyPrice3(Double buyPrice3) {
        this.buyPrice3 = buyPrice3;
    }

    public Integer getBuyVolume3() {
        return buyVolume3;
    }

    public void setBuyVolume3(Integer buyVolume3) {
        this.buyVolume3 = buyVolume3;
    }

    public Double getSellPrice3() {
        return sellPrice3;
    }

    public void setSellPrice3(Double sellPrice3) {
        this.sellPrice3 = sellPrice3;
    }

    public Integer getSellVolume3() {
        return sellVolume3;
    }

    public void setSellVolume3(Integer sellVolume3) {
        this.sellVolume3 = sellVolume3;
    }

    public Double getBuyPrice4() {
        return buyPrice4;
    }

    public void setBuyPrice4(Double buyPrice4) {
        this.buyPrice4 = buyPrice4;
    }

    public Integer getBuyVolume4() {
        return buyVolume4;
    }

    public void setBuyVolume4(Integer buyVolume4) {
        this.buyVolume4 = buyVolume4;
    }

    public Double getSellPrice4() {
        return sellPrice4;
    }

    public void setSellPrice4(Double sellPrice4) {
        this.sellPrice4 = sellPrice4;
    }

    public Integer getSellVolume4() {
        return sellVolume4;
    }

    public void setSellVolume4(Integer sellVolume4) {
        this.sellVolume4 = sellVolume4;
    }

    public Double getBuyPrice5() {
        return buyPrice5;
    }

    public void setBuyPrice5(Double buyPrice5) {
        this.buyPrice5 = buyPrice5;
    }

    public Integer getBuyVolume5() {
        return buyVolume5;
    }

    public void setBuyVolume5(Integer buyVolume5) {
        this.buyVolume5 = buyVolume5;
    }

    public Double getSellPrice5() {
        return sellPrice5;
    }

    public void setSellPrice5(Double sellPrice5) {
        this.sellPrice5 = sellPrice5;
    }

    public Integer getSellVolume5() {
        return sellVolume5;
    }

    public void setSellVolume5(Integer sellVolume5) {
        this.sellVolume5 = sellVolume5;
    }

    public abstract LocalTime getTimestamp();

    public abstract void setTimestamp(LocalTime timestamp);
}
