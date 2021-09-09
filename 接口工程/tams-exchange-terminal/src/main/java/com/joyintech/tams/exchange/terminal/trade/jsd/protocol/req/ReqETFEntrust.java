package com.joyintech.tams.exchange.terminal.trade.jsd.protocol.req;

import com.joyintech.tams.exchange.terminal.trade.jsd.protocol.annotation.ProtocolInfo;
import com.joyintech.tams.exchange.terminal.trade.jsd.protocol.resp.RespETFEntrustData;

//ETF委托
@ProtocolInfo(functionCode = "202", respHeaderClass = RespETFEntrustData.class)
public class ReqETFEntrust extends ReqHeader {
    public char market;     // 市场代码
    public String placeHolder;  // 暂时不用
    public String accountNum;//客户号
    public String gdAccount;    // 股东账号
    public char entrustBs;//1：申购，2：赎回
    public String entrustCode;    // 基金代码
    public double volume;       // 委托数量
    public String entrustType;  // 委托方式
    public char replaceTag;//全现金替代标志
    public String overheadInfo;//附加信息
    public String overheadInfo2;//附加信息2
}
