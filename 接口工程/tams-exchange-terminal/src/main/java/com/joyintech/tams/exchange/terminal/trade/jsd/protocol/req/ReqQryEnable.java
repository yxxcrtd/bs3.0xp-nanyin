package com.joyintech.tams.exchange.terminal.trade.jsd.protocol.req;


import com.joyintech.tams.exchange.terminal.trade.jsd.protocol.annotation.ProtocolInfo;
import com.joyintech.tams.exchange.terminal.trade.jsd.protocol.resp.RespQryEnable;

/*** 
 * @Description: 最大委托数查询
 * @Param:
 * @return:
 * @Author: MAXX
 * @Date: 2020/7/2
 */
@ProtocolInfo(functionCode = "17", respHeaderClass = RespQryEnable.class)
public class ReqQryEnable extends ReqHeader {
    public char market;     // 市场代码
    public String placeholder;// 暂时不用
    public String gdAccount;    // 股东账号
    public String direction;// 买卖类别
    public double price;        // 委托价格
    public String stockCode;//证券代码
    public String entrustType;  // 委托方式


}
