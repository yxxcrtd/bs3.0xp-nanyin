package com.joyintech.tams.invest.entrust.etrexchangetrade.dto;

import com.joyintech.framework.base.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Classname InterfSysTrdConfigDto
 * @Description TODO
 * @author zhuhanlu 
 * @date 2020/10/13 11:38 
 * @version 1.0 
 */
public class MinPirceDto extends BaseEntity {


    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * @Description //交易场所
     */
    private String tradeMarket;

    /**
     * @Description //交易平台
     */
    private String tradePlat;

    /**
     * @Description //一级分类
     */
    private String assetType;

    /**
     * @Description //二级分类
     */
    private String assetType1;

    /**
     * @Description //三级分类
     */
    private String assetType2;


    /**
     * @Description //是否归档
     */
    private String isGrade;

    /**
     * @Description //归档条件
     */
    private String gradeCondition;

    /**
     * @Description //分档金额
     */
    private int gradeAmt;

    /**
     * @Description //最小价差
     */
    private BigDecimal minPrice;

    /**
     * @Description //生效日
     */
    private Date effDate;

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

    public int getGradeAmt() {
        return gradeAmt;
    }

    public void setGradeAmt(int gradeAmt) {
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

}
