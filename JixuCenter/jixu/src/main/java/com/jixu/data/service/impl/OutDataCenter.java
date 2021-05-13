package com.jixu.data.service.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import com.jixu.common.type.JMSStringModel;
import com.jixu.common.types.WorkFlowStatus;
import com.jixu.common.util.DateUtil;
import com.jixu.data.service.OutDataInterface;
//import com.jixu.mq.producer.ActiveMQSender;
//import com.jixu.mq.producer.MQFactory;

/**
 * @ClassName OutDataFactory
 * @author Pan Wei
 * @Date 2017年11月2日 上午12:09:39
 * @Description []
 */

@Service
public class OutDataCenter implements InitializingBean, ApplicationContextAware {
	
	private static final Logger logger = LoggerFactory.getLogger(OutDataCenter.class);
	
	@Resource(name="taskExecutor")
	private ThreadPoolTaskExecutor taskExecutor; 
	
	//@Resource(name="mQFactory")
	//private MQFactory mqFactory;
	
	@Autowired
	private PublicDataFactory publicDataFactory;
	
	//@Resource(name="ActiveMQProducer")
	//private ActiveMQSender actSender;
	
	@Value("${jixu.dataCenter.synchFlag:0}")
	private int synchFlag;
	
	@Value("${jixu.dataCenter.startDate:2019-01-01}")
	private String startDate;
	
	@Value("${jixu.dataCenter.endDate:2999-12-31}")
	private String endDate;
	
	private ApplicationContext applicationContext;

	private List<OutDataInterface> outDataBeanList;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		logger.info("开始初始化输出数据中心...");
		outDataBeanList=new ArrayList<OutDataInterface>();
		Map<String, OutDataInterface> hm=applicationContext.getBeansOfType(OutDataInterface.class);
		hm.forEach((k,v)->outDataBeanList.add(v));
		logger.info("输出数据工初始化结束厂中心");
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
	
	public void exeDataSender(int status,String[] portnums, Date date){
		if(synchFlag==1){//同步数据
			if(status==WorkFlowStatus.finish){//状态是完成
				if(startDate.length()==10&&endDate.length()==10){//判断是否传入开始日期和结束日期 2017-07-20
					Date start=new Date(DateUtil.convertStr2Date2(startDate).getTime());
					Date end=new Date(DateUtil.convertStr2Date2(endDate).getTime());
					for(String portnum:portnums){
						if(start.equals(end)){
							exeDataSender(portnum, start);
						}else{
							while(!start.after(end)){//判断开始日期不在结束日期后
								exeDataSender(portnum, start);
								start=DateUtil.getNextSQLDate(start);
							}
						}
					}
				}else{
					for(String portnum:portnums){
						exeDataSender(portnum, date);
					}
				}
			}
			//if(status==WorkFlowStatus.process){//状态是撤销
			//	JMSStringModel model=new JMSStringModel();
			//	if(startDate.length()==10&&endDate.length()==10){//判断是否传入开始日期和结束日期
			//		for(String portnum:portnums){
			//			String content=mqFactory.returnDeleteContent2(portnum, startDate, endDate);
			//			model.setContent(content);
			//			String topicName=mqFactory.returnDeleteTopic(portnum);
			//			model.setTopicName(topicName);
			//			actSender.sendMessage(model);
			//		}
			//	}else{
			//		String strdate=DateUtil.convertD2YYYY_MM_DD(date);
			//		for(String portnum:portnums){
			//			String content=mqFactory.returnDeleteContent(portnum, strdate);
			//			model.setContent(content);
			//			String topicName=mqFactory.returnDeleteTopic(portnum);
			//			model.setTopicName(topicName);
			//			actSender.sendMessage(model);
			//		}
			//	}
			//}
		}else{ //不同步数据 return
			return; 
		}
	}
	
	/**
	 * 异步同步数据到消息中心
	 * @param portnum
	 * @param date
	 */
	private void exeDataSender(String portnum, Date date){
		for(OutDataInterface mqbean:outDataBeanList){
			TaskControler tControl=new TaskControler(mqbean, portnum, date);
			taskExecutor.execute(tControl);
		}
	}
	
	/**
	 * 异步同步公共数据到消息中心
	 * @param entityBean	公共源数据
	 */
	public void exePublicDataSender(Object entityBean){
		if(synchFlag==1){
			TaskPublicControler tControl=new TaskPublicControler(publicDataFactory, entityBean);
			taskExecutor.execute(tControl);
		}
	}
}
