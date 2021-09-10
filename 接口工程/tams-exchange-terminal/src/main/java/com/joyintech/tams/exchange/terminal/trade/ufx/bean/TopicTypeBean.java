package com.joyintech.tams.exchange.terminal.trade.ufx.bean;

import com.google.common.base.Strings;
import com.joyintech.tams.exchange.terminal.trade.ufx.constant.TopicType;

/**
 * @Author: think
 * @Description:
 * @Date: Created in 13:26 2021/9/2
 */
public class TopicTypeBean {
    private String tradeTopicName;   //成交主推  -- 包含竞价成交、大宗成交
    private String entrustTopicName; // 委托主推 -- 仅包含竞价交易主推。  大宗、固收、协议回购无委托主推
    private String incomeTradeTopic; //固收成交主推 -- 包含固收、协议回购成交

    public TopicTypeBean() {
    }

    public TopicTypeBean(String tradeTopicName, String entrustTopicName, String incomeTradeTopic) {
        this.tradeTopicName = Strings.isNullOrEmpty(tradeTopicName) ? TopicType.tradeTopic : tradeTopicName;
        this.entrustTopicName = Strings.isNullOrEmpty(entrustTopicName) ? TopicType.entrustTopic : entrustTopicName;
        this.incomeTradeTopic = Strings.isNullOrEmpty(incomeTradeTopic) ? TopicType.incomeTradeTopic : incomeTradeTopic;
    }

    public String getTradeTopicName() {
        return tradeTopicName;
    }

    public void setTradeTopicName(String tradeTopicName) {
        this.tradeTopicName = tradeTopicName;
    }

    public String getEntrustTopicName() {
        return entrustTopicName;
    }

    public void setEntrustTopicName(String entrustTopicName) {
        this.entrustTopicName = entrustTopicName;
    }

    public String getIncomeTradeTopic() {
        return incomeTradeTopic;
    }

    public void setIncomeTradeTopic(String incomeTradeTopic) {
        this.incomeTradeTopic = incomeTradeTopic;
    }
}
