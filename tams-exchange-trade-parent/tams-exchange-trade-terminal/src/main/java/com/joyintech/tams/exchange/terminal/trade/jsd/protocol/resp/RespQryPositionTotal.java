package com.joyintech.tams.exchange.terminal.trade.jsd.protocol.resp;

//持仓查询返回合计数据
public class RespQryPositionTotal {
    public char flag;
    public String source;
    public String reqId;
    public int balance;//合计余额
    public int availableBalance;//合计可用余额
    public double marketValue;//合计市值
    public double floatProfitLoss;//合计浮动盈亏
    public int yestInventoryQuantity;//合计库存数量（昨日库存）
    public double yestStockValue;//合计股票市值(昨日库存)
    public double yestFloatProfitLoss;//合计浮动盈亏(昨日库存)


}
