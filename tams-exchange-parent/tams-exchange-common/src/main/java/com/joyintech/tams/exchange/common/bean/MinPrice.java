package com.joyintech.tams.exchange.common.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * create By MAXX on 2020/10/15
 */
public class MinPrice implements Serializable {
    private String tradeMarket;//交易场所
    private String tradePlat;//交易平台
    private String assetType;//资产一级分类
    private String assetType1;//资产二级分类
    private String assetType2;//资产三级分类
    private String isGrade;//是否分档
    private String gradeCondition;//分档条件
    private Integer gradeAmt = 0;//分档金额
    private BigDecimal minPrice = new BigDecimal("0.00");//最小价差
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

    public String getIsGrade() {
        return isGrade;
    }

    public void setIsGrade(String isGrade) {
        this.isGrade = isGrade;
    }

    public String getGradeCondition() {
        return gradeCondition;
    }

    public void setGradeCondition(String gradeCondition) {
        this.gradeCondition = gradeCondition;
    }

    public Integer getGradeAmt() {
        return gradeAmt;
    }

    public void setGradeAmt(Integer gradeAmt) {
        this.gradeAmt = gradeAmt;
    }

    public BigDecimal getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
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
