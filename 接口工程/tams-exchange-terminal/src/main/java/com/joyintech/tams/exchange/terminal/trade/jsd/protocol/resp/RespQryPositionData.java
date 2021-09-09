package com.joyintech.tams.exchange.terminal.trade.jsd.protocol.resp;

//持仓查询返回消息体
public class RespQryPositionData {
    public char flag;
    public String source;
    public String reqId;
    public char market;  // 市场代码
    public String gdAccount;    // 股东账号
    public String stockCode;    // 证券代码
    public String stockName;// 证券名称
    public double balance;//余额(加上当日成交)
    public double availableBalance;//可用余额
    public double marketValue;//市值
    public double floatProfitLoss;//浮动盈亏
    public double costPrice;//成本价格
    public double latestPrice;//行情最新价
    public double yestInventoryQuantity;//昨日库存数量
    public double yestStockValue;//股票市值(昨日库存)
    public double yestFloatProfitLoss;//浮动盈亏(昨日库存)
    public String commentDate;//点评日期
    public String commentMsg;// 一句话点评信息
    public double netPrice;//国债净价（不是国债为最新价）
    public double accruedInterest;//每百元应计利息（不是国债为0）
    public double avePrice; //买入均价
    public double purchaCost; //  清算后买入成本
    public double cashDeposit;// 可充抵保证金 可用余额份额
    public char openSecurities;//是否开仓证券 0-否 1-是
    public double totalInventory;//总库存
    public double totalValue;//总市值
    public double initialStake;//期初股份
    public double excessInventoryBalance;//超限库存余额
    public double rate;//汇率
    public double hkCost;//成本价港币
    public double aveBuyHk;// 买入平均价港币
    public String buyInTransitQuantity;//买入在途数量
    public String soldInTransitQuantity;// 卖出在途数量
    public int yestQuantity;//昨日预交收数量
    public double szAllCash;// 深圳跨市场全现金替代申购/赎回可用余额
    public String productAbbreviation;// 产品简称
    public String productEndDate;//产品到期日
    public int productType;//产品类型
    public String expectedYield;//  预期收益
    public String stockType;// 证券类别


}
