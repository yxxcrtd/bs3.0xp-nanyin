package com.joyintech.tams.exchange.terminal.trade.jsd.protocol.resp;

//委托查询返回消息体
public class RespQryEntrustData {
    public char flag;
    public String source;
    public String reqId;
    public String gdAccount;
    public String bsName;
    public String entrustCode;
    public String entrustName;
    public double entrustVolume;
    public double entrustPrice;
    public int contractId;  // 合同号
    public double tradedVolume;
    public double tradedPrice;
    public char entrustStatusNoUsed;//委托状态 固定为 0
    public String tradeStatusName;//成交状态名称 第13域
    public String tradeTime;//委托时间
    public String date;//日期
    public String errorMsg;// 出错信息
    public char tradeStatus;//成交状态
    public char market;  // 市场代码
    public char sellTypeFlag; //买卖类别标志
    public char entrustStatus;//委托状态
    public String sellTypeName;//买卖类别名称
    public String errorCode;//错误代码
    public double cancelNum;//撤单数量
    public String entrustPropName;//委托属性
    public double tradeMoney;//成交金额
    public char unwindTag;//平仓标志
    public String unwindTagName;//平仓标志名称
    public String entrustChildBusiness;//委托子业务
    public String contractNo;//约定号
    public String reciprocalTradeUnit;//对方交易单元
    public int alsoNotesMode;//还券模式
    public String entrustType;//委托方式
    public String declareNum;//申报编号
    public String cancelType;//撤单状态
    public double frozenMoney;//冻结金额
    public int frozenNum;//冻结数量
    public String adverseSecuritiesAccount;//对方证券帐户
    public double referenceRate;//参考汇率
    public String settlementCurrency;//结算币种
    public String dealCurrency;// 交易币种
    public String securitiesName;//证券中文名称
    public String businessType;//委托业务类型
    public String serialNum;//特定股东管理系统流水号
    public String EntrustRemark;//委托备注
    public String securityType;//证券类别
}
