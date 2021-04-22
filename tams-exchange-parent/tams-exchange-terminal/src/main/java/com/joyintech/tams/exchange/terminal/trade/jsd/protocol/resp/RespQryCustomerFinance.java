package com.joyintech.tams.exchange.terminal.trade.jsd.protocol.resp;

//查询资金余额和可用资金余额返回头
public class RespQryCustomerFinance implements IRespHeader {
    public double financeBalance;//资金余额
    public double expendableFund;//可用资金
    public double outPurchaseAmount;//买入未交收金额
    public double soldUnpaidAmount;//卖出未交收金额
    public double unTradeFrozenAmount;//委托未成交冻结金额
    public String currencyName;//货币名称
    public double abnormalFrozenFunds;//异常冻结资金
    public double butFinancing;// 可融资金
    public double butAlsoMoney;// 可还资金
    public double marginAvailability;//保证金可用额度
    public char currencyCode;//币种代码
    public String busiAcctNo;//资金账号（第三方柜台）
    public double lineCredit;//授信额度
    public double residueLineCredit;//剩余授信额度
    public double marketValue;//担保证券市值
    public double goodMoney;//可取资金
    public double totalAmount;//正常查询－资产总额，查融资余额－负债总额
    public double guaranteeRatio;//维持担保比例
    public double avaBalance;//保证金可用余额
    public double refundableAmount;//可还款金额
    public double HKFundsAvailable;//港股可用资金

    @Override
    public int getResponseCount() {
        return 0;
    }
}
