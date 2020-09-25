/*
 * 文件名：ProductStructureStatistics001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：shengqiqiang
 * 修改时间：2019年10月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.poc.report.dto;

import java.math.BigDecimal;

import com.joyintech.framework.base.BaseEntity;

/**
 * 产品结构统计表Dto
 * 〈功能详细描述〉
 * @author shengqiqiang
 * @version 1.0 2019年10月17日
 * @see ProductStructureStatistics001Dto
 * @since 1.0
 */
public class ProductStructureStatistics001Dto extends BaseEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;
    
    private String profitType;/*收益方式*/
    
    private String operationType;/*运行方式*/
    
    private String dealMode;/*处理模式*/
    
    private BigDecimal purSpeed;/*确认天数*/
    
    private String investorType;/*客户类型*/
    
    private BigDecimal tradeCost;/*兑付本金*/
    
    private BigDecimal intAmt;/*兑付利息*/

    private BigDecimal  tradePrince;/*募集金额*/
    
    private String tarCustomerType;/*子产品客户类型*/
    
    
    
    /**
     * 获取 tarCustomerType
     * @return tarCustomerType.
     */
    public String getTarCustomerType() {
        return tarCustomerType;
    }


    
    /**
     * 设置 tarCustomerType
     * @param tarCustomerType tarCustomerType
     */
    public void setTarCustomerType(String tarCustomerType) {
        this.tarCustomerType = tarCustomerType;
    }


    /**
     * 获取 profitType
     * @return profitType.
     */
    public String getProfitType() {
        return profitType;
    }

    
    /**
     * 设置 profitType
     * @param profitType profitType
     */
    public void setProfitType(String profitType) {
        this.profitType = profitType;
    }

    
    /**
     * 获取 operationType
     * @return operationType.
     */
    public String getOperationType() {
        return operationType;
    }

    
    /**
     * 设置 operationType
     * @param operationType operationType
     */
    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    
    /**
     * 获取 dealMode
     * @return dealMode.
     */
    public String getDealMode() {
        return dealMode;
    }

    
    /**
     * 设置 dealMode
     * @param dealMode dealMode
     */
    public void setDealMode(String dealMode) {
        this.dealMode = dealMode;
    }

    
    /**
     * 获取 purSpeed
     * @return purSpeed.
     */
    public BigDecimal getPurSpeed() {
        return purSpeed;
    }

    
    /**
     * 设置 purSpeed
     * @param purSpeed purSpeed
     */
    public void setPurSpeed(BigDecimal purSpeed) {
        this.purSpeed = purSpeed;
    }

    
    /**
     * 获取 investorType
     * @return investorType.
     */
    public String getInvestorType() {
        return investorType;
    }

    
    /**
     * 设置 investorType
     * @param investorType investorType
     */
    public void setInvestorType(String investorType) {
        this.investorType = investorType;
    }

    
    /**
     * 获取 tradeCost
     * @return tradeCost.
     */
    public BigDecimal getTradeCost() {
        return tradeCost;
    }

    
    /**
     * 设置 tradeCost
     * @param tradeCost tradeCost
     */
    public void setTradeCost(BigDecimal tradeCost) {
        this.tradeCost = tradeCost;
    }

    
    /**
     * 获取 intAmt
     * @return intAmt.
     */
    public BigDecimal getIntAmt() {
        return intAmt;
    }

    
    /**
     * 设置 intAmt
     * @param intAmt intAmt
     */
    public void setIntAmt(BigDecimal intAmt) {
        this.intAmt = intAmt;
    }

    
    /**
     * 获取 serialversionuid
     * @return serialversionuid.
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }


    
    /**
     * 获取 tradePrince
     * @return tradePrince.
     */
    public BigDecimal getTradePrince() {
        return tradePrince;
    }


    
    /**
     * 设置 tradePrince
     * @param tradePrince tradePrince
     */
    public void setTradePrince(BigDecimal tradePrince) {
        this.tradePrince = tradePrince;
    }

  
    
    
    
}
