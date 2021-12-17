package com.joyintech.tams.exchange.terminal.trade.jsd.protocol.req;


import com.joyintech.tams.exchange.terminal.trade.jsd.protocol.annotation.ProtocolInfo;
import com.joyintech.tams.exchange.terminal.trade.jsd.protocol.resp.RespIntegratedEntrustHeader;

import java.math.BigDecimal;

//综合业务委托确认 固收 债券质押式协议回购
@ProtocolInfo(functionCode = "340", respHeaderClass = RespIntegratedEntrustHeader.class)
public class ReqIntegratedEntrust extends ReqHeader {
    public char market;     // 市场代码
    public String placeHolder;  // 暂时不用
    public String accountNum;//客户号
    public String gdAccount; //证券帐号 股东账号
    public String entrustCode;    // 证券代码
    public String direction;// 买卖类型   1买 2卖
    public int volume;       // 委托数量
    public BigDecimal price;        // 委托价格
    public String entrustType;  // 委托方式
    public Integer entrustBatch; //委托批号 N
    public char entrustRiskFlag; //委托风险评测标志 N
    public String entrustSubBusiness; //委托子业务 Y
    public String appointNum;   //约定号 N
    public String dfjyUnit;   //对方交易单元 N
    public char entrustFlag;        // 客户确认标志
    public String relationName;//联系人
    public String relationTel;// 联系方式
    public char reductionTag;//特殊减持标志
    public String specialGdLsh;//特定股东管理系统流水号
    public String ourTrader;//本方交易员
    public String counterpartyTrader;//对手方交易员


}
