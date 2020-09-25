package com.jixu.adapter.func.advance;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Component;

import com.jixu.adapter.service.AdditionFunc;
import com.jixu.common.type.InterfaceConfig;


@Component("CalFuncChartoNumber")
public class CalFuncChartoNumber extends AdditionFunc{
	private String funcname="数字去除特殊字符";
	
	public int getFuncType() {
		return InterfaceConfig.advanceType;
	}
	
	public boolean validateFiled(Object field) {
		return true;
	}
	
	public String fundUsefulDesc() {
		return "样例: [A]";
	}
	
	public String getFuncName() {
		return funcname;
	}
	
	public Object calFunc(String inputValue, HashMap<String, Object> colcfgMap) {
		String newValue=genConvertFormula(inputValue, colcfgMap);
		String finalStr="";
		if(newValue != null && " ".equals(newValue)){
		    finalStr = null;
		}else {
		    finalStr = newValue;
		}
		
		return finalStr;
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
}
