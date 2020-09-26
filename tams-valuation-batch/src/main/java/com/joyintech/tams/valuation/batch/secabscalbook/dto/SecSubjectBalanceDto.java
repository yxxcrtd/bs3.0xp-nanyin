/*
 * 文件名：SecSubjectBalanceDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：daiyue
 * 修改时间：2018年9月18日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.batch.secabscalbook.dto;

import java.math.BigDecimal;

public class SecSubjectBalanceDto {

    /**
     * 券面面值
     */
    private BigDecimal FaceValue;
    
    
    /**
     * 成本
     */
    private BigDecimal cb;
    
    
    /**
     * 数量
     */
    private BigDecimal num;
    
    
    /**
     * 应计利息
     */
    private BigDecimal interest;
    
    
    /**
     * 折溢价
     */
    private BigDecimal spilledPrice;

    /**
     * 成本
     */
    private BigDecimal cbT1;
    
    
    /**
     * 数量
     */
    private BigDecimal numT1;
    
    
    /**
     * 应计利息
     */
    private BigDecimal interestT1;
    
    
    /**
     * 折溢价
     */
    private BigDecimal spilledPriceT1;

    /**
     * 获取 faceValue
     * @return faceValue.
     */
    public BigDecimal getFaceValue() {
        return FaceValue;
    }


    /**
     * 设置 faceValue
     * @param faceValue faceValue
     */
    public void setFaceValue(BigDecimal faceValue) {
        FaceValue = faceValue;
    }


    /**
     * 获取 cb
     * @return cb.
     */
    public BigDecimal getCb() {
        return cb;
    }


    /**
     * 设置 cb
     * @param cb cb
     */
    public void setCb(BigDecimal cb) {
        this.cb = cb;
    }


    /**
     * 获取 num
     * @return num.
     */
    public BigDecimal getNum() {
        return num;
    }


    /**
     * 设置 num
     * @param num num
     */
    public void setNum(BigDecimal num) {
        this.num = num;
    }


    /**
     * 获取 interest
     * @return interest.
     */
    public BigDecimal getInterest() {
        return interest;
    }


    /**
     * 设置 interest
     * @param interest interest
     */
    public void setInterest(BigDecimal interest) {
        this.interest = interest;
    }


    /**
     * 获取 spilledPrice
     * @return spilledPrice.
     */
    public BigDecimal getSpilledPrice() {
        return spilledPrice;
    }


    /**
     * 设置 spilledPrice
     * @param spilledPrice spilledPrice
     */
    public void setSpilledPrice(BigDecimal spilledPrice) {
        this.spilledPrice = spilledPrice;
    }


    /**
     * 获取 cbT1
     * @return cbT1.
     */
    public BigDecimal getCbT1() {
        return cbT1;
    }


    /**
     * 设置 cbT1
     * @param cbT1 cbT1
     */
    public void setCbT1(BigDecimal cbT1) {
        this.cbT1 = cbT1;
    }


    /**
     * 获取 numT1
     * @return numT1.
     */
    public BigDecimal getNumT1() {
        return numT1;
    }


    /**
     * 设置 numT1
     * @param numT1 numT1
     */
    public void setNumT1(BigDecimal numT1) {
        this.numT1 = numT1;
    }


    /**
     * 获取 interestT1
     * @return interestT1.
     */
    public BigDecimal getInterestT1() {
        return interestT1;
    }


    /**
     * 设置 interestT1
     * @param interestT1 interestT1
     */
    public void setInterestT1(BigDecimal interestT1) {
        this.interestT1 = interestT1;
    }


    /**
     * 获取 spilledPriceT1
     * @return spilledPriceT1.
     */
    public BigDecimal getSpilledPriceT1() {
        return spilledPriceT1;
    }


    /**
     * 设置 spilledPriceT1
     * @param spilledPriceT1 spilledPriceT1
     */
    public void setSpilledPriceT1(BigDecimal spilledPriceT1) {
        this.spilledPriceT1 = spilledPriceT1;
    }
    
}
