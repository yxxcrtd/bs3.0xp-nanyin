package com.jixu.adapter.func.advance;

import java.util.HashMap;
import java.util.List;

import javax.script.ScriptException;

import org.springframework.stereotype.Component;

import com.jixu.adapter.service.AdditionFunc;
import com.jixu.common.type.InterfaceConfig;


@Component("calFuncNumber")
public class CalFuncNumber extends AdditionFunc{
	private String funcname="数值计算";
	
	public int getFuncType() {
		return InterfaceConfig.advanceType;
	}
	
	public boolean validateFiled(Object field) {
		return true;
	}
	
	public String fundUsefulDesc() {
		return "样例: [A]*2-[C]";
	}
	
	public String getFuncName() {
		return funcname;
	}
	
	public Object calFunc(String inputValue, HashMap<String, Object> colcfgMap) throws ScriptException{
		String newValue=genConvertFormula(inputValue, colcfgMap);
		String str=newValue.replace("null", "0");
		double doubleResult= genResultByFormula(str, 9);
		return String.valueOf(doubleResult);
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
