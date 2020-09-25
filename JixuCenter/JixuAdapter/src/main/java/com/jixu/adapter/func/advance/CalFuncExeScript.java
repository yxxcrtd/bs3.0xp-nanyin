package com.jixu.adapter.func.advance;

import java.util.HashMap;
import java.util.List;

import javax.script.ScriptException;

import org.springframework.stereotype.Component;

import com.jixu.adapter.service.AdditionFunc;
import com.jixu.common.type.InterfaceConfig;


@Component("calFuncExeScript")
public class CalFuncExeScript extends AdditionFunc{
	private String funcname="执行脚本";
	
	public int getFuncType() {
		return InterfaceConfig.advanceType;
	}
	
	public boolean validateFiled(Object field) {
		return true;
	}
	
	public String fundUsefulDesc() {
		return "执行JavaScrpit支持的脚本";
	}
	
	public String getFuncName() {
		return funcname;
	}
	
	public Object calFunc(String inputValue, HashMap<String, Object> colcfgMap) throws ScriptException{
		String newValue=genConvertFormula(inputValue, colcfgMap);
		String returnVal=genExeScrit(newValue).toString();
		return returnVal;
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
