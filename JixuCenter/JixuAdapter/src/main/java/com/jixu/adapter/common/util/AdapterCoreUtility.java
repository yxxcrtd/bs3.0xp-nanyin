package com.jixu.adapter.common.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.joyintech.framework.util.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *   适配器核心utility
 *
 * @author wangqidong
 * @version 1.0 2020年03月10日
 * @see AdapterCoreUtility
 * @since 1.0
 */
public class AdapterCoreUtility {
	/**
	 * 日志
	 */
	private static Logger logger = LoggerFactory.getLogger(AdapterCoreUtility.class);
	
    /**
     * @Description: 发送数据给目标系统
     * @create: 2020/1/16 16:20
     * @return: 
     * @throws IOException 
     */
    @SuppressWarnings("unchecked")
	public static Map<String, Object> send(Map<String, Object> data) throws IOException {
    	 // TODO  自主实现协议逻辑
    	Map<String, Object> res = new HashMap<String, Object>();
    	String requestJson = JsonUtils.object2Json(data);
    	//封装请求xml
 		String requestXml = GenerateXMLUtil.getRequestXml(requestJson);

 		//资管webservice地址
 		String webserviceUrl = "";
 		
 		try {
 			//读取配置文件，获取请求下游系统的接口地址
 			Properties prop = AdapterWebserviceIntefUtil.getProperties("webservice.properties");
 			if(prop!=null) {
				webserviceUrl = prop.getProperty("webseviceUrl")== null ? "" :prop.getProperty("webseviceUrl");
			}
 		} catch (IOException e) {
			logger.error(e.getMessage(),e);
 		}
 		// 调用下游接口
 		String result = AdapterHttpClientWebservice.send(requestXml, webserviceUrl);
    	
 		if(null!=result) {
 			String responseJson = GenerateXMLUtil.getResponseJson(result);
 			res = JsonUtils.json2Object(responseJson, Map.class);
 		}
    	return res;
    }
    
}
