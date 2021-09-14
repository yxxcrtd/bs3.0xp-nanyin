package com.joyintech.tams.valuation.interf.valuation.sztinterf.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ApplicationConfig {
	
	public static SztConnector connectorInit() throws IOException {
		SztConnector  connector= new SztConnector();
				
		Properties prop = new Properties();
		InputStream is = null;
		try{
			is =Thread.currentThread().getContextClassLoader().getResourceAsStream("sztInterfClientMsg.properties");
			prop.load(is);
			if(prop!=null) {
				connector.setAppId(prop.getProperty("fromAppId")==null?"":prop.getProperty("fromAppId"));
				connector.setUserId(prop.getProperty("fromUserId")==null?"":prop.getProperty("fromUserId"));
				connector.setAppPwd(prop.getProperty("appPwd")==null?"":prop.getProperty("appPwd"));
				connector.setMrIp(prop.getProperty("mrIp")==null?"":prop.getProperty("mrIp"));
				connector.setMrPort(Short.valueOf(prop.getProperty("mrPort")==null?"":prop.getProperty("mrPort")));
				connector.setMrIpBak(prop.getProperty("mrIpBak")==null?"":prop.getProperty("mrIpBak"));
				connector.setMrPortBak(Short.valueOf(prop.getProperty("mrPortBak")==null?"":prop.getProperty("mrPortBak")));
				connector.setBizTypeBySend(Byte.decode(prop.getProperty("bizType")==null?"":prop.getProperty("bizType")));
			}
			return connector;
		}finally {
			if(is != null){
				is.close();
			}
		}
	}
	
	public static SztMessage msgInit() throws IOException {
		SztMessage  msg= new SztMessage();
		InputStream is = null;
		Properties prop = new Properties();
		try{
			is = Thread.currentThread().getContextClassLoader().getResourceAsStream("sztInterfClientMsg.properties");
			prop.load(is);
		}finally {
			if (is != null){
				is.close();
			}
		}
		if(prop!=null) {
			msg.setFromAppId(prop.getProperty("fromAppId")==null?"":prop.getProperty("fromAppId"));
			msg.setFromUserId(prop.getProperty("fromUserId")==null?"":prop.getProperty("fromUserId"));
			msg.setToAppId(prop.getProperty("toAppId")==null?"":prop.getProperty("toAppId"));
			msg.setToUserId(prop.getProperty("toUserId")==null?"":prop.getProperty("toUserId"));
			msg.setUcBizType(Byte.decode(prop.getProperty("bizType")==null?"":prop.getProperty("bizType")));
		}
		msg.setUserData1("2");
		msg.setUserData2("3");
		msg.setCorrPkgId("");
		return msg;
	}
}
