package com.jixu.data.service;

/** 
 * @ClassName 		OutPublicDataInterface
 * @author  		Pan Wei 
 * @Date 			2018年2月12日 下午2:55:19 	
 * @Description		[]  
 */

public interface OutPublicDataInterface {
	
	/**
	 * 把该实例转换格式后发给消息引擎
	 * @param obj
	 */
	public void sendResults(Object obj);
	
	/**
	 * 发送所有的数据到消息中心
	 */
	public void sendResults();
	
	/**
	 * 发送所有的数据到消息引擎。
	 * 用于刚开始建立数据中心，同步数据
	 * @return
	 */
	public Class<?> getReturnClass();
}
