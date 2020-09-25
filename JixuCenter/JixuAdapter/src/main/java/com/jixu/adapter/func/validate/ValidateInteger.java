package com.jixu.adapter.func.validate;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Component;

import com.jixu.adapter.service.AdditionFunc;
import com.jixu.common.type.InterfaceConfig;


@Component("validateInteger")
public class ValidateInteger extends AdditionFunc{
	private String funcname="校验整数";
	
	public int getFuncType() {
		return InterfaceConfig.baseType;
	}
	
	public boolean validateFiled(Object field) {
		try{
			if(field instanceof Integer);
				return true;
		}catch(NumberFormatException ne){
			return false;
		}
	}
	
	public String fundUsefulDesc() {
		// TODO Auto-generated method stub
		return "";
	}
	
	public String getFuncName() {
		return funcname;
	}
	
	public String calFunc(String inputValue, HashMap<String, Object> colcfgMap){
		// TODO Auto-generated method stub
		return null;
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
