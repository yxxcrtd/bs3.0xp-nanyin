package com.joyintech.tams.exchange.service.trade.server.agent.jsd.resp.data;

/**
 * 成交查询返回消息体-122
 *
 * @author lifangpeng
 * @date 2021/11/8
 */
public class RespQryTradeData {
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
     * 日期
     */
    public String date;
    /**
     * 流水号
     */
    public int tradeId;
    /**
     * 市场代码
     */
    public char market;
    /**
     * 股东代码
     */
    public String gddm;
    /**
     * 买卖方向：1-买入，2-卖出
     */
    public char direction;
    /**
     * 买卖名称
     */
    public String bsName;
    /**
     * 成交类别：0-买卖，2-撤单
     */
    public char tradeKind;
    /**
     * 成交名称：买，卖，撤单
     */
    public String tradeName;
    /**
     * 成交类型:0-成交，1-废单，2-确认
     */
    public char tradeType;
    /**
     * 成交类型名称：成交、废单、确认
     */
    public String tradeTypeName;
    /**
     * 股票代码
     */
    public String entrustCode;
    /**
     * 股票名称
     */
    public String entrustName;
    /**
     * 成交数量
     */
    public double tradedVolume;
    /**
     * 成交价格
     */
    public double tradedPrice;
    /**
     * 成交金额
     */
    public double tradedAmount;
    /**
     * 委托编号
     */
    public int entrustId;
    /**
     * 成交笔数
     */
    public int tradeNum;
    /**
     * 成交时间
     */
    public String tradeTime;
    /**
     * 定位串
     */
    public String positionStr;
    /**
     * 申报编号
     */
    public String reportId;
    /**
     * 成交编号(交易所)
     */
    public String tradedId;
    /**
     * 委托方式
     */
    public String entrustType;
    /**
     * 解冻资金金额
     */
    public double unfrozenAmount;
    /**
     * 解冻证券数量
     */
    public double unfrozenVolume;

}
