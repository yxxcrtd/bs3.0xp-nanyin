package com.joyintech.tams.exchange.common.bean;

import org.springframework.data.annotation.Id;

import java.io.Serializable;

/**
 * 客户资金查询返回bean
 */
public class AccountFinance implements Serializable {
    private String accountId;
    /**
     * 客户代码
     */
    private String customerId;
    private String financeAccount;
    /**
     * 财富账户
     */
    private String fortuneId;
    /**
     * 机构编码
     */
    private String orgId;
    /**
     * 货币类型
     */
    private String moneyType;
    /**
     * 资金余额
     */
    private double financeBalance;
    /**
     * 资金可用金额
     */
    private double enableBalance;
    /**
     * 资产总值
     */
    private double totalAssets;
    /**
     * 资金资产 (保留)
     */
    private double financeAssets;
    /**
     * 市值（持有股票价值）(保留)
     */
    private double stkValue;
    /**
     * 融资总金额
     */
    private double fundLoan;
    /**
     * 冻结总金额
     */
    private double totalFrozenFinance;
    /**
     * 昨日余额
     */
    private double prevDayFinanceBalance;


    @Id
    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getFinanceAccount() {
        return financeAccount;
    }

    public void setFinanceAccount(String financeAccount) {
        this.financeAccount = financeAccount;
    }

    public String getFortuneId() {
        return fortuneId;
    }

    public void setFortuneId(String fortuneId) {
        this.fortuneId = fortuneId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getMoneyType() {
        return moneyType;
    }

    public void setMoneyType(String moneyType) {
        this.moneyType = moneyType;
    }

    public double getFinanceBalance() {
        return financeBalance;
    }

    public void setFinanceBalance(double financeBalance) {
        this.financeBalance = financeBalance;
    }

    public double getEnableBalance() {
        return enableBalance;
    }

    public void setEnableBalance(double enableBalance) {
        this.enableBalance = enableBalance;
    }

    public double getTotalAssets() {
        return totalAssets;
    }

    public void setTotalAssets(double totalAssets) {
        this.totalAssets = totalAssets;
    }

    public double getFinanceAssets() {
        return financeAssets;
    }

    public void setFinanceAssets(double financeAssets) {
        this.financeAssets = financeAssets;
    }

    public double getStkValue() {
        return stkValue;
    }

    public void setStkValue(double stkValue) {
        this.stkValue = stkValue;
    }

    public double getFundLoan() {
        return fundLoan;
    }

    public void setFundLoan(double fundLoan) {
        this.fundLoan = fundLoan;
    }

    public double getTotalFrozenFinance() {
        return totalFrozenFinance;
    }

    public void setTotalFrozenFinance(double totalFrozenFinance) {
        this.totalFrozenFinance = totalFrozenFinance;
    }

    public double getPrevDayFinanceBalance() {
        return prevDayFinanceBalance;
    }

    public void setPrevDayFinanceBalance(double prevDayFinanceBalance) {
        this.prevDayFinanceBalance = prevDayFinanceBalance;
    }
}
