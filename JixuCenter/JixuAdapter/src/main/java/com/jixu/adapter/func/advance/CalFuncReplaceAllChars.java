package com.jixu.adapter.func.advance;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Component;

import com.jixu.adapter.service.AdditionFunc;
import com.jixu.common.type.InterfaceConfig;


@Component("calFuncReplaceAllChars")
public class CalFuncReplaceAllChars extends AdditionFunc{
	private String funcname="替换字符";
	
	public int getFuncType() {
		return InterfaceConfig.advanceType;
	}
	
	public boolean validateFiled(Object field) {
		return true;
	}
	
	public String fundUsefulDesc() {
		return "用逗号分隔,后面的字符替换前面的字符 .	\r\n	样例:	[SecID],a,b";
	}
	
	public String getFuncName() {
		return funcname;
	}
	
	public Object calFunc(String inputValue, HashMap<String, Object> colcfgMap) {
		String[] strArr = genConvertFormula(inputValue, colcfgMap).split(",");
		String str = "";
		if(strArr.length == 3){
			str = strArr[0].replace(strArr[1], strArr[2]);
		}else if(strArr.length == 2){ 
			str = strArr[0].replace(strArr[1], "");
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
	
	/**
	 * 
	 */
	public CalFuncReplaceAllChars() {
		// TODO Auto-generated constructor stub
	}
}
