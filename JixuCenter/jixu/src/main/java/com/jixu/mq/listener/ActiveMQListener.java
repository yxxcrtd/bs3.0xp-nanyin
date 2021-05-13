//package com.jixu.mq.listener;
//
//import javax.annotation.Resource;
//import javax.jms.BytesMessage;
//import javax.jms.Destination;
//import javax.jms.JMSException;
//import javax.jms.MapMessage;
//import javax.jms.Message;
//import javax.jms.ObjectMessage;
//import javax.jms.Session;
//import javax.jms.TextMessage;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.jms.core.JmsTemplate;
//import org.springframework.jms.listener.SessionAwareMessageListener;
//
//
///**
// * @ClassName ActiveMQListener
// * @author Pan Wei
// * @Date 2017年10月20日 上午12:09:41
// * @Description []
// */
////@Component("activeMQListener")
//public class ActiveMQListener implements SessionAwareMessageListener<Message>{
//	private static final Logger logger = LoggerFactory.getLogger(ActiveMQListener.class);
//
//	@Resource(name = "activeMqJmsTemplate")
//	private JmsTemplate activeMqJmsTemplate;
//	@Resource(name = "sessionAwareQueue")
//	private Destination sessionAwareQueue;
//
//	@Resource(name="messagePublish")
//	private MessagePublish messagePublic;
//
//
//	public void onMessage(Message message, Session session) {
//		if(message instanceof TextMessage){
//			TextMessage msg = (TextMessage) message;
//			messagePublic.exeBean(msg);
//			try {
//				Thread.sleep(1000L);
//			} catch (InterruptedException e) {
//				logger.error("Error", e);
//			}
//
//		}else if(message instanceof MapMessage){
//			MapMessage mm=(MapMessage) message;
//			messagePublic.exeBean(mm);
//
//		}else if(message instanceof BytesMessage){
//			BytesMessage bm=(BytesMessage) message;
//			messagePublic.exeBean(bm);
//
//		}else{
//			ObjectMessage om=(ObjectMessage) message;
//			messagePublic.exeBean(om);
//			try {
//				System.out.println(om.getObject());
//			} catch (JMSException e) {
//				logger.error("Error", e);
//			}
//		}
//	}
//}
