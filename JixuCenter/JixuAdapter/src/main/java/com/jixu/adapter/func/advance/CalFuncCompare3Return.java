package com.jixu.adapter.func.advance;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Component;

import com.jixu.adapter.service.AdditionFunc;
import com.jixu.common.type.InterfaceConfig;


@Component("calFuncCompare3Return")
public class CalFuncCompare3Return extends AdditionFunc{
	private String funcname="(比较)小于返回值";
	
	public int getFuncType() {
		return InterfaceConfig.advanceType;
	}
	
	public boolean validateFiled(Object field) {
		return true;
	}
	
	public String fundUsefulDesc() {
		return "如果A<b,则返回C,否则返回D。		样例: A,B,C,D";
	}
	
	public String getFuncName() {
		return funcname;
	}
	
	public Object calFunc(String inputValue, HashMap<String, Object> colcfgMap) {
		String inputLine=genConvertFormula(inputValue, colcfgMap);
		String[] lines=inputLine.split(",");
		String a=lines[0];
		String b="";
		String c="";
		String d="";
		if(lines.length>=4) {
			b=lines[1];
			c=lines[2];
			d=lines[3];
		}else if(lines.length==3){
			b=lines[1];
			c=lines[2];
		}else if(lines.length==2) {
			b=lines[1];
		}
		
		if(a.compareTo(b)<0){
			return c;
		}else{
			return d;
		}
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
