package com.jixu.adapter.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.jixu.adapter.service.AdditionFunc;

/** 
 * @ClassName 		InterfaceExcpetion
 * @author  		Pan Wei 
 * @Date 			2017年3月15日 上午10:51:26 	
 * @Description		[]  
 */

@Component("InterfaceException")
public class InterfaceExcpetion {
	private static final Logger logger = LoggerFactory.getLogger(InterfaceExcpetion.class);
	
	public AdditionFunc getExceptionClass(Exception e){
		StackTraceElement[] traces=e.getStackTrace();
		AdditionFunc funcClass=null;
		for(StackTraceElement trace:traces){
			if(trace.getClassName().startsWith("com.jixu.dataInterface.service.impl")){
				try{
					Object obj=Class.forName(trace.getClassName()).newInstance();
					if(obj instanceof AdditionFunc){
						funcClass=(AdditionFunc)obj;
						break; 
					}else{
						return null;
					}
				}catch(ClassNotFoundException ce){
					logger.error("Error", ce);
					return null;
				} catch (InstantiationException e1) {
					logger.error("Error", e1);
					return null;
				} catch (IllegalAccessException e1) {
					logger.error("Error", e1);
					return null;
				}
			}
		}
		return funcClass;
	}
}
