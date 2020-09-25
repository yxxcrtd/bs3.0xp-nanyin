package com.jixu.dataInterface.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jixu.common.exception.InterfaceException;
import com.jixu.common.type.InterfaceConfig;
import com.jixu.dataInterface.service.AdditionValFunc;
import com.orm.obj.PortfolioHdr;

@Service("calFuncPortByAcctID")
public class CalFuncPortByAcctID extends AdditionValFunc{
	private String funcname="根据证券账户获取投资组合";
	
	public int getFuncType() {
		return InterfaceConfig.advanceType;
	}
	
	public boolean validateFiled(Object field) {
		return true;
	}
	
	public String fundUsefulDesc() {
		return "根据证券账户获取投资组合	样例:[]";
	}
	
	public String getFuncName() {
		return funcname;
	}
	
	public String calFunc(String inputValue, HashMap<String, Object> colcfgMap) throws Exception{
		String acctID=genConvertFormula(inputValue, colcfgMap);
		int AcctID=Integer.valueOf(acctID);
		PortfolioHdr por=portfolioHdrDao.loadPortByAcctID(AcctID);
		if(!por.getId().equals("")){
			return por.getId();
		}else{
			throw new InterfaceException("找不到证券账户对应的投资组合");
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
