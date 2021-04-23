package com.joyintech.tams.exchange.terminal.trade.jsd.protocol.req;


import com.joyintech.tams.exchange.terminal.trade.jsd.protocol.annotation.ProtocolInfo;
import com.joyintech.tams.exchange.terminal.trade.jsd.protocol.resp.RespQryCustomerFinance;

/*** 
 * @Description: 查询资金余额和可用资金余额
 * @Param:
 * @return:
 * @Author: MAXX
 * @Date: 2020/7/2
 */
@ProtocolInfo(functionCode = "9", respHeaderClass = RespQryCustomerFinance.class)
public class ReqQryCustomerFinance extends ReqHeader {
    public char currencyCode;//货币代码
    public String placeholder;// 暂时不用
    public String accountNum;//客户号
    public String entrustType;  // 委托方式
    public char financingBalanceTag;//是否查融资余额


}
