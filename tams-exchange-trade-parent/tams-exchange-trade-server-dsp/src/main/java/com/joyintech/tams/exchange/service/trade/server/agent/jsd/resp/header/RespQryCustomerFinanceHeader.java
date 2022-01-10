package com.joyintech.tams.exchange.service.trade.server.agent.jsd.resp.header;

/**
 * 查询资金余额和可用资金余额返回头-9
 *
 * @author lifangpeng
 * @date 2021/11/8
 */
public class RespQryCustomerFinanceHeader implements IRespHeader {
    /**
     * 资金余额
     */
    private double financeBalance;
    /**
     * 可用资金
     */
    private double expendableFund;
    /**
     * 买入未交收金额
     */
    private double outPurchaseAmount;
    /**
     * 卖出未交收金额
     */
    private double soldUnpaidAmount;
    /**
     * 委托未成交冻结金额
     */
    private double unTradeFrozenAmount;
    /**
     * 货币名称
     */
    private String currencyName;
    /**
     * 异常冻结资金
     */
    private double abnormalFrozenFunds;
    /**
     * 可融资金
     */
    private double butFinancing;
    /**
     * 可还资金
     */
    private double butAlsoMoney;
    /**
     * 保证金可用额度
     */
    private double marginAvailability;
    /**
     * 币种代码
     */
    private char currencyCode;
    /**
     * 资金账号（第三方柜台）
     */
    private String busiAcctNo;
    /**
     * 授信额度
     */
    private double lineCredit;
    /**
     * 剩余授信额度
     */
    private double residueLineCredit;
    /**
     * 担保证券市值
     */
    private double marketValue;
    /**
     * 可取资金
     */
    private double goodMoney;
    /**
     * 正常查询－资产总额，查融资余额－负债总额
     */
    private double totalAmount;
    /**
     * 维持担保比例
     */
    private double guaranteeRatio;
    /**
     * 保证金可用余额
     */
    private double avaBalance;
    /**
     * 可还款金额
     */
    private double refundableAmount;
    /**
     * 港股可用资金
     */
    private double HKFundsAvailable;

    @Override
    public int getResponseCount() {
        return 0;
    }

    public double getFinanceBalance() {
        return financeBalance;
    }

    public void setFinanceBalance(double financeBalance) {
        this.financeBalance = financeBalance;
    }

    public double getExpendableFund() {
        return expendableFund;
    }

    public void setExpendableFund(double expendableFund) {
        this.expendableFund = expendableFund;
    }

    public double getOutPurchaseAmount() {
        return outPurchaseAmount;
    }

    public void setOutPurchaseAmount(double outPurchaseAmount) {
        this.outPurchaseAmount = outPurchaseAmount;
    }

    public double getSoldUnpaidAmount() {
        return soldUnpaidAmount;
    }

    public void setSoldUnpaidAmount(double soldUnpaidAmount) {
        this.soldUnpaidAmount = soldUnpaidAmount;
    }

    public double getUnTradeFrozenAmount() {
        return unTradeFrozenAmount;
    }

    public void setUnTradeFrozenAmount(double unTradeFrozenAmount) {
        this.unTradeFrozenAmount = unTradeFrozenAmount;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public double getAbnormalFrozenFunds() {
        return abnormalFrozenFunds;
    }

    public void setAbnormalFrozenFunds(double abnormalFrozenFunds) {
        this.abnormalFrozenFunds = abnormalFrozenFunds;
    }

    public double getButFinancing() {
        return butFinancing;
    }

    public void setButFinancing(double butFinancing) {
        this.butFinancing = butFinancing;
    }

    public double getButAlsoMoney() {
        return butAlsoMoney;
    }

    public void setButAlsoMoney(double butAlsoMoney) {
        this.butAlsoMoney = butAlsoMoney;
    }

    public double getMarginAvailability() {
        return marginAvailability;
    }

    public void setMarginAvailability(double marginAvailability) {
        this.marginAvailability = marginAvailability;
    }

    public char getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(char currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getBusiAcctNo() {
        return busiAcctNo;
    }

    public void setBusiAcctNo(String busiAcctNo) {
        this.busiAcctNo = busiAcctNo;
    }

    public double getLineCredit() {
        return lineCredit;
    }

    public void setLineCredit(double lineCredit) {
        this.lineCredit = lineCredit;
    }

    public double getResidueLineCredit() {
        return residueLineCredit;
    }

    public void setResidueLineCredit(double residueLineCredit) {
        this.residueLineCredit = residueLineCredit;
    }

    public double getMarketValue() {
        return marketValue;
    }

    public void setMarketValue(double marketValue) {
        this.marketValue = marketValue;
    }

    public double getGoodMoney() {
        return goodMoney;
    }

    public void setGoodMoney(double goodMoney) {
        this.goodMoney = goodMoney;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getGuaranteeRatio() {
        return guaranteeRatio;
    }

    public void setGuaranteeRatio(double guaranteeRatio) {
        this.guaranteeRatio = guaranteeRatio;
    }

    public double getAvaBalance() {
        return avaBalance;
    }

    public void setAvaBalance(double avaBalance) {
        this.avaBalance = avaBalance;
    }

    public double getRefundableAmount() {
        return refundableAmount;
    }

    public void setRefundableAmount(double refundableAmount) {
        this.refundableAmount = refundableAmount;
    }

    public double getHKFundsAvailable() {
        return HKFundsAvailable;
    }

    public void setHKFundsAvailable(double HKFundsAvailable) {
        this.HKFundsAvailable = HKFundsAvailable;
    }
}
