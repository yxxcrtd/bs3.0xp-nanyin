package com.joyintech.tams.exchange.service.trade.server.agent.jsd.resp.data;

/**
 * 成交历史查询返回消息体-62
 *
 * @author lifangpeng
 * @date 2021/11/8
 */
public class RespQryHistoryTrade {
    /**
     * 请求应答标志
     */
    public char flag;
    /**
     * 请求源地址
     */
    public String source;
    /**
     * 请求编号
     */
    public String reqId;
    /**
     * 成交日期
     */
    public String date;
    /**
     * 股东代码
     */
    public String gddm;
    /**
     * 买卖标志名称
     */
    public String bsFlagName;
    /**
     * 证券代码
     */
    public String stockCode;
    /**
     * 证券名称
     */
    public String stockName;
    /**
     * 成交数量
     */
    public double entrustVolume;
    /**
     * 成交价格
     */
    public double entrustPrice;
    /**
     * 成交金额
     */
    public double tradeBalance;
    /**
     * 变动金额
     */
    public double changeAmount;
    /**
     * 资金余额
     */
    public double financeBalance;
    /**
     * 股票余额
     */
    public double stockBalance;
    /**
     * 佣金
     */
    public double commission;
    /**
     * 印花税
     */
    public double printingCost;
    /**
     * 过户费
     */
    public double transferCost;
    /**
     * 其他费用
     */
    public double otherExpenses;
    /**
     * 成交时间
     */
    public String tradeTime;
    /**
     * 合同编号
     */
    public String contractId;
    /**
     * 成交编号（金仕达）
     */
    public String dealNo;
    /**
     * "1"买"2"卖
     */
    public char entrustBs;
    /**
     * 流水号:8未日期+6位序号
     */
    public String serialNum;
    /**
     * 成交数量
     */
    public double tradeVolume;
    /**
     * 国债全
     */
    public double nationalDebt;
    /**
     * 每百元应计利息
     */
    public double accruedInterest;
    /**
     * 标准佣金
     */
    public double standardCommission;
    /**
     * 业务标志
     */
    public String businessTag;
    /**
     * 委托属性
     */
    public String entrustPropName;
    /**
     * 结算费用
     */
    public double clearingFee;
    /**
     * 交易币种
     */
    public String dealCurrency;
    /**
     * 结算币种
     */
    public String settlementCurrencyType;
    /**
     * 结算汇率
     */
    public double settlementCurrency;
    /**
     * 港股交易费
     */
    public double HKTradeFees;
    /**
     * 成交编号(交易所)
     */
    public String newSerialNo;
    /**
     * 交易征费
     */
    public double TransactionFees;
    /**
     * 备注
     */
    public String remark;
    /**
     * 委托业务类型
     */
    public String businessType;
    /**
     * 市场代码
     */
    public char market;


}
