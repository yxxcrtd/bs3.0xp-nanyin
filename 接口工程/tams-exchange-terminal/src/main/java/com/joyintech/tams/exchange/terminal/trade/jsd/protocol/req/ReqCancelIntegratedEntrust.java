package com.joyintech.tams.exchange.terminal.trade.jsd.protocol.req;


import com.joyintech.tams.exchange.terminal.trade.jsd.protocol.annotation.ProtocolInfo;
import com.joyintech.tams.exchange.terminal.trade.jsd.protocol.resp.RespCancelEntrustHeader;

/*** 
 * @Description: 综合业务委托撤单
 * @Param:
 * @return:
 * @Author: MAXX
 * @Date: 2020/6/30
 */
@ProtocolInfo(functionCode = "341", respHeaderClass = RespCancelEntrustHeader.class)
public class ReqCancelIntegratedEntrust extends ReqHeader {

    public char market;     // 市场代码
    public String placeholder;// 暂时不用
    public String accountNum;//客户号

    public Integer contractId;  // 合同号
    public String entrustType;  // 委托方式
}
