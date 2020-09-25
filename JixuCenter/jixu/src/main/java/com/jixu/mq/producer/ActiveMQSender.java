package com.jixu.mq.producer;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;

import javax.annotation.Resource;
import javax.jms.BytesMessage;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import com.jixu.common.type.JMSByteModel;
import com.jixu.common.type.JMSHashMapModel;
import com.jixu.common.type.JMSObjectModel;
import com.jixu.common.type.JMSStringModel;
import com.jixu.common.type.MsgFields;

/**
 * @ClassName ActiveMQProducer
 * @author Pan Wei
 * @Date 2017年10月19日 下午11:35:35
 * @Description []
 */
@Service("ActiveMQProducer")
public class ActiveMQSender {
	
	@Resource(name="activeMqJmsTemplate")
	private JmsTemplate activeMqJmsTemplate;
	
	@Resource(name="mQFactory")
	private MQFactory mqFactory;
	
	/**
	 * 发送字符消息
	 * @param model	字符串消息容器
	 */
	public void sendMessage(JMSStringModel model) {
		String topicName=model.getTopicName();
		activeMqJmsTemplate.send(topicName,new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				String str=model.getContent();
				return session.createTextMessage(str);
			}
		});

	}
	
	public void sendMessage(JMSByteModel model) {
		activeMqJmsTemplate.send(new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				String str=model.getContent();
				BytesMessage bytes=session.createBytesMessage();
				bytes.writeBytes(str.getBytes());
				return bytes;
			}
		});

	}
	
	public void sendMessage(JMSObjectModel model) {
		String topicName=mqFactory.returnTopicName(model);
		activeMqJmsTemplate.send(topicName,new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				return session.createObjectMessage((Serializable)model);
			}
		});
	}
	
	public void sendPublicMessage(JMSObjectModel model) {
		String topicName=MsgFields.publicJixuMsg;
		activeMqJmsTemplate.send(topicName,new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				return session.createObjectMessage((Serializable)model);
			}
		});
	}
	
	
	public void sendMessage(JMSHashMapModel model) {
		activeMqJmsTemplate.send(new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				HashMap contentmap=model.getContent();
				MapMessage mm=session.createMapMessage();
				Iterator it=contentmap.keySet().iterator();
				while(it.hasNext()){
					String name=it.next().toString();
					Object object=contentmap.get(name);
					if(object==null){
						mm.setString(name, "");
					}else{
						mm.setString(name, object.toString());
					}
				}
				return mm;
			}
		});
	}
}
