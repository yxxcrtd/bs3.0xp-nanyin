/*
 * 文件名：FlowForecastDealAmontDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：chendijun
 * 修改时间：2019年8月7日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.product.forecast.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author chendijun
 * @version 1.0 2019年8月7日
 * @see FinFlowForecastDealAmontDto
 * @since 1.0
 */
public class FinFlowForecastDealAmontDto extends BaseEntity{
    
    /**
     * 名称
     */
    
    private String targetName;
    
    /**
     * 指标种类
     */
    private String targetType;
    

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * 指标值1的申购金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal targetTimeoneSG;
    
    /**
     * 指标值1的赎回金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal targetTimeoneSH;
    
    /**
     * 指标值1的净申购金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal targetTimeone;
    
    /**
     * 指标值2的申购金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal targetTimetwoSG;
    
    /**
     * 指标值2的赎回金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal targetTimetwoSH;
    
    /**
     * 指标值2的净申购金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal targetTimetwo;
    
    /**
     * 指标值3的申购金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal targetTimethreeSG;
    
    /**
     * 指标值3的赎回金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal targetTimethreeSH;
    
    /**
     * 指标值3的金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal targetTimethree;
    
    /**
     * 指标值4的申购金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal targetTimefourSG;
    
    /**
     * 指标值4的赎回金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal targetTimefourSH;
    
    /**
     * 指标值4的金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal targetTimefour;
    
    /**
     * 指标值5的申购金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal targetTimefiveSG;
    
    /**
     * 指标值5的赎回金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal targetTimefiveSH;
    
    /**
     * 指标值5金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal targetTimefive;
    
    /**
     * 指标值6的申购金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal targetTimesixSG;
    
    /**
     * 指标值6的赎回金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal targetTimesixSH;
    
    /**
     * 指标值6的金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal targetTimesix;

    
    /**
     * 获取 targetName
     * @return targetName.
     */
    public String getTargetName() {
        return targetName;
    }

    
    /**
     * 设置 targetName
     * @param targetName targetName
     */
    public void setTargetName(String targetName) {
        this.targetName = targetName;
    } 
    
    /**
     * 获取 targetTimeoneSG
     * @return targetTimeoneSG.
     */
    public BigDecimal getTargetTimeoneSG() {
        return targetTimeoneSG;
    }

    
    /**
     * 设置 targetTimeoneSG
     * @param targetTimeoneSG targetTimeoneSG
     */
    public void setTargetTimeoneSG(BigDecimal targetTimeoneSG) {
        this.targetTimeoneSG = targetTimeoneSG;
    }

    
    /**
     * 获取 targetTimeoneSH
     * @return targetTimeoneSH.
     */
    public BigDecimal getTargetTimeoneSH() {
        return targetTimeoneSH;
    }

    
    /**
     * 设置 targetTimeoneSH
     * @param targetTimeoneSH targetTimeoneSH
     */
    public void setTargetTimeoneSH(BigDecimal targetTimeoneSH) {
        this.targetTimeoneSH = targetTimeoneSH;
    }

    
    /**
     * 获取 targetTimetwoSG
     * @return targetTimetwoSG.
     */
    public BigDecimal getTargetTimetwoSG() {
        return targetTimetwoSG;
    }

    
    /**
     * 设置 targetTimetwoSG
     * @param targetTimetwoSG targetTimetwoSG
     */
    public void setTargetTimetwoSG(BigDecimal targetTimetwoSG) {
        this.targetTimetwoSG = targetTimetwoSG;
    }

    
    /**
     * 获取 targetTimetwoSH
     * @return targetTimetwoSH.
     */
    public BigDecimal getTargetTimetwoSH() {
        return targetTimetwoSH;
    }

    
    /**
     * 设置 targetTimetwoSH
     * @param targetTimetwoSH targetTimetwoSH
     */
    public void setTargetTimetwoSH(BigDecimal targetTimetwoSH) {
        this.targetTimetwoSH = targetTimetwoSH;
    }

    /**
     * 获取 targetTimethreeSG
     * @return targetTimethreeSG.
     */
    public BigDecimal getTargetTimethreeSG() {
        return targetTimethreeSG;
    }

    
    /**
     * 设置 targetTimethreeSG
     * @param targetTimethreeSG targetTimethreeSG
     */
    public void setTargetTimethreeSG(BigDecimal targetTimethreeSG) {
        this.targetTimethreeSG = targetTimethreeSG;
    }

    
    /**
     * 获取 targetTimethreeSH
     * @return targetTimethreeSH.
     */
    public BigDecimal getTargetTimethreeSH() {
        return targetTimethreeSH;
    }

    
    /**
     * 设置 targetTimethreeSH
     * @param targetTimethreeSH targetTimethreeSH
     */
    public void setTargetTimethreeSH(BigDecimal targetTimethreeSH) {
        this.targetTimethreeSH = targetTimethreeSH;
    }
    
    /**
     * 获取 targetTimefourSG
     * @return targetTimefourSG.
     */
    public BigDecimal getTargetTimefourSG() {
        return targetTimefourSG;
    }

    
    /**
     * 设置 targetTimefourSG
     * @param targetTimefourSG targetTimefourSG
     */
    public void setTargetTimefourSG(BigDecimal targetTimefourSG) {
        this.targetTimefourSG = targetTimefourSG;
    }

    
    /**
     * 获取 targetTimefourSH
     * @return targetTimefourSH.
     */
    public BigDecimal getTargetTimefourSH() {
        return targetTimefourSH;
    }

    
    /**
     * 设置 targetTimefourSH
     * @param targetTimefourSH targetTimefourSH
     */
    public void setTargetTimefourSH(BigDecimal targetTimefourSH) {
        this.targetTimefourSH = targetTimefourSH;
    }
    
    /**
     * 获取 targetTimefiveSG
     * @return targetTimefiveSG.
     */
    public BigDecimal getTargetTimefiveSG() {
        return targetTimefiveSG;
    }

    
    /**
     * 设置 targetTimefiveSG
     * @param targetTimefiveSG targetTimefiveSG
     */
    public void setTargetTimefiveSG(BigDecimal targetTimefiveSG) {
        this.targetTimefiveSG = targetTimefiveSG;
    }

    
    /**
     * 获取 targetTimefiveSH
     * @return targetTimefiveSH.
     */
    public BigDecimal getTargetTimefiveSH() {
        return targetTimefiveSH;
    }

    
    /**
     * 设置 targetTimefiveSH
     * @param targetTimefiveSH targetTimefiveSH
     */
    public void setTargetTimefiveSH(BigDecimal targetTimefiveSH) {
        this.targetTimefiveSH = targetTimefiveSH;
    }
    
    /**
     * 获取 targetTimesixSG
     * @return targetTimesixSG.
     */
    public BigDecimal getTargetTimesixSG() {
        return targetTimesixSG;
    }

    
    /**
     * 设置 targetTimesixSG
     * @param targetTimesixSG targetTimesixSG
     */
    public void setTargetTimesixSG(BigDecimal targetTimesixSG) {
        this.targetTimesixSG = targetTimesixSG;
    }

    
    /**
     * 获取 targetTimesixSH
     * @return targetTimesixSH.
     */
    public BigDecimal getTargetTimesixSH() {
        return targetTimesixSH;
    }

    
    /**
     * 设置 targetTimesixSH
     * @param targetTimesixSH targetTimesixSH
     */
    public void setTargetTimesixSH(BigDecimal targetTimesixSH) {
        this.targetTimesixSH = targetTimesixSH;
    }

    /**
     * 获取 targetType
     * @return targetType.
     */
    public String getTargetType() {
        return targetType;
    }


    /**
     * 设置 targetType
     * @param targetType targetType
     */
    public void setTargetType(String targetType) {
        this.targetType = targetType;
    }


    
    /**
     * 获取 targetTimeone
     * @return targetTimeone.
     */
    public BigDecimal getTargetTimeone() {
        return targetTimeone;
    }


    
    /**
     * 设置 targetTimeone
     * @param targetTimeone targetTimeone
     */
    public void setTargetTimeone(BigDecimal targetTimeone) {
        this.targetTimeone = targetTimeone;
    }


    
    /**
     * 获取 targetTimetwo
     * @return targetTimetwo.
     */
    public BigDecimal getTargetTimetwo() {
        return targetTimetwo;
    }


    
    /**
     * 设置 targetTimetwo
     * @param targetTimetwo targetTimetwo
     */
    public void setTargetTimetwo(BigDecimal targetTimetwo) {
        this.targetTimetwo = targetTimetwo;
    }


    
    /**
     * 获取 targetTimethree
     * @return targetTimethree.
     */
    public BigDecimal getTargetTimethree() {
        return targetTimethree;
    }


    
    /**
     * 设置 targetTimethree
     * @param targetTimethree targetTimethree
     */
    public void setTargetTimethree(BigDecimal targetTimethree) {
        this.targetTimethree = targetTimethree;
    }


    
    /**
     * 获取 targetTimefour
     * @return targetTimefour.
     */
    public BigDecimal getTargetTimefour() {
        return targetTimefour;
    }


    
    /**
     * 设置 targetTimefour
     * @param targetTimefour targetTimefour
     */
    public void setTargetTimefour(BigDecimal targetTimefour) {
        this.targetTimefour = targetTimefour;
    }


    
    /**
     * 获取 targetTimefive
     * @return targetTimefive.
     */
    public BigDecimal getTargetTimefive() {
        return targetTimefive;
    }


    
    /**
     * 设置 targetTimefive
     * @param targetTimefive targetTimefive
     */
    public void setTargetTimefive(BigDecimal targetTimefive) {
        this.targetTimefive = targetTimefive;
    }


    
    /**
     * 获取 targetTimesix
     * @return targetTimesix.
     */
    public BigDecimal getTargetTimesix() {
        return targetTimesix;
    }


    
    /**
     * 设置 targetTimesix
     * @param targetTimesix targetTimesix
     */
    public void setTargetTimesix(BigDecimal targetTimesix) {
        this.targetTimesix = targetTimesix;
    }
    
}
