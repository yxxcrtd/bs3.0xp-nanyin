package com.jixu.adapter.service.export;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.jixu.common.util.DataConvert;

/** 
 * @ClassName 		ExportDataServiceImpl
 * @author  		Pan Wei 
 * @Date 			2017年4月25日 下午11:09:32 	
 * @Description		[]  
 */

public abstract class ExportDataServiceImpl implements ExportDataService{
	private static final Logger logger = LoggerFactory.getLogger(ExportDataServiceImpl.class);

	/**
	 * 把获取的查询结果,转换成HashMap List
	 * @param model
	 * @return
	 */
	protected List<HashMap<String, Object>> getFieldsHM(List<?> modelList){
		List<HashMap<String, Object>> fieldsHMList=new ArrayList<HashMap<String,Object>>();
		
		for(Object model:modelList){
			Class<?> classObj=model.getClass();
			Field[] fields = classObj.getDeclaredFields();
			HashMap<String, Object> modelHM=new HashMap<String, Object>();
			try{	
				for (Field field:fields) {  
					field.setAccessible(true);
					Object obj=field.get(model);
					if(obj==null){
						modelHM.put(field.getName(), "");
					}else{
						if(field.get(model) instanceof Double){
							String result=DataConvert.convertCashNoCalFormat((Double)field.get(model));
							modelHM.put(field.getName(), result);
						}else{
							modelHM.put(field.getName(), obj.toString());
						}
					}
					//field.get(model)==null?"":field.get(model).toString());
				}
			}catch(IllegalAccessException iae){
				logger.error("Error", iae);
				continue;
			}
			fieldsHMList.add(modelHM);
		}
		return fieldsHMList;
	}
}
