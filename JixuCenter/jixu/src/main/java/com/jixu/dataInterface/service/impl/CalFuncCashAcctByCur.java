package com.jixu.dataInterface.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jixu.common.exception.InterfaceException;
import com.jixu.common.type.InterfaceConfig;
import com.jixu.dataInterface.service.AdditionValFunc;
import com.orm.obj.BkCashAcct;


@Service("calFuncCashAcctByCur")
public class CalFuncCashAcctByCur extends AdditionValFunc{
	
	private String funcname="根据货币获取默认银行账户";
	
	public int getFuncType() {
		return InterfaceConfig.advanceType;
	}
	
	public boolean validateFiled(Object field) {
		return true;
	}
	
	public String fundUsefulDesc() {
		return "根据货币获取默认银行账户	样例:[港币的系统内部编号]";
	}
	
	public String getFuncName() {
		return funcname;
	}
	
	public String calFunc(String inputValue, HashMap<String, Object> colcfgMap) throws Exception{
		String currency=genConvertFormula(inputValue, colcfgMap);
		BkCashAcct cashacct=bkCashAcctDao.loadDefaultCashAcctByCur(currency);
		if(cashacct==null){
			throw new InterfaceException("找不到该货币对应的默认银行"+": "+currency);
		}
		return String.valueOf(cashacct.getId());
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
