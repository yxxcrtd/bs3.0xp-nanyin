/**
 * 
 */
package com.jixu.adapter.func.filter;

import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import com.jixu.adapter.service.AdditionFunc;
import com.jixu.common.type.InterfaceConfig;

/** 
 * @ClassName 		FilterGreaterThan
 * @author  		Pan Wei 
 * @Date 			2016年12月9日 下午3:54:35 	
 * @Description		[]  
 */

@Component("filterNotMatchChar")
public class FilterNotMatchChar extends AdditionFunc {

	private String funcName="不匹配字符";
	
	@Override
	public String fundUsefulDesc() {
		return "不匹配某字符串";
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
		if(compareField.contains("*") && !compareField.contains(".*")){
			compareField=compareField.replaceAll("\\*", ".\\*");
		}
		Pattern pattern = Pattern.compile(compareField);
		Matcher matcher = pattern.matcher(field.toString());
		if(matcher.matches()){
			return false;
		}else{
			return true;
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
