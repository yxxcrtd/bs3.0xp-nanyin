package com.jixu.adapter.service.export;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import com.jixu.adapter.dao.intf.InterfaceAdapterDao;
import com.jixu.adapter.writer.WriteDataFile;
import com.jixu.common.pojo.InterfaceSource;
import com.jixu.common.util.FieldMeta;

@Service("exportDataSource")
public class ExportDataSource implements InitializingBean, ApplicationContextAware{
	
	private static final Logger logger = LoggerFactory.getLogger(ExportDataSource.class);
	
	@Resource(name="interfaceAdapterDao")
	private InterfaceAdapterDao interfaceSourceDao;
	
	private HashMap<String, ExportDataService> dataFuncMap;
	private ApplicationContext applicationContext;
	//private HashMap<Integer, String> descriptionMap;
	private HashMap<Integer, String> idClassMap;
	private HashMap<String, Integer> classIDMap;
	private HashMap<Integer, String> sourceNameMap;
	private HashMap<Integer, WriteDataFile> writeFileBeanHM;
    
    
	public void afterPropertiesSet() throws Exception {
		logger.info("开始初始化输出数据源...");
		Map<String, ExportDataService> dataSources = applicationContext.getBeansOfType(ExportDataService.class);
		//descriptionMap=new HashMap<Integer, String>();
		dataFuncMap=new HashMap<String, ExportDataService>();
		idClassMap=new HashMap<Integer, String>();
		classIDMap=new HashMap<String, Integer>();
		sourceNameMap=new HashMap<Integer, String>();
		writeFileBeanHM=new HashMap<Integer, WriteDataFile>();
		int funID=0;

		for (ExportDataService dataSourceBean : dataSources.values()) {
			funID++;
			String classname=dataSourceBean.getClass().getName();
			String[] namearr=classname.split("\\.");
			if(namearr.length>0){
				classname=namearr[namearr.length-1];
			}
			dataFuncMap.put(classname, dataSourceBean);
			idClassMap.put(funID,classname);
			sourceNameMap.put(funID, dataSourceBean.getSourceName());
			classIDMap.put(classname,funID);
		}
		
		Map<String, WriteDataFile> beanMap = applicationContext.getBeansOfType(WriteDataFile.class);
		for (WriteDataFile fileBean : beanMap.values()) {
			writeFileBeanHM.put(fileBean.getFileType(), fileBean);
        }
		logger.info("初始化输出数据源结束");
	}
	
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
	
	public HashMap<Integer, String> fetchModelFieldsDescHM(int sourceID){
		InterfaceSource source=interfaceSourceDao.loadEntity(InterfaceSource.class, sourceID);
		String classname=source.getExpSourceClass();
		ExportDataService exportbean=dataFuncMap.get(classname);
		
		HashMap<Integer, String> fieldsHM=new HashMap<Integer, String>();
		Class<?> modelClass=exportbean.getReturnClassModel();
		if(modelClass!=null){
			Field[] fields = modelClass.getDeclaredFields();  
			int fieldNum=0;
			
			for (Field field:fields) {  
				FieldMeta meta=field.getAnnotation(FieldMeta.class);
				try{
					fieldNum++;
					fieldsHM.put(fieldNum, meta.desc());
				}catch(NullPointerException npe){
					continue;
				}
			}
		}
		return fieldsHM;
	}
	
	public HashMap<Integer, String> fetchModelFieldsNameHM(int sourceID){
		InterfaceSource source=interfaceSourceDao.loadEntity(InterfaceSource.class, sourceID);
		String classname=source.getExpSourceClass();
		ExportDataService exportbean=dataFuncMap.get(classname);
		
		HashMap<Integer, String> fieldsHM=new HashMap<Integer, String>();
		Class<?> modelClass=exportbean.getReturnClassModel();
		if(modelClass!=null){
			Field[] fields = modelClass.getDeclaredFields();  
			int fieldNum=0;
			
			for (Field field:fields) {  
				fieldNum++;
				fieldsHM.put(fieldNum, field.getName());
			}
		}
		return fieldsHM;
	}
	
	public HashMap<String, Integer> fetchModelFieldNumberHM(int sourceID){
		InterfaceSource source=interfaceSourceDao.loadEntity(InterfaceSource.class, sourceID);
		String classname=source.getExpSourceClass();
		ExportDataService exportbean=dataFuncMap.get(classname);
		
		HashMap<String, Integer> fieldsHM=new HashMap<String, Integer>();
		Class<?> modelClass=exportbean.getReturnClassModel();
		if(modelClass!=null){
			Field[] fields = modelClass.getDeclaredFields();  
			int fieldNum=0;
			
			for (Field field:fields) {  
				fieldNum++;
				fieldsHM.put(field.getName(),fieldNum);
			}
		}
		return fieldsHM;
	}
	
	/**
	 * 返回所有的输出数据源
	 * @return
	 */
	public HashMap<Integer, String> getSourceNameHM(){
		return sourceNameMap;
	}
	
	public HashMap<Integer, String> getIdClassMap(){
		return idClassMap;
	}
	
	public HashMap<String, Integer> getClassIDMap(){
		return classIDMap;
	}
	
	public HashMap<Integer, WriteDataFile> getWriteFileBeanHM(){
		return writeFileBeanHM;
	}
	
	public HashMap<String, ExportDataService> getExpDataFuncMap(){
		return dataFuncMap;
	}
}
