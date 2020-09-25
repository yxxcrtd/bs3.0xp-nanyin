package com.jixu.adapter.common.util;

import java.io.IOException;


public class GenerateXMLUtil {
	
		
		public static String getRequestXml(String json) throws IOException{
			
			//拼接请求xml报文
					StringBuilder sb = new StringBuilder();
					
					sb.append("<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">");
					sb.append("<soap:Header>");
					sb.append("<SoapHeader xmlns=\"http://tams.fpp.joyintech.com/SoapHeader\">");
					sb.append("<an9898>sessionid</an9898>");
					sb.append("<bner08>");
					sb.append("joyin");
					sb.append("</bner08>");
					sb.append("<bs89p7>");
					sb.append("joyin");
					sb.append("</bs89p7>");
					sb.append("<bs89p8>129.99.99.99</bs89p8>");
					sb.append("</SoapHeader>");
					sb.append("</soap:Header>");
					sb.append("<soap:Body>");
					sb.append("<ns2:receive xmlns:ns2=\"http://wsservice.investManage.interf.grcb.tisaccel.joyintech.com/\">");
					sb.append("<arg0>");
					//投管传给资管的核心数据
					sb.append(json);
					
					sb.append("</arg0>");
					sb.append("</ns2:receive>");
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

