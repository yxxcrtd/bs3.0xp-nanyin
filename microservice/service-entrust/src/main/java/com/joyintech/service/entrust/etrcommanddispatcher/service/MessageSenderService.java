package com.joyintech.service.entrust.etrcommanddispatcher.service;

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
import com.joyintech.service.entrust.etrcommanddispatcher.dto.EtrCommandDispatcherTotalDto;
import com.joyintech.tams.invest.common.constantrds.RedisKeyEnum;

@Service("entrustMessageSenderService")
public class MessageSenderService {
	@Autowired
	private PipeWriteService<String, String, Object> writeService;
	
    private static final Logger logger = LoggerFactory.getLogger(MessageSenderService.class);
    @MessageSender(exchange = "exchange.direct.yitihua.instruction", routingKey = "yitihua.instruction.dispatch")
    public SimpleRabbitMessage pushCommandMsgToMQ(EtrCommandDispatcherTotalDto dto) {
        SimpleRabbitMessage defaultRabbitMessage = new SimpleRabbitMessage();
        String msgId = IDGenerator.generate();
        String dtoStr = JSON.toJSONString(dto);
        
    	Map<String,Object> info = new HashMap<>();
    	Map<String,Map<String,Object>> infoList = new HashMap<>();
        info.put(msgId, dtoStr);
        infoList.put(RedisKeyEnum.MQ指令信息推送.key(), info);
        writeService.writeList(infoList);
        logger.info("==========推送指令数据到场内系统============ ");
        logger.info("pushCommandMsgToMQ msgId："+msgId);
        logger.info("pushCommandMsgToMQ Message: "+dtoStr);

        defaultRabbitMessage.setId(msgId);
        defaultRabbitMessage.setMessage(msgId);
        return defaultRabbitMessage;
    }
}
