package com.jixu.mq.listener;

import java.util.HashMap;
import java.util.Map;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import com.jixu.common.type.JMSObjectModel;
import com.jixu.mq.service.MqService;

@Service("messagePublish")
public class MessagePublish implements InitializingBean, ApplicationContextAware {
	
	private static final Logger logger = LoggerFactory.getLogger(MessagePublish.class);
	
	public HashMap<String, MqService> beanHM=new HashMap<String, MqService>();
	private ApplicationContext applicationContext;
	

	@Override
	public void afterPropertiesSet() throws Exception {
		logger.info("开始初始化消息中心...");
		Map<String, MqService> msgMap = applicationContext.getBeansOfType(MqService.class);
		for (MqService fileBean : msgMap.values()) {
			beanHM.put(fileBean.destination(),fileBean);
		}
		logger.info("初始化消息中心结束");
	}
	
	public void exeBean(ObjectMessage message){
		try {
			JMSObjectModel model=(JMSObjectModel) message.getObject();
			/*HashMap<String, Object> mqMap=MQFactory.getMqHM();
			mqMap.get(model.getHeader());
			User u=(User) model.getContent();
			String msgname = message.getJMSDestination().toString();
			MqService mqexe=beanHM.get(msgname);
			mqexe.executeMQMsq(message);*/
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			logger.error("Error", e);
		}
	}
	public void exeBean(Message message){
		try {
			String msgname = message.getJMSDestination().toString();
			MqService mqexe=beanHM.get(msgname);
			mqexe.executeMQMsq(message);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			logger.error("Error", e);
		}
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
		
	}

	
	public HashMap<String, MqService> getBeanHM() {
		return beanHM;
	}
}
