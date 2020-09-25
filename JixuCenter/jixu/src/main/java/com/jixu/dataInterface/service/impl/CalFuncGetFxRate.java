package com.jixu.dataInterface.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jixu.common.type.InterfaceConfig;
import com.jixu.common.util.DateUtil;
import com.jixu.dataInterface.service.AdditionValFunc;


@Service("calFuncGetFxRate")
public class CalFuncGetFxRate extends AdditionValFunc{
	private String funcname="获取汇率";
	
	public int getFuncType() {
		return InterfaceConfig.advanceType;
	}
	
	public boolean validateFiled(Object field) {
		return true;
	}
	
	public String fundUsefulDesc() {
		return "获取汇率	样例:[日期],[投资组合货币编码],[美元货币编码],[投资组合id]";
	}
	
	public String getFuncName() {
		return funcname;
	}
	
	public String calFunc(String inputValue, HashMap<String, Object> colcfgMap) throws Exception{
		String inputLine=genConvertFormula(inputValue, colcfgMap);
		String[] lines=inputLine.split(",");
		String date=lines[0].replaceAll("-", "");
		String portCur=lines[1].toString();
		String fxCur=lines[2].toString();
		String portnum=lines[3];
		double fxrate=comService.loadFxrate(portnum,portCur, fxCur, DateUtil.convertStr2Date(date));
		return String.valueOf(fxrate);
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
