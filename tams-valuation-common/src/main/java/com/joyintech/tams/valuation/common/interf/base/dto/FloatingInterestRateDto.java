/*
 * 文件名：NPrdDealForInterfRecProcessorResult.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：weipeng
 * 修改时间：2018年6月25日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.common.interf.base.dto;

import java.math.BigDecimal;

import com.joyintech.framework.base.BaseEntity;

/**
 * 浮动利率初始化 处理返回共通结果
 * 
 * @author wangwei1
 * @version 1.0 2018年7月9日
 * @see FloatingInterestRateDto
 * @since 1.0
 */
public class FloatingInterestRateDto extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 初始利率
     */
    private BigDecimal initRate;
    /**
     * 挂钩利率    
     */
    private BigDecimal LinkedInterestRate;
    /**
     * 系数 
     */
    private BigDecimal coefficient;
    /**
     * 利率上限   
     */
    private BigDecimal spreadMax;
    /**
     * 利率下限      SPREAD_MIN
     */ 
    private BigDecimal spreadMin;
    /**
     *  浮动利率编号 FloatingInterestRateNo
     */                    
    private String   floatingInterestRateNo;
    /**
     *  利差           SPREAD_RATE                       
     */  
    private BigDecimal spreadRate;
    
    /**
     * 获取 initRate初始利率
     * @return initRate.
     */
    public BigDecimal getInitRate() {
        return initRate;
    }
    
    /**
     * 设置 initRate初始利率
     * @param initRate initRate
     */
    public void setInitRate(BigDecimal initRate) {
        this.initRate = initRate;
    }
    
    /**
     * 获取 linkedInterestRate 挂钩利率    
     * @return linkedInterestRate.
     */
    public BigDecimal getLinkedInterestRate() {
        return LinkedInterestRate;
    }
    
    /**
     * 设置 linkedInterestRate 挂钩利率    
     * @param linkedInterestRate linkedInterestRate
     */
    public void setLinkedInterestRate(BigDecimal linkedInterestRate) {
        LinkedInterestRate = linkedInterestRate;
    }
    
    /**
     * 获取 coefficient系数
     * @return coefficient.
     */
    public BigDecimal getCoefficient() {
        return coefficient;
    }
    
    /**
     * 设置 coefficient系数
     * @param coefficient coefficient
     */
    public void setCoefficient(BigDecimal coefficient) {
        this.coefficient = coefficient;
    }
    
    /**
     * 获取 spreadMax利率上限 
     * @return spreadMax.
     */
    public BigDecimal getSpreadMax() {
        return spreadMax;
    }
    
    /**
     * 设置 spreadMax利率上限 
     * @param spreadMax spreadMax
     */
    public void setSpreadMax(BigDecimal spreadMax) {
        this.spreadMax = spreadMax;
    }
    
    /**
     * 获取 spreadMin利率下限
     * @return spreadMin.
     */
    public BigDecimal getSpreadMin() {
        return spreadMin;
    }
    
    /**
     * 设置 spreadMin利率下限
     * @param spreadMin spreadMin
     */
    public void setSpreadMin(BigDecimal spreadMin) {
        this.spreadMin = spreadMin;
    }
    
    /**
     * 获取 floatingInterestRateNo浮动利率编号
     * @return floatingInterestRateNo.
     */
    public String getFloatingInterestRateNo() {
        return floatingInterestRateNo;
    }
    
    /**
     * 设置 floatingInterestRateNo浮动利率编号
     * @param floatingInterestRateNo floatingInterestRateNo
     */
    public void setFloatingInterestRateNo(String floatingInterestRateNo) {
        this.floatingInterestRateNo = floatingInterestRateNo;
    }
    
    /**
     * 获取 spreadRate利差
     * @return spreadRate.
     */
    public BigDecimal getSpreadRate() {
        return spreadRate;
    }
    
    /**
     * 设置 spreadRate利差
     * @param spreadRate spreadRate
     */
    public void setSpreadRate(BigDecimal spreadRate) {
        this.spreadRate = spreadRate;
    }
    
    /**
     * 获取 serialversionuid
     * @return serialversionuid.
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    
    

    
}
