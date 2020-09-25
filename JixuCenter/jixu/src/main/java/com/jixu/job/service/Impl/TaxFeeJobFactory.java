package com.jixu.job.service.Impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import com.jixu.job.service.TaxFeeJobService;

/** 
 * @ClassName 		TaxFeeJobFactory
 * @author  		Pan Wei 
 * @Date 			2018年5月10日 上午11:16:23 	
 * @Description		[]  
 */

@Service
public class TaxFeeJobFactory implements InitializingBean, ApplicationContextAware{
	private static final Logger logger = LoggerFactory.getLogger(TaxFeeJobFactory.class);
	
	private ApplicationContext applicationContext;
	
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
	
	private static Map<String, TaxFeeJobService> taxFeeJobsMap;
	
	private Map<String, String> taxFeeJobsNameMap;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		logger.info("开始初始化税费任务工厂...");
		taxFeeJobsMap=applicationContext.getBeansOfType(TaxFeeJobService.class);
		
		taxFeeJobsNameMap=new HashMap<String, String>();
		for(Map.Entry<String, TaxFeeJobService> entry:taxFeeJobsMap.entrySet()){
			taxFeeJobsNameMap.put(entry.getKey(), entry.getValue().jobName());
		}
		logger.info("初始化税费任务工厂结束");
	}

	public static TaxFeeJobService createInstance(String classServiceName) {
		return taxFeeJobsMap.get(classServiceName);
	}
	
	public Map<String, String> getTaxFeeJobsNameMap(){
		return taxFeeJobsNameMap;
	}
}
