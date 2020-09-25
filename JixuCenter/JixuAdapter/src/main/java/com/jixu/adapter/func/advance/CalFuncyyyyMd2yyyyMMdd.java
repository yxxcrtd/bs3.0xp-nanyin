package com.jixu.adapter.func.advance;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Component;

import com.jixu.adapter.service.AdditionFunc;
import com.jixu.common.type.InterfaceConfig;
import com.jixu.common.util.DateUtil;


@Component("calFuncyyyyMd2yyyyMMdd")
public class CalFuncyyyyMd2yyyyMMdd extends AdditionFunc{
	private String funcname="转换日期Md";
	
	public int getFuncType() {
		return InterfaceConfig.advanceType;
	}
	
	public boolean validateFiled(Object field) {
		return true;
	}
	
	public String fundUsefulDesc() {
		return "转换日期	样例:2018/3/1----20180301";
	}
	
	public String getFuncName() {
		return funcname;
	}
	
	public Object calFunc(String inputValue, HashMap<String, Object> colcfgMap) throws Exception{
		String date=genConvertFormula(inputValue, colcfgMap);
		return DateUtil.convertD2YYYYMMDD(DateUtil.convertStr2DateYYYY_MM_DD2(date));
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
