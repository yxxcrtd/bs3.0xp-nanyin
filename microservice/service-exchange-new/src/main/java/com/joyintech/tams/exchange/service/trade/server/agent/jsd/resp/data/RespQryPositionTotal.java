package com.joyintech.tams.exchange.service.trade.server.agent.jsd.resp.data;

/**
 * 持仓查询返回合计数据-10
 *
 * @author lifangpeng
 * @date 2021/11/8
 */
public class RespQryPositionTotal {
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
     * 合计余额
     */
    public int balance;
    /**
     * 合计可用余额
     */
    public int availableBalance;
    /**
     * 合计市值
     */
    public double marketValue;
    /**
     * 合计浮动盈亏
     */
    public double floatProfitLoss;
    /**
     * 合计库存数量（昨日库存）
     */
    public int yestInventoryQuantity;
    /**
     * 合计股票市值(昨日库存)
     */
    public double yestStockValue;
    /**
     * 合计浮动盈亏(昨日库存)
     */
    public double yestFloatProfitLoss;


}
