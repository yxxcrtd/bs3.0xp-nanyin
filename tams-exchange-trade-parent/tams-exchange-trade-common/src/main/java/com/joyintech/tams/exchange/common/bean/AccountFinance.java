package com.joyintech.tams.exchange.common.bean;

import org.springframework.data.annotation.Id;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 客户资金查询返回bean
 */
@ApiModel(value="客户资金查询返回bean",description="客户资金查询返回bean")
public class AccountFinance implements Serializable {
    private static final long serialVersionUID = -4501627645683023102L;
    @ApiModelProperty(name = "accountId", value = "账户Id")
    private String accountId;
    /**
     * 客户代码
     */
    @ApiModelProperty(name = "customerId", value = "客户代码")
    private String customerId;
    @ApiModelProperty(name = "financeAccount", value = "资金账号")
    private String financeAccount;
    /**
     * 财富账户
     */
    @ApiModelProperty(name = "fortuneId", value = "财富账户")
    private String fortuneId;
    /**
     * 机构编码
     */
    @ApiModelProperty(name = "orgId", value = "机构编码")
    private String orgId;
    /**
     * 货币类型
     */
    @ApiModelProperty(name = "moneyType", value = "货币类型")
    private String moneyType;
    /**
     * 资金余额
     */
    @ApiModelProperty(name = "financeBalance", value = "资金余额")
    private double financeBalance;
    /**
     * 资金可用金额
     */
    @ApiModelProperty(name = "enableBalance", value = "资金可用金额")
    private double enableBalance;
    /**
     * 资产总值
     */
    @ApiModelProperty(name = "totalAssets", value = "资产总值")
    private double totalAssets;
    /**
     * 资金资产 (保留)
     */
    @ApiModelProperty(name = "financeAssets", value = "资金资产 (保留)")
    private double financeAssets;
    /**
     * 市值（持有股票价值）(保留)
     */
    @ApiModelProperty(name = "stkValue", value = "市值（持有股票价值）(保留)")
    private double stkValue;
    /**
     * 融资总金额
     */
    @ApiModelProperty(name = "fundLoan", value = "融资总金额")
    private double fundLoan;
    /**
     * 冻结总金额
     */
    @ApiModelProperty(name = "totalFrozenFinance", value = "冻结总金额")
    private double totalFrozenFinance;
    /**
     * 昨日余额
     */
    @ApiModelProperty(name = "prevDayFinanceBalance", value = "昨日余额")
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
