package com.joyintech.tams.invest.batch.overduesecpositionforward.dto;

import java.math.BigDecimal;

public class SecManageAcctInfoDto {
    /**
     * 证券管理账户/通道代码
     */
    private String secAcctId;
    /**
     * 数量
     */
    private BigDecimal qty;

    /**
     * 分支序号
     */
    private Long branch;
    /**
     * 持仓总数量
     */
    private BigDecimal holdAmtTotal;

    /**
     * 金额
     */
    private BigDecimal amount;


    /**
     * 获取 数量
     *
     * @return qty 数量
     */
    public BigDecimal getQty() {
        return this.qty;
    }

    /**
     * 设置 数量
     *
     * @param qty 数量
     */
    public void setQty(BigDecimal qty) {
        this.qty = qty;
    }

    /**
     * 获取 分支序号
     *
     * @return branch 分支序号
     */
    public Long getBranch() {
        return this.branch;
    }

    /**
     * 设置 分支序号
     *
     * @param branch 分支序号
     */
    public void setBranch(Long branch) {
        this.branch = branch;
    }

    /**
     * 获取 持仓总数量
     *
     * @return holdAmtTotal 持仓总数量
     */
    public BigDecimal getHoldAmtTotal() {
        return this.holdAmtTotal;
    }

    /**
     * 设置 持仓总数量
     *
     * @param holdAmtTotal 持仓总数量
     */
    public void setHoldAmtTotal(BigDecimal holdAmtTotal) {
        this.holdAmtTotal = holdAmtTotal;
    }

    /**
     * 获取 金额
     *
     * @return amount 金额
     */
    public BigDecimal getAmount() {
        return this.amount;
    }

    /**
     * 设置 金额
     *
     * @param amount 金额
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * 获取 证券管理账户通道代码
     *
     * @return secAcctId 证券管理账户通道代码
     */
    public String getSecAcctId() {
        return this.secAcctId;
    }

    /**
     * 设置 证券管理账户通道代码
     *
     * @param secAcctId 证券管理账户通道代码
     */
    public void setSecAcctId(String secAcctId) {
        this.secAcctId = secAcctId;
    }
}
