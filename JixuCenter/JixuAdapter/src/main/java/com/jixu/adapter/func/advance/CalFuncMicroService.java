package com.jixu.adapter.func.advance;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.script.ScriptException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.jixu.adapter.common.util.JixuRestTemplate;
import com.jixu.adapter.service.AdditionFunc;
import com.jixu.common.type.InterfaceConfig;

@Component
public class CalFuncMicroService extends AdditionFunc{
	
	@Autowired
	private CalMicroServiceCache calMicroServiceCache;
	
	private String funcname="微服务访问(返回字符)";
	
	public int getFuncType() {
		return InterfaceConfig.advanceType;
	}
	
	public boolean validateFiled(Object field) {
		return true;
	}
	
	public String fundUsefulDesc() {
		return "通过微服务调用方法获取返回值。 样例: valService/getLastName | \"port\":\"[port]\",\"valDate\":\"[vDate]\"";
	}
	
	public String getFuncName() {
		return funcname;
	}
	
	//[交易日],天数,假期ID
	public Object calFunc(String inputValue, HashMap<String, Object> colcfgMap) throws ScriptException{
		String inputLine=genConvertFormula(inputValue, colcfgMap);
		return calMicroServiceCache.postRestTemlate(inputLine);
	}
	
	
	
	public boolean filterField(Object field, String compareField){
		return false;
	}
	
	@Override
	public String groupCal(String key, List<HashMap<String, Object>> sourceData)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Component
	class CalMicroServiceCache{
		//缓存注解不能在一个类的不同方法间使用，需要不同类之间调用，用aop实现缓存。因此这里用内部类方式实现
		@Autowired(required = false)
	    @Qualifier("loadBalancedRestTemplate")
		private JixuRestTemplate restTemplate;
		
		
		@Cacheable(cacheManager="jixuCacheManager", value="adapterMicroSerCache", key="#sendParam")
		public Object postRestTemlate(String sendParam) {
			//配置10秒缓存，10秒内参数相同，直接缓存中取值
			String[] arrs=sendParam.split("\\|");
			if(arrs.length!=2)
				return "";
			String url="http://"+arrs[0].trim();
			String params="{"+arrs[1].trim()+"}";
			Map<String, Object> paramsmap=JSONObject.parseObject(params, Map.class);
			
			String obj=restTemplate.postForObject(url, paramsmap, String.class);
			return obj;
		}
	}
}
