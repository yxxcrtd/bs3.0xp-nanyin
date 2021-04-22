package com.joyintech.tams.exchange.common.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * create By MAXX on 2020/10/15
 */
public class MinTrdUnitLimit implements Serializable {
    private String tradeMarket;//交易场所
    private String tradePlat;//交易平台
    private String assetType;//资产一级分类
    private String assetType1;//资产二级分类
    private String assetType2;//资产三级分类
    private String ps;//交易方向
    private String marketType;//上市板
    private Integer minStep = 0;//最小交易单元
    private String minStepUnit;//最小交易单元单位
    private BigDecimal minAmt = new BigDecimal("0.00");//最低交易金额
    private BigDecimal maxAmt = new BigDecimal("0.00");//最大交易金额
    private Integer minNum = 0;//最低交易数量
    private String minNumUnit;//最低交易数量单位
    private Integer maxNum = 0;//最大交易数量
    private String maxNumUnit;//最大交易数量单位
    private Integer minNumMarket = 0;//最低交易数量（市价）
    private String minNumMarketUnit;//最低交易数量单位（市价）
    private Integer maxNumMarket = 0;//最大交易数量（市价）
    private String maxNumMarketUnit;//最大交易数量单位（市价）
    private Date effDate;//生效日
    private String market;

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

    public String getPs() {
        return ps;
    }

    public void setPs(String ps) {
        this.ps = ps;
    }

    public String getMarketType() {
        return marketType;
    }

    public void setMarketType(String marketType) {
        this.marketType = marketType;
    }

    public Integer getMinStep() {
        return minStep;
    }

    public void setMinStep(Integer minStep) {
        this.minStep = minStep;
    }

    public String getMinStepUnit() {
        return minStepUnit;
    }

    public void setMinStepUnit(String minStepUnit) {
        this.minStepUnit = minStepUnit;
    }

    public BigDecimal getMinAmt() {
        return minAmt;
    }

    public void setMinAmt(BigDecimal minAmt) {
        this.minAmt = minAmt;
    }

    public BigDecimal getMaxAmt() {
        return maxAmt;
    }

    public void setMaxAmt(BigDecimal maxAmt) {
        this.maxAmt = maxAmt;
    }

    public Integer getMinNum() {
        return minNum;
    }

    public void setMinNum(Integer minNum) {
        this.minNum = minNum;
    }

    public String getMinNumUnit() {
        return minNumUnit;
    }

    public void setMinNumUnit(String minNumUnit) {
        this.minNumUnit = minNumUnit;
    }

    public Integer getMaxNum() {
        return maxNum;
    }

    public void setMaxNum(Integer maxNum) {
        this.maxNum = maxNum;
    }

    public String getMaxNumUnit() {
        return maxNumUnit;
    }

    public void setMaxNumUnit(String maxNumUnit) {
        this.maxNumUnit = maxNumUnit;
    }

    public Integer getMinNumMarket() {
        return minNumMarket;
    }

    public void setMinNumMarket(Integer minNumMarket) {
        this.minNumMarket = minNumMarket;
    }

    public String getMinNumMarketUnit() {
        return minNumMarketUnit;
    }

    public void setMinNumMarketUnit(String minNumMarketUnit) {
        this.minNumMarketUnit = minNumMarketUnit;
    }

    public Integer getMaxNumMarket() {
        return maxNumMarket;
    }

    public void setMaxNumMarket(Integer maxNumMarket) {
        this.maxNumMarket = maxNumMarket;
    }

    public String getMaxNumMarketUnit() {
        return maxNumMarketUnit;
    }

    public void setMaxNumMarketUnit(String maxNumMarketUnit) {
        this.maxNumMarketUnit = maxNumMarketUnit;
    }

    public Date getEffDate() {
        return effDate;
    }

    public void setEffDate(Date effDate) {
        this.effDate = effDate;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }
}
