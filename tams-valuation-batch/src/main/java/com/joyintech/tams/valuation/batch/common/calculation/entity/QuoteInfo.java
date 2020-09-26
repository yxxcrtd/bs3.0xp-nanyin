/*
 * 文件名：QuoteInfo.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：王波
 * 修改时间：2018年6月1日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.common.calculation.entity;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;

/**
 * 报价信息
 * 
 * @author 王波
 * @version 1.0 2018年6月1日
 * @see QuoteInfo
 * @since 1.0
 */
public class QuoteInfo {

    /**
     * 生效日
     */
    private Date issueDate;

    /**
     * 利率
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal interest;

    /**
     * 计算基础
     */
    private String accountBasis;

    
    /**
     * 获取 生效日
     * @return 生效日
     */
    public Date getIssueDate() {
        return issueDate;
    }

    
    /**
     * 设置 生效日
     * @param issueDate 生效日
     */
    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    
    /**
     * 获取 利率
     * @return 利率.
     */
    public BigDecimal getInterest() {
        return interest;
    }

    
    /**
     * 设置 利率
     * @param interest 利率
     */
    public void setInterest(BigDecimal interest) {
        this.interest = interest;
    }

    
    /**
     * 获取 计算基础
     * @return 计算基础.
     */
    public String getAccountBasis() {
        return accountBasis;
    }

    
    /**
     * 设置 计算基础
     * @param accountBasis 计算基础
     */
    public void setAccountBasis(String accountBasis) {
        this.accountBasis = accountBasis;
    }

}
