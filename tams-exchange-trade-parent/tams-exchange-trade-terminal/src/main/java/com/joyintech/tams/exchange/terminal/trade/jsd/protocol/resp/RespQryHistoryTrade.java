package com.joyintech.tams.exchange.terminal.trade.jsd.protocol.resp;

//对账单返回消息体
public class RespQryHistoryTrade {
    public char flag;//请求应答标志
    public String source;//请求源地址
    public String reqId;//请求编号
    public String date;//成交日期
    public String gddm;  // 股东代码
    public String bsFlagName;    //买卖标志名称
    public String stockCode;//证券代码
    public String stockName;//证券名称
    public double entrustVolume;//成交数量
    public double entrustPrice;//成交价格
    public double tradeBalance;//成交金额
    public double changeAmount;//变动金额
    public double financeBalance;//资金余额
    public double stockBalance;//股票余额
    public double commission;//佣金
    public double printingCost;// 印花税
    public double transferCost;//过户费
    public double otherExpenses;// 其他费用
    public String tradeTime;//成交时间
    public String contractId;  // 合同编号
    public String dealNo;          // 成交编号（金仕达）
    public char entrustBs;//"1"买"2"卖
    public String serialNum;//流水号
    public double tradeVolume;//成交数量
    public double nationalDebt;//国债全
    public double accruedInterest;//每百元应计利息
    public double standardCommission;  //标准佣金
    public String businessTag;// 业务标志
    public String entrustPropName;// 委托属性
    public double clearingFee;//结算费用
    public String dealCurrency;// 交易币种
    public String settlementCurrencyType;//结算币种
    public double settlementCurrency;//结算汇率
    public double HKTradeFees;// 港股交易费
    public String newSerialNo;//成交编号
    public double TransactionFees;//交易征费
    public String remark;//  备注
    public String businessType;//委托业务类型
    public char market;  // 市场代码


}
