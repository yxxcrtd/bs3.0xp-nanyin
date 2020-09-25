package com.jixu.dataInterface.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jixu.common.exception.InterfaceException;
import com.jixu.common.type.InterfaceConfig;
import com.jixu.dataInterface.service.AdditionValFunc;
import com.orm.obj.SecAcct;


@Service("calFuncAcctCodeBySecAcctSeatNumber")
public class CalFuncAcctCodeBySecAcctSeatNumber extends AdditionValFunc{
	private String funcname="证券账户机构获取证券账户";
	
	public int getFuncType() {
		return InterfaceConfig.advanceType;
	}
	
	public boolean validateFiled(Object field) {
		return true;
	}
	
	public String fundUsefulDesc() {
		return "根据证券账户机构获取证券账户	样例:[]";
	}
	
	public String getFuncName() {
		return funcname;
	}
	
	public String calFunc(String inputValue, HashMap<String, Object> colcfgMap) throws Exception{
		String seatNumber=genConvertFormula(inputValue, colcfgMap);
		SecAcct secAcct=secAcctDao.loadSecAcctBySeatNum(seatNumber);
		if(secAcct!=null){
			return secAcct.getAcctID();
		}else{
			throw new InterfaceException("找不到证券账户机构对应的证券账户: " + seatNumber);
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
