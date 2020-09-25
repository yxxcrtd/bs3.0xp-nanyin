package com.jixu.dataInterface.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jixu.common.type.InterfaceConfig;
import com.jixu.dataInterface.service.AdditionValFunc;
import com.orm.obj.SecAcct;


@Service("calFuncDefaultCashAcctBySecAcct")
public class CalFuncDefaultCashAcctBySecAcct extends AdditionValFunc{
	private String funcname="根据证券账户查找银行现金默认主账户";
	
	public int getFuncType() {
		return InterfaceConfig.advanceType;
	}
	
	public boolean validateFiled(Object field) {
		return true;
	}
	
	public String fundUsefulDesc() {
		return "根据证券账户查找银行现金默认主账户";
	}
	
	public String getFuncName() {
		return funcname;
	}
	
	public String calFunc(String inputValue, HashMap<String, Object> colcfgMap) throws Exception{
		String secacct=genConvertFormula(inputValue, colcfgMap);
		SecAcct sec=secAcctDao.loadEntity(SecAcct.class, Integer.valueOf(secacct));
		return String.valueOf(sec.getDefaultCashAcct());
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
