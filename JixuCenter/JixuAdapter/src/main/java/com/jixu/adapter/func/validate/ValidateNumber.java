package com.jixu.adapter.func.validate;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Component;

import com.jixu.adapter.service.AdditionFunc;
import com.jixu.common.type.InterfaceConfig;

@Component("validateNumber")
public class ValidateNumber extends AdditionFunc{
	private String funcname="校验数字";
	
	public int getFuncType() {
		return InterfaceConfig.baseType;
	}
	
	public boolean validateFiled(Object field) {
		try{
			if(field instanceof Double)
				return true;
			else
				return false;
		}catch(NumberFormatException ne){
			return false;
		}
	}
	
	public String fundUsefulDesc() {
		// TODO Auto-generated method stub
		return null;
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
