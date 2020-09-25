package com.jixu.adapter.func.advance;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Component;

import com.jixu.adapter.service.AdditionFunc;
import com.jixu.common.type.InterfaceConfig;


@Component("CalSubString")
public class CalSubString extends AdditionFunc{
	private String funcname="获取部分字符串";
	
	public int getFuncType() {
		return InterfaceConfig.advanceType;
	}
	
	public boolean validateFiled(Object field) {
		return true;
	}
	
	public String fundUsefulDesc() {
		return "截取部分字符串.	\r\n	样例:	[SecID],1,3";
	}
	
	public String getFuncName() {
		return funcname;
	}
	
	public Object calFunc(String inputValue, HashMap<String, Object> colcfgMap) {
		String[] strArr = genConvertFormula(inputValue, colcfgMap).split(",");
		String str = "";
		if(strArr.length == 3){
			int start = Integer.parseInt(strArr[1]);
			int step = Integer.parseInt(strArr[2]);
			str = strArr[0].substring(start, step);
		}
		return str;
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
