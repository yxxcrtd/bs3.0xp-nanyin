/**
 * 
 */
package com.jixu.adapter.func.filter;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Component;

import com.jixu.adapter.service.AdditionFunc;
import com.jixu.common.type.InterfaceConfig;

/** 
 * @ClassName 		FilterGreaterThan
 * @author  		Pan Wei 
 * @Date 			2016年12月9日 下午3:54:35 	
 * @Description		[]  
 */

@Component("filterNotEquqlChar")
public class FilterNotEquqlChar extends AdditionFunc {

	private String funcName="不等于(字符类型)";
	
	@Override
	public String fundUsefulDesc() {
		return "取不等于某个值的数据";
	}

	@Override
	public String getFuncName() {
		return funcName;
	}

	@Override
	public int getFuncType() {
		return InterfaceConfig.filterType;
	}
	
	@Override
	public boolean filterField(Object field, String compareField) throws Exception{
		String data1=field.toString();
		if(data1 == null){
			return false;
		}else if(data1 != compareField){
			return true;
		}else {
			return false;
		}
	}

	public boolean validateFiled(Object field) {
		return false;

	}

	@Override
	public String calFunc(String inputValue, HashMap<String, Object> colcfgMap) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String groupCal(String key, List<HashMap<String, Object>> sourceData)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
