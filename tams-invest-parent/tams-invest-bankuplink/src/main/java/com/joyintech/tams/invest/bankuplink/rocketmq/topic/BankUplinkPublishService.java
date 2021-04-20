/*
 * 文件名：BankUplinkPublishService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Jiaoxujin
 * 修改时间：2020年5月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.tams.invest.bankuplink.rocketmq.topic;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.rabbitmq.entity.SimpleRabbitMessage;
import com.joyintech.framework.web.socket.SocketService;
import com.joyintech.tams.common.base.entity.InfContractDetailfile;
import com.rabbitmq.client.Channel;

//import com.joyintech.mq.rocket.RocketMQPublishService;

/**
 * 银行间上行模块RocketMQ 发布消息工具服务<br />
 * 每个Group写一个PublishService
 * @author Jiaoxujin
 * @version 1.0 2020年5月18日
 */
/*@Service
public class BankUplinkPublishService extends RocketMQPublishService {
    @Value("${application.rocketmq.group.bankuplink.name:bankuplink}")
    private String groupName;
    
    @Value("${application.rocketmq.topic:quoteinterf}")
    private String topic;

    @Override
    protected String getGroupName() {
        return this.groupName;
    }

    *//**
     * 批量发送RocketMQ消息
     * @param msgList List<String>
     * @param tags String
     * @return boolean 全部成功返回true,否则返回false
     *//*
    public boolean sendQuoteInterfMsgList(List<String> msgList, String tags) {
        // 不是TopicQuoteInterfTag中的TAG拒发？
        return super.sendMessageList(topic, tags, msgList);
    }

//    *//**
//     * 同步发送单条消息到RocketMQ
//     * @param message String
//     * @param tags String
//     * @return boolean 发送成功返回true, 失败返回false
//     *//*
    public boolean sendQuoteInterfMsg(String message, String tags) {
        // 不是TopicQuoteInterfTag中的TAG拒发？
        return super.sendMessage(topic, tags, message);
    }
}
*/
@Service
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class BankUplinkPublishService extends BaseService<InfContractDetailfile> {

	private static final Logger logger = LoggerFactory.getLogger(BankUplinkPublishService.class);

	@Autowired(required = false)
	private SocketService socket;

	@RabbitListener(queues = "queue.bankup.trade")
	public void processFanout(SimpleRabbitMessage context, Message message, Channel channel) throws Exception {
		logger.info("【银行间上下行接收消息开始********************************】");
		String tradeMessageStr = (String) context.getMessage();
		try {
			if (tradeMessageStr != null) {
				logger.info("【银行间上下行消息接收】the message get from MQ is {}", tradeMessageStr);
				this.publishMessage(tradeMessageStr);
			} else {
				logger.info("【银行间上下行消息接收】the message get from MQ is null");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("银行间上下行消息接收失败-错误信息:{},消息内容:{}", e.getLocalizedMessage(), tradeMessageStr);
		}
	}

	public boolean publishMessage(String message) {
		logger.info("【银行间上下行发布消息开始********************************】");
		JSONObject jsonObj = JSONObject.parseObject(message);
		String jsonStr = JSONObject.toJSONString(jsonObj);
		String quoteId = jsonObj.getString("quoteId");
		quoteId = "cfets";
		try {
			logger.info("【银行间上下行发布消息路径:】" + "/" + quoteId + "/bankup/api");
			logger.info("【银行间上下行发布消息内容:】" + jsonStr);
			// 推送到socketClient
			socket.send("/" + quoteId + "/bankup/api", jsonStr);
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
		logger.info("【银行间上下行发布消息结束********************************】");
		return true;
	}
}