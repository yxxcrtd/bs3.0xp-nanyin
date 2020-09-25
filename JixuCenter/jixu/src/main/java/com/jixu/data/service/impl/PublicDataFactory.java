package com.jixu.data.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import com.jixu.data.service.OutPublicDataInterface;


/**
 * 该类用于分发公共数据，采用反射的方式自动把传进来的对象找到对应的处理类 
 * @author "taotaizhu"
 *
 */
@Service
public class PublicDataFactory implements InitializingBean,ApplicationContextAware{

	private static final Logger logger = LoggerFactory.getLogger(PublicDataFactory.class);
	
	public static HashMap<String, OutPublicDataInterface> publicDataMap = null;
	private ApplicationContext applicationContext;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		logger.info("开始初始化公共数据工厂...");
		publicDataMap=new HashMap<String, OutPublicDataInterface>();
		Map<String, OutPublicDataInterface> beanMap = applicationContext.getBeansOfType(OutPublicDataInterface.class);
		for (OutPublicDataInterface publicData : beanMap.values()) {
			publicDataMap.put(publicData.getReturnClass().getName(), publicData);
		}
		logger.info("初始化公共数据工厂结束");
	}
	
	public void sendMsg(Object obj){
		String className=obj.getClass().getName();
		if(className.contains("_")){
			className=className.substring(0, className.indexOf("_"));
		}
		if(publicDataMap.containsKey(className)){
			OutPublicDataInterface publicData=publicDataMap.get(className);
			publicData.sendResults(obj);
		}
	}
	
}
