package com.jixu.adapter.func.advance;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Component;

import com.jixu.adapter.service.AdditionFunc;
import com.jixu.common.type.InterfaceConfig;


@Component
public class CalFuncNumberToCharFormat extends AdditionFunc{
	private String funcname="数字转字符格式";
	
	public int getFuncType() {
		return InterfaceConfig.advanceType;
	}
	
	public boolean validateFiled(Object field) {
		return true;
	}
	
	public String fundUsefulDesc() {
		return "样例(整数三位逗号分隔，小数保留四位): [A] | #,###.0000";
	}
	
	public String getFuncName() {
		return funcname;
	}
	
	public Object calFunc(String inputValue, HashMap<String, Object> colcfgMap) {
		String newValue=genConvertFormula(inputValue, colcfgMap);
		
		String[] strArr=newValue.split("\\|");
		String numstr=strArr[0].toString();
		if(numstr==null || numstr.toLowerCase().trim().equals("null")){
			numstr="0";
		}
		double number=Double.parseDouble(numstr);
		DecimalFormat format=new DecimalFormat(strArr[1].trim());
		String result=format.format(number);
		return result;
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
