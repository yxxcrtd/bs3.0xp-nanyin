package com.jixu.dataInterface.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jixu.common.type.InterfaceConfig;
import com.jixu.common.types.InterestCpnMo;
import com.jixu.dataInterface.service.AdditionValFunc;


@Service("getMonthID")
public class GetMonthID extends AdditionValFunc{
	
	private String funcname="获取付款月份代码";
	
	public int getFuncType() {
		return InterfaceConfig.advanceType;
	}
	
	public boolean validateFiled(Object field) {
		return true;
	}
	
	public String fundUsefulDesc() {
		return "通过名字获取付款月份代码    样例:[7月]";
	}
	
	public String getFuncName() {
		return funcname;
	}
	
	public String calFunc(String inputValue, HashMap<String, Object> colcfgMap) {
		String payMonth = genConvertFormula(inputValue, colcfgMap);
		HashMap<Integer, String> map = InterestCpnMo.quarterHM;
		HashMap<Integer, String> map1 = InterestCpnMo.semiAnnualHM;
		HashMap<Integer, String> map2 = InterestCpnMo.annualHM;
		map.putAll(map1);
		map.putAll(map2);
		int id=0;
		for (int monthID : map.keySet()) {
			if(map.get(monthID).equals(payMonth)){
				id=monthID;
				break;
			}
		}
		return Integer.toString(id);
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
