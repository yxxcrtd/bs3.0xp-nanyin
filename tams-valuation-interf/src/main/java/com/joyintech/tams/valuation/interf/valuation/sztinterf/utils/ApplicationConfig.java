package com.joyintech.tams.valuation.interf.valuation.sztinterf.utils;

import java.util.Properties;

public class ApplicationConfig {
	
	public static SztConnector connectorInit() throws Exception{
		SztConnector  connector= new SztConnector();
				
		Properties prop = new Properties();
        prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("sztInterfClientMsg.properties"));
        
    	connector.setAppId(prop.getProperty("fromAppId"));
    	connector.setUserId(prop.getProperty("fromUserId"));
    	connector.setAppPwd(prop.getProperty("appPwd"));
    	connector.setMrIp(prop.getProperty("mrIp"));
    	connector.setMrPort(Short.valueOf(prop.getProperty("mrPort")));
    	connector.setMrIpBak(prop.getProperty("mrIpBak"));
    	connector.setMrPortBak(Short.valueOf(prop.getProperty("mrPortBak")));
    	connector.setBizTypeBySend(Byte.decode(prop.getProperty("bizType")));
    	
		return connector;
	}
	
	public static SztMessage msgInit() throws Exception{
		SztMessage  msg= new SztMessage();
		
		Properties prop = new Properties();
		prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("sztInterfClientMsg.properties"));
		
		msg.setFromAppId(prop.getProperty("fromAppId"));
		msg.setFromUserId(prop.getProperty("fromUserId"));
		msg.setToAppId(prop.getProperty("toAppId"));
		msg.setToUserId(prop.getProperty("toUserId"));
		msg.setUserData1("2");
		msg.setUserData2("3");
		msg.setCorrPkgId("");
		msg.setUcBizType(Byte.decode(prop.getProperty("bizType")));
		
		return msg;
	}
}
