package com.joyintech.tams.invest.portfolio.ptlfundallocation.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 资金划拨导入dto
 * @author liuwei
 * @version 1.0 2021年2月20日
 * @see PtlFundAllocationImportDto
 * @since 1.0
 */
public class PtlFundAllocationImportDto {

    /**
     * 划款类型
     */
    private String payType;

    /**
     * 收付款账户
     */
    private String counterAcctNo;

    /**
     * 收付款账户名称
     */
    private String counterAcctNoName;

    /**
     * 托管户
     */
    private String myAcctNo;

    /**
     * 托管户名称
     */
    private String myAcctNoName;

    /**
     * 组合代码
     */
    private String portfolioId;

    /**
     * 组合代码
     */
    private String portfolioName;

    /**
     * 划拨金额
     */
    private BigDecimal settleAmt;

    /**
     * 币种
     */
    private String ccy;

    /**
     * 导入日期
     */
    private Date date;

    /**
     * 托管户余额
     */
    private BigDecimal amount;

    /**
     * 获取 划款类型
     *
     * @return payType 划款类型
     */
    public String getPayType() {
        return this.payType;
    }

    /**
     * 设置 划款类型
     *
     * @param payType 划款类型
     */
    public void setPayType(String payType) {
        this.payType = payType;
    }

    /**
     * 获取 收付款账户
     *
     * @return counterAcctNo 收付款账户
     */
    public String getCounterAcctNo() {
        return this.counterAcctNo;
    }

    /**
     * 设置 收付款账户
     *
     * @param counterAcctNo 收付款账户
     */
    public void setCounterAcctNo(String counterAcctNo) {
        this.counterAcctNo = counterAcctNo;
    }

    /**
     * 获取 组合代码
     *
     * @return portfolioId 组合代码
     */
    public String getPortfolioId() {
        return this.portfolioId;
    }

    /**
     * 设置 组合代码
     *
     * @param portfolioId 组合代码
     */
    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }

    /**
     * 获取 划拨金额
     *
     * @return settleAmt 划拨金额
     */
    public BigDecimal getSettleAmt() {
        return this.settleAmt;
    }

    /**
     * 设置 划拨金额
     *
     * @param settleAmt 划拨金额
     */
    public void setSettleAmt(BigDecimal settleAmt) {
        this.settleAmt = settleAmt;
    }

    /**
     * 获取 币种
     *
     * @return ccy 币种
     */
    public String getCcy() {
        return this.ccy;
    }

    /**
     * 设置 币种
     *
     * @param ccy 币种
     */
    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    /**
     * 获取 组合代码
     *
     * @return portfolioName 组合代码
     */
    public String getPortfolioName() {
        return this.portfolioName;
    }

    /**
     * 设置 组合代码
     *
     * @param portfolioName 组合代码
     */
    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
    }

    /**
     * 获取 托管户
     *
     * @return myAcctNo 托管户
     */
    public String getMyAcctNo() {
        return this.myAcctNo;
    }

    /**
     * 设置 托管户
     *
     * @param myAcctNo 托管户
     */
    public void setMyAcctNo(String myAcctNo) {
        this.myAcctNo = myAcctNo;
    }

    /**
     * 获取 导入日期
     *
     * @return date 导入日期
     */
    public Date getDate() {
        return this.date;
    }

    /**
     * 设置 导入日期
     *
     * @param date 导入日期
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * 获取 托管户名称
     *
     * @return myAcctNoName 托管户名称
     */
    public String getMyAcctNoName() {
        return this.myAcctNoName;
    }

    /**
     * 设置 托管户名称
     *
     * @param myAcctNoName 托管户名称
     */
    public void setMyAcctNoName(String myAcctNoName) {
        this.myAcctNoName = myAcctNoName;
    }

    /**
     * 获取 收付款账户名称
     *
     * @return counterAcctNoName 收付款账户名称
     */
    public String getCounterAcctNoName() {
        return this.counterAcctNoName;
    }

    /**
     * 设置 收付款账户名称
     *
     * @param counterAcctNoName 收付款账户名称
     */
    public void setCounterAcctNoName(String counterAcctNoName) {
        this.counterAcctNoName = counterAcctNoName;
    }

    /**
     * 获取 托管户余额
     *
     * @return amount 托管户余额
     */
    public BigDecimal getAmount() {
        return this.amount;
    }

    /**
     * 设置 托管户余额
     *
     * @param amount 托管户余额
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
