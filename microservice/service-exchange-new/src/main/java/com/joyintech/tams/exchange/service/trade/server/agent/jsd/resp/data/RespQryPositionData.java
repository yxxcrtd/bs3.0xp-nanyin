package com.joyintech.tams.exchange.service.trade.server.agent.jsd.resp.data;

/**
 * 持仓查询返回消息体-10
 *
 * @author lifangpeng
 * @date 2021/11/8
 */
public class RespQryPositionData {
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
     * 市场代码
     */
    public char market;
    /**
     * 股东账号
     */
    public String gdAccount;
    /**
     * 证券代码
     */
    public String stockCode;
    /**
     * 证券名称
     */
    public String stockName;
    /**
     * 余额(加上当日成交)
     */
    public double balance;
    /**
     * 可用余额
     */
    public double availableBalance;
    /**
     * 市值（当日）
     */
    public double marketValue;
    /**
     * 浮动盈亏（当日）
     */
    public double floatProfitLoss;
    /**
     * 成本价格
     */
    public double costPrice;
    /**
     * 行情最新价
     */
    public double latestPrice;
    /**
     * 昨日库存数量
     */
    public double yestInventoryQuantity;
    /**
     * 股票市值(昨日库存)
     */
    public double yestStockValue;
    /**
     * 浮动盈亏(昨日库存)
     */
    public double yestFloatProfitLoss;
    /**
     * 点评日期
     */
    public String commentDate;
    /**
     * 一句话点评信息
     */
    public String commentMsg;
    /**
     * 国债净价（不是国债为最新价）
     */
    public double netPrice;
    /**
     * 每百元应计利息（不是国债为0）
     */
    public double accruedInterest;
    /**
     * 买入均价
     */
    public double avePrice;
    /**
     * 清算后买入成本
     */
    public double purchaCost;
    /**
     * 可充抵保证金 可用余额份额
     */
    public double cashDeposit;
    /**
     * 是否开仓证券 0-否 1-是
     */
    public char openSecurities;
    /**
     * 总库存
     */
    public double totalInventory;
    /**
     * 总市值
     */
    public double totalValue;
    /**
     * 期初股份
     */
    public double initialStake;
    /**
     * 超限库存余额
     */
    public double excessInventoryBalance;
    /**
     * 汇率
     */
    public double rate;
    /**
     * 成本价港币
     */
    public double hkCost;
    /**
     * 买入平均价港币
     */
    public double aveBuyHk;
    /**
     * 买入在途数量
     */
    public String buyInTransitQuantity;
    /**
     * 卖出在途数量
     */
    public String soldInTransitQuantity;
    /**
     * 昨日预交收数量
     */
    public int yestQuantity;
    /**
     * 深圳跨市场全现金替代申购/赎回可用余额
     */
    public double szAllCash;
    /**
     * 产品简称
     */
    public String productAbbreviation;
    /**
     * 产品到期日
     */
    public String productEndDate;
    /**
     * 产品类型
     */
    public int productType;
    /**
     * 预期收益
     */
    public String expectedYield;
    /**
     * 证券类别
     */
    public String stockType;

}
