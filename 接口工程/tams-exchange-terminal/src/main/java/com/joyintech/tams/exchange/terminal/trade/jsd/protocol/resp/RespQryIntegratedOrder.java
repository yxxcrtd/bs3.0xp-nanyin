package com.joyintech.tams.exchange.terminal.trade.jsd.protocol.resp;

//大宗交易委托主动拉取返回消息体
public class RespQryIntegratedOrder {
    public char flag;
    public String source;
    public String reqId;
    public String gdAccount;
    public String sellTypeName;//买卖类别名称
    public String stockCode;//证券代码
    public String stockName;//证券名称
    public double entrustVolume;// 委托数量
    public double entrustPrice;//委托价格
    public int contractId;  // 合同号
    public double tradeVolume;//成交数量
    public double dealPrice;       // 成交价格
    public char entrustStatus;//委托状态
    public String entrustStatusName;//委托状态名称
    public String entrustTime;//委托时间
    public String date;//日期
    public String errorMsg;//交易所错误信息
    public String unUsed;// 为空
    public char market;     // 市场代码
    public char sellTypeFlag; //买卖类别标志
    public String unUsed2;// 为空
    public String sellTypeNameNew;//买卖类别名称
    public String errorNo;// 错误代码
    public double cancelNum;//撤单数量
    public String entrustPropName;//委托属性
    public double tradeMoney;//成交金额
    public char reductionReportMark;//减持报告标志
    public char confirmMark;//客户确认标志
    public String entrustChildBusiness;//委托子业务
    public String contractNo;//约定号
    public String reciprocalTradeUnit;//对方交易单元
    public String relationName;//联系人
    public String relationTel;// 联系方式
    public String entrustSource;//委托来源
    public String specialGdLsh;//特定股东管理系统流水号
    public String offerContractNo;//报盘合同号
    public String ourTrader;//本方交易员
    public String counterpartyTrader;//对手方交易员


}
