package com.joyintech.service.bankuplink.ibu.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.joyintech.framework.rabbitmq.annotation.MessageSender;
import com.joyintech.framework.rabbitmq.entity.SimpleRabbitMessage;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.redis.core.base.PipeWriteService;
import com.joyintech.tams.common.base.entity.User;
import com.joyintech.tams.invest.common.constantrds.RedisKeyEnum;

@Service("backBankUplinkPublishService")
public class BankUplinkPublishService {
	@Autowired
	private PipeWriteService<String, String, Object> writeService;
	
    private static final Logger logger = LoggerFactory.getLogger(BankUplinkPublishService.class);
    @MessageSender(exchange = "exchange.fanout.yitihua.bankup", routingKey = "yitihua.bankup.dispatch")
    public SimpleRabbitMessage pushCommandMsgToMQ(String msg) {
        SimpleRabbitMessage defaultRabbitMessage = new SimpleRabbitMessage();
        String msgId = IDGenerator.generate();
        User user = new User();
        user.setCreateUser("amyli");
        user.setRealName(msg);
        String dtoStr = JSON.toJSONString(user);
        
    	Map<String,Object> info = new HashMap<>();
    	Map<String,Map<String,Object>> infoList = new HashMap<>();
        info.put(msgId, dtoStr);
        infoList.put(RedisKeyEnum.MQ指令信息推送.key(), info);
        writeService.writeList(infoList);
        logger.info("==========推送银行间回复消息数据到mq============ ");
        logger.info("pushBankUpMsgToMQ msgId:"+ msgId);
        logger.info("pushBankUpMsgToMQ message: "+ dtoStr);

        defaultRabbitMessage.setId(msgId);
        defaultRabbitMessage.setMessage(dtoStr);
        return defaultRabbitMessage;
    }
}
