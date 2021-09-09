package com.joyintech.tams.exchange.terminal.trade.jsd.protocol.req;


import com.joyintech.tams.exchange.terminal.trade.jsd.protocol.annotation.ProtocolInfo;
import com.joyintech.tams.exchange.terminal.trade.jsd.protocol.resp.RespEntrustData;

import java.math.BigDecimal;

//委托
@ProtocolInfo(functionCode = "3", respHeaderClass = RespEntrustData.class)
public class ReqEntrust extends ReqHeader {
    public char market;     // 市场代码
    public String placeHolder;  // 暂时不用
    public String gdAccount;    // 股东账号
    public String entrustCode;    // 证券代码
    public String direction;    // 买卖类型 XY x表示买卖 1买 2卖 Y表示市价类型 1第一种市价 2第二种市价
    public double volume;       // 委托数量
    public BigDecimal price;        // 委托价格

    public String entrustType;  // 委托方式
    public String yhdm;         // 银行代码 N
    public String yhzh;         // 银行账号 N
    public char rzrqMode;       // 融资融券模式 N
    public Integer entrustBatch; //委托批号 N
    public char entrustRiskFlag; //委托风险评测标志 N
    public String entrustSubBusiness; //委托子业务 N
    public String appointNum;   //约定号 N
    public String dfjyUnit;   //对方交易单元 N
    public Integer contractNum;     // 合同号 N
    public String bgjshyh;          // b股票结算会员号 N
    public char lowEntrustFlag;   // 低价委托确认标记 N
    public char entrustFlag;        // 委托确认标志
    public String otherEntrustAccount;  // 对方证券账户 N
    public String specialGdLsh;     //特定股东管理系统流水号 N
    public String attachInfo;       // 附加信息 N
    public String attachInfo2;      // 附加信息2 N


}
