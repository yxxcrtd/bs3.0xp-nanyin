package com.joyintech.tams.exchange.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.joyintech.background.component.common.pack.common.util.StringUtil;
import com.joyintech.framework.JoyinRestTemplate;
import com.joyintech.tams.exchange.common.bean.ResultBean;
import com.joyintech.tams.exchange.common.domain.Message;
import com.joyintech.tams.exchange.common.utils.SpringContextUtils;
import com.joyintech.tams.exchange.service.trade.scheduled.TokenJob;

public class MasterUtil {
	
	public static boolean isMaster() {
		return TokenJob.isMaster();
	}
	
	public static String getMasterUrl() {
    	String url = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest().getRequestURL().toString();
    	String queryString = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest().getQueryString();
    	if(StringUtil.isNotEmpty(queryString)) {
    		url += "?"+queryString;
    	}
		String masterAddress = TokenJob.getMasterAddress();
		String[] splitTemp = url.split("//");
		String masterUrl = splitTemp[0]+"//"+masterAddress+splitTemp[1].substring(splitTemp[1].indexOf("/"));
    	return masterUrl;
    }
	
	public static Message postForObject(Object paramObject) {
		JoyinRestTemplate restTemplate = SpringContextUtils.getBean("loadBalancedRestTemplate");
		return restTemplate.postForObject(MasterUtil.getMasterUrl() , paramObject, Message.class);
	}
	
	public static ResultBean postForObjectResultBean(Object paramObject) {
		JoyinRestTemplate restTemplate = SpringContextUtils.getBean("loadBalancedRestTemplate");
		return restTemplate.postForObject(MasterUtil.getMasterUrl() , paramObject, ResultBean.class);
	}
	
}
