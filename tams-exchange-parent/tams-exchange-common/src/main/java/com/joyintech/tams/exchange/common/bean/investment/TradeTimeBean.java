package com.joyintech.tams.exchange.common.bean.investment;

import java.io.Serializable;

/**
 * @Author: think
 * @Description: 交易时间段
 * @Date: Created in 13:47 2020/10/20
 */
public class TradeTimeBean implements Serializable {
    private String market;//交易市场
    private String tradeMarket;//交易场所
    private String tradePlat;//交易平台
    private String assetType;//资产一级分类
    private String assetType1;//资产二级分类
    private String assetType2;//资产三级分类
    private String declarationType;//申报类型
    private String earlyOpenTime;//早盘开市时间
    private String earlyCloseTime;//早盘收市时间
    private String noonOpenTime;//午盘开市时间
    private String noonCloseTime;//午盘收市时间
    private String nightOpenTime;//晚盘开市时间
    private String nightCloseTime;//晚盘收市时间

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public String getTradeMarket() {
        return tradeMarket;
    }

    public void setTradeMarket(String tradeMarket) {
        this.tradeMarket = tradeMarket;
    }

    public String getTradePlat() {
        return tradePlat;
    }

    public void setTradePlat(String tradePlat) {
        this.tradePlat = tradePlat;
    }

    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    public String getAssetType1() {
        return assetType1;
    }

    public void setAssetType1(String assetType1) {
        this.assetType1 = assetType1;
    }

    public String getAssetType2() {
        return assetType2;
    }

    public void setAssetType2(String assetType2) {
        this.assetType2 = assetType2;
    }

    public String getDeclarationType() {
        return declarationType;
    }

    public void setDeclarationType(String declarationType) {
        this.declarationType = declarationType;
    }

    public String getEarlyOpenTime() {
        return earlyOpenTime;
    }

    public void setEarlyOpenTime(String earlyOpenTime) {
        this.earlyOpenTime = earlyOpenTime;
    }

    public String getEarlyCloseTime() {
        return earlyCloseTime;
    }

    public void setEarlyCloseTime(String earlyCloseTime) {
        this.earlyCloseTime = earlyCloseTime;
    }

    public String getNoonOpenTime() {
        return noonOpenTime;
    }

    public void setNoonOpenTime(String noonOpenTime) {
        this.noonOpenTime = noonOpenTime;
    }

    public String getNoonCloseTime() {
        return noonCloseTime;
    }

    public void setNoonCloseTime(String noonCloseTime) {
        this.noonCloseTime = noonCloseTime;
    }

    public String getNightOpenTime() {
        return nightOpenTime;
    }

    public void setNightOpenTime(String nightOpenTime) {
        this.nightOpenTime = nightOpenTime;
    }

    public String getNightCloseTime() {
        return nightCloseTime;
    }

    public void setNightCloseTime(String nightCloseTime) {
        this.nightCloseTime = nightCloseTime;
    }
}
