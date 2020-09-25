package com.jixu.framework.msg;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.jixu.common.type.MsgContainer;
import com.jixu.common.type.MsgFields;
import com.jixu.common.type.MsgTemplate;
import com.jixu.common.util.ReturnResult;

@Service("msgFactory")
public class MsgFactory implements InitializingBean, ApplicationContextAware{
	
	private static final Logger logger = LoggerFactory.getLogger(MsgFactory.class);
	
	public static HashMap<String, String> fieldsTemplateHM=null;
	
	public static HashMap<String, String> msgTemplateHM=null;
	
	public static HashMap<String, String> MessageNameHM=null;
	
	/*public static HashMap<String, String> MessageFormHM=null;*/
	
	public static Map<String, MsgService> msgMap = null;
	
	//使用service名字作为消息头的唯一标志
	public static Map<String, MsgService> msgBeanMap = null;
	
    private ApplicationContext applicationContext;
	
	public MsgService createMsgAssembleBean(MsgContainer container) {
		String header=container.getHeader();
		return msgMap.get(header);
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		logger.info("开始消息工厂...");
		fieldsTemplateHM=new HashMap<String, String>();
		msgTemplateHM=new HashMap<String, String>();
		MessageNameHM=new HashMap<String, String>();
		msgMap=new HashMap<String, MsgService>();
		
		//初始化msg的消息
		Class msgTemClass=MsgTemplate.class;
		Field[] fields = msgTemClass.getDeclaredFields();

		HashMap<String, String> tmpHeaderHM=new HashMap<String, String>();
		HashMap<String, String> tmpFieldsHM=new HashMap<String, String>();
		
		
		for(Field field:fields){
			try{
				if(field.getType().equals(String.class)){
					String varname=field.getName();
					if(!varname.contains("_")){
						continue;
					}
					//消息在java文件中的名字,必须含有字符"_"
					String key=varname.substring(0, varname.lastIndexOf("_"));
					if(varname.toLowerCase().endsWith("header")){
						tmpHeaderHM.put(key, field.get(MsgTemplate.class).toString());
					}else if(varname.toLowerCase().endsWith("fields")){
						tmpFieldsHM.put(key, field.get(MsgTemplate.class).toString());
					}
				}
			}catch(Exception e){
				logger.error("Error", e);
			}
		}
		
		tmpHeaderHM.forEach((k,v)->{
			fieldsTemplateHM.put(v, tmpFieldsHM.get(k));
			msgTemplateHM.put(v, v+"("+tmpFieldsHM.get(k)+")");
		});
		
		
		//map header和bean的映射
		msgBeanMap = applicationContext.getBeansOfType(MsgService.class);
		for (Map.Entry<String, MsgService> entry: msgBeanMap.entrySet()) {
			
			MsgService fileBean=entry.getValue();
			msgMap.put(fileBean.msgHeader(), fileBean);
	
			//从交易消息里面反射消息头和消息名称,放入消息池
			if(fileBean.messageName()!=null){
				MessageNameHM.put(entry.getKey()+"-ADD", fileBean.messageName()+"-创建");
				MessageNameHM.put(entry.getKey()+"-UPDATE", fileBean.messageName()+"-更新");
				MessageNameHM.put(entry.getKey()+"-DELETE", fileBean.messageName()+"-删除");
			}
			//从交易消息里面反射消息头和消息内容,放入消息池
			/*if(fileBean.msgHeader()!=null && fileBean.msgFields()!=null){
				//if(!fieldsTemplateHM.containsKey(fileBean.msgHeader())){
				//接下去会去除 msgTemplate类里的msgFields
				fieldsTemplateHM.put(fileBean.msgHeader(), fileBean.msgFields());
				//}
				if(!msgTemplateHM.containsKey(fileBean.msgHeader())){
					msgTemplateHM.put(fileBean.msgHeader(), fileBean.msgHeader()+"("+fileBean.msgFields()+")");
				}
			}*/
		}
		logger.info("消息工厂初始化结束");
	}
	
	public static HashMap<String, String> getMsgFieldsTemplateHM(){
		return fieldsTemplateHM;
	}
	
	public static HashMap<String, String> getMsgTemplateHM(){
		return msgTemplateHM;
	}
	
	public static HashMap<String, String> getMessageNameHM(){
		return MessageNameHM;
	}
	
	public static Map<String, MsgService> getMsgMap(){
		return msgMap;
	}
	
	public static Map<String, MsgService> getMsgBeanMap(){
		return msgBeanMap;
	}
	
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}

	/**
	 * 执行消息
	 * @param container 容器的fieldsHM包含了map格式的消息
	 * @return	消息的执行结果和错误信息
	 */
	public static MsgContainer executeMsg(MsgContainer container) {
		HashMap<String, Object> msgHM=container.getFieldsHM();
		//支持dto消息
		String jsonMsg=JSONObject.toJSONString(msgHM);
		container.setJsonMsg(jsonMsg);
		//老消息   通过header取对应的实现类
		MsgService msgService=msgMap.get(msgHM.get(MsgFields.header));
		//如果老消息找不到对应的实现类   走新消息
		if(msgService==null){
			msgService=msgBeanMap.get(msgHM.get(MsgFields.header));
		}
		
		if(msgService==null){
			container.setCorrectFlag(false);
			container.setErrorCode(ReturnResult.header_not_exist);
			container.setErrorMsg(ReturnResult.getResultHM().get(ReturnResult.header_not_exist)+": "+msgHM.get(MsgFields.header));
		}else{
			msgService.executeMessage(container);
		}
		return container;
	}
	
	/**
	 * 通过service的名字作为header来调用相关的类，目前最新的方法
	 * @param container
	 * @return
	 */
	public static MsgContainer executeMsgBean(MsgContainer container) {
		HashMap<String, Object> msgHM=container.getFieldsHM();
		MsgService msgService=msgBeanMap.get(msgHM.get(MsgFields.header));
		if(msgService==null){
			container.setCorrectFlag(false);
			container.setErrorCode(ReturnResult.header_not_exist);
			container.setErrorMsg(ReturnResult.getResultHM().get(ReturnResult.header_not_exist)+": "+msgHM.get(MsgFields.header));
		}else{
			msgService.executeMessage(container);
		}
		return container;
	}
	
	public static MsgContainer executeMsgBeanNoTx(MsgContainer container) {
		HashMap<String, Object> msgHM=container.getFieldsHM();
		MsgService msgService=msgBeanMap.get(msgHM.get(MsgFields.header));
		if(msgService==null){
			container.setCorrectFlag(false);
			container.setErrorCode(ReturnResult.header_not_exist);
			container.setErrorMsg(ReturnResult.getResultHM().get(ReturnResult.header_not_exist)+": "+msgHM.get(MsgFields.header));
		}else{
			msgService.executeMessageNoTx(container);
		}
		return container;
	}
}
