package com.jixu.dataInterface.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jixu.common.exception.InterfaceException;
import com.jixu.common.type.InterfaceConfig;
import com.jixu.dataInterface.service.AdditionValFunc;
import com.orm.obj.BkCashAcct;


@Service("calFuncAcctCodeByBankSeatNumber")
public class CalFuncAcctCodeByBankSeatNumber extends AdditionValFunc{
	private String funcname="银行账户机构代码获取银行账户";
	
	public int getFuncType() {
		return InterfaceConfig.advanceType;
	}
	
	public boolean validateFiled(Object field) {
		return true;
	}
	
	public String fundUsefulDesc() {
		return "根据银行账户机构代码获取银行账户	样例:[]";
	}
	
	public String getFuncName() {
		return funcname;
	}
	
	public String calFunc(String inputValue, HashMap<String, Object> colcfgMap) throws Exception{
		String seatNumber=genConvertFormula(inputValue, colcfgMap);
		BkCashAcct bkacct=bkCashAcctDao.loadBankAcctByBankSeatNumber(seatNumber);
		if(bkacct.getId()!=0){
			return String.valueOf(bkacct.getId());
		}else{
			throw new InterfaceException("找不到银行账户机构代码对应的银行账户");
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
