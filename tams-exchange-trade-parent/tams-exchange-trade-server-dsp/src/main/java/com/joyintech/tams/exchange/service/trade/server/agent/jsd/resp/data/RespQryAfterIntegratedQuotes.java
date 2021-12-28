package com.joyintech.tams.exchange.service.trade.server.agent.jsd.resp.data;

/**
 * 盘后大宗交易行情查询返回消息体-346
 *
 * @author lifangpeng
 * @date 2021/11/8
 */
public class RespQryAfterIntegratedQuotes {
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
     * 证券代码
     */
    public String stockCode;
    /**
     * 证券简称
     */
    public String stockName;
    /**
     * 指令类别
     */
    public String instruction;
    /**
     * 记录号
     */
    public int recordNum;
    /**
     * 报价类型1
     */
    public String orderType1;
    /**
     * 价格1 收盘价格
     */
    public double price1;
    /**
     * 成交数量1
     */
    public double dealVolume1;
    /**
     * 成交金额1
     */
    public double dealMoney1;
    /**
     * 成交笔数1
     */
    public int dealCount1;
    /**
     * 买盘剩余数量1
     */
    public double buyRemainQuantity1;
    /**
     * 卖盘剩余数量1
     */
    public double sellRemainQuantity1;
    /**
     * 报价类型 2
     */
    public char quotationType2;
    /**
     * 价格2 加权平均价格
     */
    public double price2;
    /**
     * 成交数量2
     */
    public double dealVolume2;
    /**
     * 成交金额2
     */
    public double dealMoney2;
    /**
     * 成交笔数2
     */
    public int dealCount2;
    /**
     * 买盘剩余数量2
     */
    public double buyRemainQuantity2;
    /**
     * 卖盘剩余数量2
     */
    public double sellRemainQuantity2;
    /**
     * 更新时间
     */
    public String updateTime;
    /**
     * 备用文本
     */
    public String remark;

}
