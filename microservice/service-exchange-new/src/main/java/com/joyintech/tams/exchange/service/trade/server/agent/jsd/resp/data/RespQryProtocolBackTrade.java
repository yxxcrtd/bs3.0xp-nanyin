package com.joyintech.tams.exchange.service.trade.server.agent.jsd.resp.data;

/**
 * 大宗交易当日成交查返回消息体-344
 *
 * @author lifangpeng
 * @date 2021/11/8
 */
public class RespQryProtocolBackTrade {
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
     * 客户号
     */
    public String accountNum;
    /**
     * 市场代码
     */
    public char market;
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
    public int tradedVolume;
    /**
     * 超限成交数量
     */
    public String overrunTradedVolume;
    /**
     * 成交价格
     */
    public double tradedPrice;
    /**
     * 成交金额
     */
    public double tradedAmount;
    /**
     * 成交日期
     */
    public String date;
    /**
     * 成交时间
     */
    public String tradeTime;
    /**
     * 合同编号
     */
    public String contractId;
    /**
     * 成交编号
     */
    public String dealNo;
    /**
     * 业务类别
     */
    public char businessClass;
    /**
     * 委托属性
     */
    public String entrustPropName;


}
