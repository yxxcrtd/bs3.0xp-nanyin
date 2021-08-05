/*
 * 文件名：PtlFundAllocationDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2019年1月29日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.portfolio.ptlfundallocation.dto;

import java.math.BigDecimal;

/**
 * 资金划拨
 * @author fanshuanghu
 * @version 1.0 2019年1月29日
 * @see PtlFundAllocation001Dto
 * @since 1.0
 */
public class PtlFundAllocation001Dto {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -8529608709130282761L;
    /**
     * 组合代码
     */
    private String portfolioId;
    /**
     * 账户编号
     */
    private String acctNo;
    /**
     * 账户名称
     */
    private String acctName;
    /**
     * 账户余额
     */
    private BigDecimal amount;
    /**
     * 划款金额
     */
    private BigDecimal settleAmt;
    /**
     * 操作
     */
    private String operation;

    public String getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }

    public String getAcctNo() {
        return acctNo;
    }

    public void setAcctNo(String acctNo) {
        this.acctNo = acctNo;
    }

    public String getAcctName() {
        return acctName;
    }

    public void setAcctName(String acctName) {
        this.acctName = acctName;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getSettleAmt() {
        return settleAmt;
    }

    public void setSettleAmt(BigDecimal settleAmt) {
        this.settleAmt = settleAmt;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}
