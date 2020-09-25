package com.jixu.adapter.func.global;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jixu.adapter.service.AdditionFunc;
import com.jixu.common.type.InterfaceConfig;


@Service("interfaceGlobalSetValue")
public class GlobalSetValue extends AdditionFunc{
	private String funcname="设置全局变量";
	
	public int getFuncType() {
		return InterfaceConfig.globalType;
	}
	
	public boolean validateFiled(Object field) {
		return true;
	}
	
	public String fundUsefulDesc() {
		return "如果A的值等于B的值,则返回C的值。	样例:A,B,C";
	}
	
	public String getFuncName() {
		return funcname;
	}
	
	public String calFunc(String inputValue, HashMap<String, Object> colcfgMap) throws Exception{
		String inputLine=genConvertFormula(inputValue, colcfgMap);
		String[] lines=inputLine.split(",");
		if(lines.length==3){
			String a=lines[0];
			String b=lines[1];
			String c=lines[2];
			if(a.compareTo(b)==0){
				return c;
			}
		}
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
