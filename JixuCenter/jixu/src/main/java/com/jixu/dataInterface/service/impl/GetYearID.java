package com.jixu.dataInterface.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jixu.common.type.InterfaceConfig;
import com.jixu.common.types.CouponData;
import com.jixu.dataInterface.service.AdditionValFunc;

@Service("getYearID")
public class GetYearID extends AdditionValFunc{
	
	private String funcname="获取付款频率代码";
	
	public int getFuncType() {
		return InterfaceConfig.advanceType;
	}
	
	public boolean validateFiled(Object field) {
		return true;
	}
	
	public String fundUsefulDesc() {
		return "通过名字获取付款频率代码    样例:[年度]";
	}
	
	public String getFuncName() {
		return funcname;
	}
	
	public String calFunc(String inputValue, HashMap<String, Object> colcfgMap) {
		String frequency = genConvertFormula(inputValue, colcfgMap);
		HashMap<Integer, String> result = CouponData.intCpnfReqTypeHM;
		String id="";
		for (int frequencyID : result.keySet()) {
			if(result.get(frequencyID).equals(frequency)){
				id=Integer.toString(frequencyID);
				break;
			}
		}
		return id;
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
