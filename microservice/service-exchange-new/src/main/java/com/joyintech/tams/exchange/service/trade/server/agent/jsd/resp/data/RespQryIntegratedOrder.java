package com.joyintech.tams.exchange.service.trade.server.agent.jsd.resp.data;

/**
 * 大宗交易当日委托查询返回消息体-343
 *
 * @author lifangpeng
 * @date 2021/11/8
 */
public class RespQryIntegratedOrder {
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
     * 股东账号
     */
    public String gdAccount;
    /**
     * 买卖类别名称
     */
    public String sellTypeName;
    /**
     * 证券代码
     */
    public String stockCode;
    /**
     * 证券名称
     */
    public String stockName;
    /**
     * 委托数量
     */
    public double entrustVolume;
    /**
     * 委托价格
     */
    public double entrustPrice;
    /**
     * 合同号
     */
    public int contractId;
    /**
     * 成交数量
     */
    public double tradeVolume;
    /**
     * 成交价格
     */
    public double dealPrice;
    /**
     * 委托状态
     */
    public char entrustStatus;
    /**
     * 委托状态名称
     */
    public String entrustStatusName;
    /**
     * 委托时间
     */
    public String entrustTime;
    /**
     * 日期
     */
    public String date;
    /**
     * 交易所错误信息
     */
    public String errorMsg;
    /**
     * 为空
     */
    public String unUsed;
    /**
     * 市场代码
     */
    public char market;
    /**
     * 买卖类别标志
     */
    public char sellTypeFlag;
    /**
     * 为空
     */
    public String unUsed2;
    /**
     * 买卖类别名称
     */
    public String sellTypeNameNew;
    /**
     * 错误代码
     */
    public String errorNo;
    /**
     * 撤单数量
     */
    public double cancelNum;
    /**
     * 委托属性
     */
    public String entrustPropName;
    /**
     * 成交金额
     */
    public double tradeMoney;
    /**
     * 减持报告标志
     */
    public char reductionReportMark;
    /**
     * 客户确认标志
     */
    public char confirmMark;
    /**
     * 委托子业务
     */
    public String entrustChildBusiness;
    /**
     * 约定号
     */
    public String contractNo;
    /**
     * 对方交易单元
     */
    public String reciprocalTradeUnit;
    /**
     * 联系人
     */
    public String relationName;
    /**
     * 联系方式
     */
    public String relationTel;
    /**
     * 委托来源
     */
    public String entrustSource;
    /**
     * 特定股东管理系统流水号
     */
    public String specialGdLsh;
    /**
     * 报盘合同号
     */
    public String offerContractNo;
    /**
     * 本方交易员
     */
    public String ourTrader;
    /**
     * 对手方交易员
     */
    public String counterpartyTrader;


}
