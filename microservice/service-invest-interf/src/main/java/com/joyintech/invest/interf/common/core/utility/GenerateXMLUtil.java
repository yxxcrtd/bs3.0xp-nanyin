package com.joyintech.invest.interf.common.core.utility;

import java.io.IOException;
import java.util.Properties;

public class GenerateXMLUtil {
	
	public static String getRequestXml(String json) throws IOException{
		
		//读取配置文件，获取请求资管的接口地址
			Properties prop = WebserviceIntefUtil.getProperties("webservice.properties");
			//投管请求资管的验证用户名
		    String userName = prop.getProperty("userName");
		    //投管请求资管的验证密码
		    String word = prop.getProperty("word");
		
		// 把json串中的双引号转换为%
		//String json2 = json.replaceAll("\"","%");
		
		//拼接请求xml报文
		StringBuilder sb = new StringBuilder();
/*		sb.append("<soapenv:Envelope xmlns:q0=\"http://ws.join.com/\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">  ");
		sb.append("<soapenv:Header>");
		sb.append("</soapenv:Header>");
		sb.append("<soapenv:Body>");
		sb.append("<q0:sayHello>");
		sb.append("<arg0>");
		
		sb.append(json);

		sb.append("</arg0>");
		sb.append("</q0:sayHello>");
		sb.append("</soapenv:Body>");
		sb.append("</soapenv:Envelope>");*/
		
		sb.append("<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">  ");
		sb.append("<soap:Header>");
		sb.append("<SoapHeader xmlns=\"http://tams.fpp.joyintech.com/SoapHeader\">");
		sb.append("<an9898>sessionid</an9898>");
		sb.append("<bner08>");
		sb.append(userName);
		sb.append("</bner08>");
		sb.append("<bs89p7>");
		sb.append(word);
		sb.append("</bs89p7>");
		sb.append("<bs89p8>12.99.141.61</bs89p8>");
		sb.append("</SoapHeader>");
		sb.append("</soap:Header>");
		sb.append("<soap:Body>");
		sb.append("<ns2:recive xmlns:ns2=\"http://wsservice.investManage.interf.qrcb.tisaccel.joyintech.com/\">");
		sb.append("<arg0>");
		//投管传给资管的核心数据
		sb.append(json);

		sb.append("</arg0>");
		sb.append("</ns2:recive>");
		sb.append("</soap:Body>");
		sb.append("</soap:Envelope>");
		
		String xml =sb.toString(); 

		
		return xml ;
		
	}
	/**
	 * 获取webservice返回的json报文 add by pw
	 */
	public static String getResponseJson(String xml){
		
		String json = "";
		
		int start = xml.indexOf("<return>")+8;
		
		int end = xml.indexOf("</return>");
		
	    json = xml.substring(start,end);
		
		return json.replaceAll("&quot;","\"");
	}
	
}
