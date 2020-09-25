/*
 * 文件名：RepFinProductBalanceDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2019年3月1日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.report.balance.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.framework.json.BigDecimalSerializer;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2019年3月1日
 * @see RepFinProductBalanceDto
 * @since 1.0
 */
public class RepFinProductBalanceDto extends FinProduct{

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * 组合代码
     */
    private String portfolioId;


    /**
     * 组合名称
     */
    private String portfolioName;
    
    /**
     * 处理模式，“普通”、“滚动”、“日添利”、“智能日添利”等
     */
    private String dealMode;
    
    /**
     * 销售部门，零售产品管理部、金融同业部、对公产品和现金管理部
     */
    private String saleDepartment;
    
    /**
     * 产品款型
     */
    private String productType;
    
    /**
     * 交易金额(认购金额)
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tradeAmt;
    
    /**
     * 当前本金(剩余金额)
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdyPrinAmt;
    
    /**
     * 每日估值，净值、万份收益等(预期收益率(%)/净值)
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal valuation;
    
    /**
     * 托管费率(%)
     */
    private BigDecimal clientYield;
    
    /**
     * 销售费率(%)
     */
    private BigDecimal saleYield;
    
    /**
     * 管理费率(%)
     */
    private BigDecimal managerYield;
    
    /**
     * 其他费率(%)
     */
    private BigDecimal otherYield;

    
    /**
     * 获取 portfolioId
     * @return portfolioId.
     */
    public String getPortfolioId() {
        return portfolioId;
    }

    
    /**
     * 设置 portfolioId
     * @param portfolioId portfolioId
     */
    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }

    
    /**
     * 获取 portfolioName
     * @return portfolioName.
     */
    public String getPortfolioName() {
        return portfolioName;
    }

    
    /**
     * 设置 portfolioName
     * @param portfolioName portfolioName
     */
    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
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
     * 获取 saleDepartment
     * @return saleDepartment.
     */
    public String getSaleDepartment() {
        return saleDepartment;
    }

    
    /**
     * 设置 saleDepartment
     * @param saleDepartment saleDepartment
     */
    public void setSaleDepartment(String saleDepartment) {
        this.saleDepartment = saleDepartment;
    }

    
    /**
     * 获取 productType
     * @return productType.
     */
    public String getProductType() {
        return productType;
    }

    
    /**
     * 设置 productType
     * @param productType productType
     */
    public void setProductType(String productType) {
        this.productType = productType;
    }

    
    /**
     * 获取 tradeAmt
     * @return tradeAmt.
     */
    public BigDecimal getTradeAmt() {
        return tradeAmt;
    }

    
    /**
     * 设置 tradeAmt
     * @param tradeAmt tradeAmt
     */
    public void setTradeAmt(BigDecimal tradeAmt) {
        this.tradeAmt = tradeAmt;
    }

    
    /**
     * 获取 tdyPrinAmt
     * @return tdyPrinAmt.
     */
    public BigDecimal getTdyPrinAmt() {
        return tdyPrinAmt;
    }

    
    /**
     * 设置 tdyPrinAmt
     * @param tdyPrinAmt tdyPrinAmt
     */
    public void setTdyPrinAmt(BigDecimal tdyPrinAmt) {
        this.tdyPrinAmt = tdyPrinAmt;
    }

    
    /**
     * 获取 valuation
     * @return valuation.
     */
    public BigDecimal getValuation() {
        return valuation;
    }

    
    /**
     * 设置 valuation
     * @param valuation valuation
     */
    public void setValuation(BigDecimal valuation) {
        this.valuation = valuation;
    }

    
    /**
     * 获取 clientYield
     * @return clientYield.
     */
    public BigDecimal getClientYield() {
        return clientYield;
    }

    
    /**
     * 设置 clientYield
     * @param clientYield clientYield
     */
    public void setClientYield(BigDecimal clientYield) {
        this.clientYield = clientYield;
    }

    
    /**
     * 获取 saleYield
     * @return saleYield.
     */
    public BigDecimal getSaleYield() {
        return saleYield;
    }

    
    /**
     * 设置 saleYield
     * @param saleYield saleYield
     */
    public void setSaleYield(BigDecimal saleYield) {
        this.saleYield = saleYield;
    }

    
    /**
     * 获取 managerYield
     * @return managerYield.
     */
    public BigDecimal getManagerYield() {
        return managerYield;
    }

    
    /**
     * 设置 managerYield
     * @param managerYield managerYield
     */
    public void setManagerYield(BigDecimal managerYield) {
        this.managerYield = managerYield;
    }

    
    /**
     * 获取 otherYield
     * @return otherYield.
     */
    public BigDecimal getOtherYield() {
        return otherYield;
    }

    
    /**
     * 设置 otherYield
     * @param otherYield otherYield
     */
    public void setOtherYield(BigDecimal otherYield) {
        this.otherYield = otherYield;
    }
    
    

}