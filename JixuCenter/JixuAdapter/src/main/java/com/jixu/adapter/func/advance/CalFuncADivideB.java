package com.jixu.adapter.func.advance;

import java.util.HashMap;
import java.util.List;

import javax.script.ScriptException;

import org.springframework.stereotype.Component;

import com.jixu.adapter.service.AdditionFunc;
import com.jixu.common.type.InterfaceConfig;
import com.jixu.common.util.RoundUtil;

@Component
public class CalFuncADivideB extends AdditionFunc{
	
	private String funcname="两个数值相除";
	
	public int getFuncType() {
		return InterfaceConfig.advanceType;
	}
	
	public boolean validateFiled(Object field) {
		return true;
	}
	
	public String fundUsefulDesc() {
		return "两个数值相除	\r\n	样例: [A]/[B]";
	}
	
	public String getFuncName() {
		return funcname;
	}
	
	//[交易日],天数,假期ID
	public Object calFunc(String inputValue, HashMap<String, Object> colcfgMap) throws ScriptException{
		String inputLine=genConvertFormula(inputValue, colcfgMap);
		String[] arrs=inputLine.split("/");
		double arr1=Double.parseDouble(arrs[0]);
		double arr2=Double.parseDouble(arrs[1]);
		return RoundUtil.round(arr1/arr2,15);
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
