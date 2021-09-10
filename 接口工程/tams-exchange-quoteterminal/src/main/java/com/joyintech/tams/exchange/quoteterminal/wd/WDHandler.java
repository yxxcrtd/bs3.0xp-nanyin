package com.joyintech.tams.exchange.quoteterminal.wd;

import cn.com.wind.td.tdf.TDFClient;
import cn.com.wind.td.tdf.TDF_MSG;
import cn.com.wind.td.tdf.TDF_MSG_DATA;
import cn.com.wind.td.tdf.TDF_MSG_ID;
import com.joyintech.tams.exchange.common.proto.QuoteProto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * create By MAXX on 2020/11/3
 */

public class WDHandler implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(WDHandler.class);
    private TDFClient client;
    private WDClient wdClient;
    private ThreadLocal<QuoteProto.TickBean.Builder> threadLocalBuilder = ThreadLocal.withInitial(QuoteProto.TickBean::newBuilder);

    public WDHandler(TDFClient client, WDClient wdClient) {
        this.client = client;
        this.wdClient = wdClient;
    }

    @Override
    public void run() {
        while (!wdClient.getQuitFlag()) {
            TDF_MSG msg = client.getMessage(100);       //getMessage是取数据函数。参数含义是：等待多少ms还没数据可读就返回。 如果调用时已有数据可读，立刻返回
            if (msg == null)
                continue;
            switch (msg.getDataType()) {                           //消息分2类：系统消息（心跳，网络断开，网络连接结果，收到登陆响应，收到代码表，收到闭市，收到行情变更）
                //数据消息（行情，指数，期货，逐笔成交，逐笔委托，委托队列）。 使用getDataType获取消息类型
                //系统消息
                case TDF_MSG_ID.MSG_SYS_HEART_BEAT:
                    logger.info("收到心跳信息！");
                    break;
                case TDF_MSG_ID.MSG_SYS_DISCONNECT_NETWORK:
                    logger.info("网络断开！");
                    break;
                case TDF_MSG_ID.MSG_SYS_CONNECT_RESULT: {
                    TDF_MSG_DATA data = TDFClient.getMessageData(msg, 0);                  //getMessageData: TDF_MSG可能包含多条对应类型的消息，使用此函数取条N条
                    logger.info("网络连接结果：");
                    WDConverter.printConnectResult(data.getConnectResult());               //再使用对应方法取具体数据
                    break;
                }
                case TDF_MSG_ID.MSG_SYS_LOGIN_RESULT: {
                    TDF_MSG_DATA data = TDFClient.getMessageData(msg, 0);
                    WDConverter.printLoginResult(data.getLoginResult());
                    break;
                }
                case TDF_MSG_ID.MSG_SYS_CODETABLE_RESULT: {
                    logger.info("收到代码表！");
                    wdClient.setLoginFlag(true);
                    //TDF_MSG_DATA data = TDFClient.getMessageData(msg, 0);
                    //PrintHelper.printCodeTableResult(data.getCodeTableResult());
                    //printCodeTable();
                    break;
                }
                case TDF_MSG_ID.MSG_SYS_MARKET_CLOSE: {
                    TDF_MSG_DATA data = TDFClient.getMessageData(msg, 0);
                    WDConverter.printMarketClose(data.getMarketClose());
                    break;
                }
                case TDF_MSG_ID.MSG_SYS_QUOTATIONDATE_CHANGE: {
                    TDF_MSG_DATA data = TDFClient.getMessageData(msg, 0);
                    WDConverter.printDateChange(data.getDateChange());
                    break;
                }
                //数据消息
                case TDF_MSG_ID.MSG_DATA_MARKET:
                    for (int i = 0; i < msg.getAppHead().getItemCount(); i++) {
                        TDF_MSG_DATA data = TDFClient.getMessageData(msg, i);
                        QuoteProto.TickBean.Builder builder = threadLocalBuilder.get();
                        WDConverter.convertToTick(data.getMarketData(), builder);
                        wdClient.addTick(builder.build());
                    }
                    break;
                case TDF_MSG_ID.MSG_DATA_FUTURE:
                    for (int i = 0; i < msg.getAppHead().getItemCount(); i++) {
                        TDF_MSG_DATA data = TDFClient.getMessageData(msg, i);
                        QuoteProto.TickBean.Builder builder = threadLocalBuilder.get();
                        WDConverter.convertToTick(data.getFutureData(), builder);
                        wdClient.addTick(builder.build());
                    }
                    break;
                case TDF_MSG_ID.MSG_DATA_INDEX:
                    for (int i = 0; i < msg.getAppHead().getItemCount(); i++) {
                        TDF_MSG_DATA data = TDFClient.getMessageData(msg, i);
                        QuoteProto.TickBean.Builder builder = threadLocalBuilder.get();
                        WDConverter.convertToTick(data.getIndexData(), builder);
                        wdClient.addTick(builder.build());
                    }
                    break;
                default:
                    break;
            }
        }
        client.close();
    }
}
