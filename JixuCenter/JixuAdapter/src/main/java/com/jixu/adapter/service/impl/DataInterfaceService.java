package com.jixu.adapter.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import com.jixu.adapter.service.AdditionFunc;
import com.jixu.common.type.InterfaceConfig;


@Service("dataInterfaceService")
public class DataInterfaceService implements InitializingBean, ApplicationContextAware{
	private Map<String, AdditionFunc> dataFuncMap;
	private HashMap<Integer, String> idClassMap;
	private HashMap<String, Integer> classIDMap;
	private HashMap<Integer, String> validateMap;
	private HashMap<Integer, String> calculateMap;
	private HashMap<Integer, String> paramsMap;
	private HashMap<Integer, String> filterMap;
	private HashMap<Integer, String> descriptionMap;
	//用该类的service作为key
	private ApplicationContext applicationContext;
    
	private final Logger logger = LoggerFactory.getLogger(getClass());
    
	public void afterPropertiesSet() throws Exception {
		logger.info("开始初始化数据接口服务...");
		Map<String, AdditionFunc> funcMap = applicationContext.getBeansOfType(AdditionFunc.class);
		dataFuncMap = new HashMap<String, AdditionFunc>();
		idClassMap = new HashMap<Integer, String>();
		classIDMap=new HashMap<String, Integer>();
		validateMap=new HashMap<Integer, String>();
		calculateMap=new HashMap<Integer, String>(); 
		filterMap=new HashMap<Integer, String>();
		descriptionMap=new HashMap<Integer, String>();
		paramsMap=new HashMap<Integer, String>();
		validateMap.put(0, "-");
		calculateMap.put(0, "-");
		filterMap.put(0, "-");
		int funID=0;

		for (AdditionFunc dataFuncBean : funcMap.values()) {
			funID++;
			String classname=dataFuncBean.getClass().getName();
			String[] namearr=classname.split("\\.");
			if(namearr.length>0){
				classname=namearr[namearr.length-1];
			}
			dataFuncMap.put(classname, dataFuncBean);
			idClassMap.put(funID,classname);
			descriptionMap.put(funID, dataFuncBean.fundUsefulDesc());
			classIDMap.put(classname,funID);
			if(dataFuncBean.getFuncType()==InterfaceConfig.baseType){
				validateMap.put(funID, dataFuncBean.getFuncName());
			}else if(dataFuncBean.getFuncType()==InterfaceConfig.advanceType 
					|| dataFuncBean.getFuncType()==InterfaceConfig.groupType
					|| dataFuncBean.getFuncType()==InterfaceConfig.groupAdvanceType
					|| dataFuncBean.getFuncType()==InterfaceConfig.globalType){
				calculateMap.put(funID, dataFuncBean.getFuncName());
			}else if(dataFuncBean.getFuncType()==InterfaceConfig.filterType){
				filterMap.put(funID, dataFuncBean.getFuncName());
			}else if(dataFuncBean.getFuncType()==InterfaceConfig.paramsType || dataFuncBean.getFuncType()==InterfaceConfig.paramsCycleType
					|| dataFuncBean.getFuncType()==InterfaceConfig.paramsOut){
				paramsMap.put(funID, dataFuncBean.getFuncName());
			}
		}
		
		logger.info("初始化数据接口服务结束");
	}
	
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
	
	public AdditionFunc getAdditionFuncBean(String funcName) {
		return dataFuncMap.get(funcName);
	}

	/**  
	 * 获取基础校验功能的选择列表
	 * @return validateMap validateMap  
	 */
	
	public HashMap<Integer, String> getValidateMap() {
		return validateMap;
	}

	/**  
	 * 获取附加功能的选择列表
	 * @return calculateMap calculateMap  
	 */
	
	public HashMap<Integer, String> getCalculateMap() {
		return calculateMap;
	}
	
	public HashMap<Integer, String> getParamsMap(){
		return paramsMap;
	}

	public HashMap<Integer, String> getIDClassMap() {
		return idClassMap;
	}

	public HashMap<String, Integer> getClassIDMap() {
		return classIDMap;
	}
	
	public HashMap<Integer, String> getFilterMap() {
		return filterMap;
	}
	
	public HashMap<Integer, String> getDescriptionMap(){
		return descriptionMap;
	}
}
