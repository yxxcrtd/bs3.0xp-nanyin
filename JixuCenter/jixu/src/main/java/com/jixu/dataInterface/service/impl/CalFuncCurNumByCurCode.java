package com.jixu.dataInterface.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jixu.common.type.InterfaceConfig;
import com.jixu.dao.intf.FxCurrencyDao;
import com.jixu.dataInterface.service.AdditionValFunc;

@Service("calFuncCurNumByCurCode")
public class CalFuncCurNumByCurCode extends AdditionValFunc{
	
	private String funcname="根据货币获取系统内部货币编码";
	
	public int getFuncType() {
		return InterfaceConfig.advanceType;
	}
	
	public boolean validateFiled(Object field) {
		return true;
	}
	
	public String fundUsefulDesc() {
		return "根据货币获取系统内部货币编码	样例:[HKD]";
	}
	
	public String getFuncName() {
		return funcname;
	}
	
	public String calFunc(String inputValue, HashMap<String, Object> colcfgMap) throws Exception{
		String currency=genConvertFormula(inputValue, colcfgMap);
		String cur=comService.loadFxCurIDByCurCode(currency);
		return cur;
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
