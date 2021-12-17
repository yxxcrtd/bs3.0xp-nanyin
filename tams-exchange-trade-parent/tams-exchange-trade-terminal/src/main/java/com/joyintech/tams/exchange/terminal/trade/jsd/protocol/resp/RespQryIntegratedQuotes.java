package com.joyintech.tams.exchange.terminal.trade.jsd.protocol.resp;

//综合行情查询返回消息体
public class RespQryIntegratedQuotes {
    public char flag;//请求应答标志
    public String source;//请求源地址
    public String reqId;//请求编号
    public int recordNum;//记录号
    public char market;  // 市场代码
    public String stockCode;//证券代码
    public double declarationQuantity;//申报数量
    public double declarationPrice;//申报价格
    public String businessClass;//业务类别
    public String instruction;//指令类别
    public String declarationOrderId;//申报合同序号
    public String tradeUnit;   //交易单元
    public String declarationDate;//申报日期
    public String tradeId; // 流水号
    public String cancellationsNo;//撤单编号
    public String relationName;//联系人
    public String relationTel;// 联系方式
    public String clearInstitutionCode;//结算机构代码
    public String declarationTime;//申报时间
    public String contractNo;//约定号
    public double referenceRate;//参考收益率
    public char recordStatus;//记录状态
    public String remark;//  备注
    public char termType;//期限类型
    public int backTerm;//购回期限
    public String timestamp;//变动时间戳
    public String productSetNum;//产品集编号
    public String productSetNo;// 产品集序号
    public double declarationQuantity2;// 申报数量2
    public double declarationPrice2;//申报价格2
    public double referenceRate2;//参考收益率2
    public String standbyTag;//备用标志


}
