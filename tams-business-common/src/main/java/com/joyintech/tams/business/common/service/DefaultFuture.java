package com.joyintech.tams.business.common.service;

import com.joyintech.framework.rabbitmq.entity.SimpleRabbitMessage;
import com.joyintech.tams.business.common.dto.ManagerDto;
import com.joyintech.tams.business.common.exception.PositionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.function.Function;

public class DefaultFuture {
    private static Logger logger = LoggerFactory.getLogger(DefaultFuture.class);
    
    public DefaultFuture(String id, Long timeout) {
        this.id = id;
        this.timeout = timeout;
    }

    public Object waitObject = new Object();
    
    public boolean isDone = false;
    
    private Long timeout = null; // 阻塞超时时间
    
    private String id;
    
    private SimpleRabbitMessage simpleRabbitMessage;
    
    private Map<String, Object> mapMessage;
    public boolean doInvoker(Function f, ManagerDto managerDto) throws PositionException {
        
        try {
            if(PositionFutureManager.add(this)){
                // mq发送
                f.apply(null);
                
                synchronized (waitObject) {
                    // 阻塞 
                    long start = System.currentTimeMillis();
                    
                    waitObject.wait(timeout);

                    long end = System.currentTimeMillis();
                    if(end - start >= timeout){
                        // 超时重发次数
                        if (managerDto.getRevokeTimes() > 0){
                            for (int i=0;i<managerDto.getRevokeTimes();i++){
                                if(mapMessage == null){
                                    logger.info("MQ消息超时重发：{}", managerDto.getPositionRuleInterDto());
                                    f.apply(null);
                                    waitObject.wait(timeout);
                                }else {
                                    break;
                                }
                            }
                        }else {
                            throw new PositionException("头寸调用阻塞超时！");
                        }
                    }
                }
            }
            return true;
        } catch (PositionException e) {
            throw new PositionException(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            // 存在超时情况下删除，防止mq接收异常导致内存溢出
            this.isDone=true;
            PositionFutureManager.futureMap.remove(id);
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getTimeout() {
        return timeout;
    }

    public void setTimeout(Long timeout) {
        this.timeout = timeout;
    }

    public SimpleRabbitMessage getSimpleRabbitMessage() {
        return simpleRabbitMessage;
    }

    public void setSimpleRabbitMessage(SimpleRabbitMessage simpleRabbitMessage) {
        this.simpleRabbitMessage = simpleRabbitMessage;
    }

    public Map<String, Object> getMapMessage() {
        return mapMessage;
    }

    public void setMapMessage(Map<String, Object> mapMessage) {
        this.mapMessage = mapMessage;
    }
}
