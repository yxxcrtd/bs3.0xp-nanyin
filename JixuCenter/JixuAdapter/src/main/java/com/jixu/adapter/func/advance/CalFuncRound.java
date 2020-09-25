package com.jixu.adapter.func.advance;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Component;

import com.jixu.adapter.service.AdditionFunc;
import com.jixu.common.type.InterfaceConfig;
import com.jixu.common.util.RoundUtil;


@Component("CalFuncRound")
public class CalFuncRound extends AdditionFunc{
	private String funcname="四舍五入";
	
	public int getFuncType() {
		return InterfaceConfig.advanceType;
	}
	
	public boolean validateFiled(Object field) {
		return true;
	}
	
	public String fundUsefulDesc() {
		return "实例(保留三位,四舍五入): 123.123456,3";
	}
	
	public String getFuncName() {
		return funcname;
	}
	
	public Object calFunc(String inputValue, HashMap<String, Object> colcfgMap) {
		String fieldsStr=genConvertFormula(inputValue, colcfgMap);
		String[] lines=fieldsStr.split(",");
		double a=Double.valueOf(lines[0]);
		int b=Integer.valueOf(lines[1]);
		
		double c=RoundUtil.roundMoney(a, b);
		
		return String.valueOf(c);
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
