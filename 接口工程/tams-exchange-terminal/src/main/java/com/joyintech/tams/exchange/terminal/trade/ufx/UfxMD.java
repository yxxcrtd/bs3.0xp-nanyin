package com.joyintech.tams.exchange.terminal.trade.ufx;

import com.hundsun.mcapi.MCServers;
import com.hundsun.mcapi.interfaces.ISubscriber;
import com.hundsun.mcapi.subscribe.MCSubscribeParameter;
import com.joyintech.tams.exchange.terminal.trade.ufx.bean.TopicTypeBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;

//消息订阅
public class UfxMD {
    private final static Logger logger = LoggerFactory.getLogger(UfxMD.class);

    private TopicTypeBean topicTypeBean;
    private String eventId;

    public UfxMD(TopicTypeBean topicTypeBean, String eventId) {
        this.topicTypeBean = topicTypeBean;
        this.eventId = eventId;
    }

    public void init() throws Exception {
        logger.info("初始化消息发布订阅组件 -- MCServers");
        MCServers.MCInit();
        logger.info("MCServers 消息发布订阅组件 -- 初始化完成");
    }


    //订阅-成交主推-证券成交回报
    public void reqSubTradeReturn(String account, Integer branch_no) throws Exception {
        ISubscriber subscriber = MCServers.GetSubscriber();
        MCSubscribeParameter subParam = new MCSubscribeParameter();
        subParam.SetTopicName(topicTypeBean.getTradeTopicName());
        byte[] data = eventId.getBytes(StandardCharsets.UTF_8);
        subParam.SetAppData(data);
        subParam.SetFromNow(true);
        subParam.SetReplace(false);
        subParam.SetFilter("branch_no", branch_no.toString());
        subParam.SetFilter("fund_account", account);
        int iRet = subscriber.SubscribeTopic(subParam, 3000);
        //返回iRet>0表示成功
        logger.info("订阅成交主推 account :{} , branch_no:{} ,ReqSubTradeReturn:{}", account, branch_no, iRet);
    }

    //订阅-成交主推-证券委托回报
    public void reqSubOrderReturn(String account, Integer branch_no) throws Exception {
        ISubscriber subscriber = MCServers.GetSubscriber();
        MCSubscribeParameter subParam = new MCSubscribeParameter();
        subParam.SetTopicName(topicTypeBean.getEntrustTopicName());
        byte[] data = eventId.getBytes(StandardCharsets.UTF_8);
        subParam.SetAppData(data);
        subParam.SetFromNow(true);
        subParam.SetReplace(false);
        subParam.SetFilter("branch_no", branch_no.toString());
        subParam.SetFilter("fund_account", account);
        int iRet = subscriber.SubscribeTopic(subParam, 3000);
        //返回iRet>0表示成功
        logger.info("订阅委托主推 account :{} , branch_no:{} ,ReqSubOrderReturn:{}", account, branch_no, iRet);
    }

    //订阅-固收平台成交主推
    public void reqSubIncomeTradeReturn(String account, Integer branch_no) throws Exception {
        ISubscriber subscriber = MCServers.GetSubscriber();
        MCSubscribeParameter subParam = new MCSubscribeParameter();
        subParam.SetTopicName(topicTypeBean.getIncomeTradeTopic());
        byte[] data = eventId.getBytes(StandardCharsets.UTF_8);
        subParam.SetAppData(data);
        subParam.SetFromNow(true);
        subParam.SetReplace(false);
        subParam.SetFilter("branch_no", branch_no.toString());
        subParam.SetFilter("fund_account", account);
        int iRet = subscriber.SubscribeTopic(subParam, 3000);
        //返回iRet>0表示成功
        logger.info("订阅固收平台成交主推 account :{} , branch_no:{} ,ReqSubOrderReturn:{}", account, branch_no, iRet);
    }
}
