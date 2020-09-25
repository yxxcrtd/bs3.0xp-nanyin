package com.joyintech.invest.interf.common.core.utility;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;

/**
 * 投管核心utility
 *
 * @author 王波
 * @version 1.0 2020年01月16日
 * @see InvestCoreUtility
 * @since 1.0
 */
public class InvestCoreUtility {
	
	
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
 			//读取配置文件，获取请求资管的接口地址
 			Properties prop = WebserviceIntefUtil.getProperties("webservice.properties");
 			webserviceUrl = prop.getProperty("webseviceUrl");
 		} catch (IOException e) {
 			e.printStackTrace();
 		}
 		// 调用资管接口
 		String result = HttpClientWebservice.send(requestXml, webserviceUrl);
    	
 		if(StringUtil.isNotEmpty(result)) {
 			res = JsonUtils.json2Object(result, Map.class);
 		}

    	return res;
    }
    
    

    /**
     * @description： 以下定义自定义接收方法
     * 1. 接收数据
     * 2. 将数据整理成标准报文
     * 3. 调用统一接收接口 recv     * 
     */
    
    
}
