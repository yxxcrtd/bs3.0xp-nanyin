package com.joyintech.tams.business.common.service;

import com.joyintech.framework.util.JsonUtils;
import com.joyintech.tams.business.common.dto.ManagerDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * mq消息发送与监听实现类
 */
@Service
@Transactional
public class PositionRuleService {
    private static Logger logger = LoggerFactory.getLogger(PositionRuleService.class);

    private static RabbitTemplate rabbitTemplate;

    private static ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);
    
    {
        fixedThreadPool.submit(new Runnable() {
            @Override
            public void run() {
                while (true){
                    receive();
                    try {TimeUnit.SECONDS.sleep(2L);} catch (InterruptedException i) {i.printStackTrace(); }
                }
            }
        });
    }
    
    public void sendMessage(ManagerDto managerDto){
        rabbitTemplate.send(managerDto.getExchange(), managerDto.getRoutingKey(), 
                toMessage(JsonUtils.object2Json(managerDto.getPositionRuleInterDto()), managerDto.getId()), 
                new CorrelationData(managerDto.getId()));
    }
    
    protected Message toMessage(Object message, String id) {
        MessageProperties properties = new MessageProperties();
        MessageConverter messageConverter = rabbitTemplate.getMessageConverter();
        properties.setDeliveryMode(MessageDeliveryMode.PERSISTENT);
        properties.setMessageId(id);
        properties.getHeaders().put("id", id);
        Message m = messageConverter.toMessage(message, properties);
        logger.info("MQ消息发送：{}", message.toString());
        return m;
    }


    private static Message receive(){
        Message msg = null;
        try {
            if (rabbitTemplate != null){
                msg = rabbitTemplate.receive(PositionFutureManager.QUEUE_POSITION_CALLBACK);
                if (msg != null){
                    PositionFutureManager.notify(msg);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            try {TimeUnit.SECONDS.sleep(1L);} catch (InterruptedException i) {i.printStackTrace(); }
        }
        return msg;
    }
    
    public static void executeListener(){
        fixedThreadPool.submit(new Runnable() {
            @Override
            public void run() {
                while (PositionFutureManager.futureMap.size() > 0){
                    Message msg = receive();
                    if (msg != null){
                        break;
                    }
                }
            }
        });
    }

    @Autowired
    public void setRabbitTemplate(RabbitTemplate rabbitTemplate) {
        PositionRuleService.rabbitTemplate = rabbitTemplate;
    }
}
