package com.jixu.dataInterface.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jixu.common.type.InterfaceConfig;
import com.jixu.dataInterface.service.AdditionValFunc;
import com.orm.obj.PortfolioHdr;


@Service("calFuncDefaultSecAcctByPort")
public class CalFuncDefaultSecAcctByPort extends AdditionValFunc{
	private String funcname="根据投资组合查找默认证券主账户";
	
	public int getFuncType() {
		return InterfaceConfig.advanceType;
	}
	
	public boolean validateFiled(Object field) {
		return true;
	}
	
	public String fundUsefulDesc() {
		return "根据投资组合查找默认证券主账户";
	}
	
	public String getFuncName() {
		return funcname;
	}
	
	public String calFunc(String inputValue, HashMap<String, Object> colcfgMap) throws Exception{
		String portfolio=genConvertFormula(inputValue, colcfgMap);
		PortfolioHdr por=portfolioHdrDao.loadPortfolio(portfolio);
		return String.valueOf(por.getDefaultSecAcct());
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
