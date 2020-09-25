package com.jixu.dataInterface.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.script.ScriptException;

import org.springframework.stereotype.Service;

import com.jixu.common.type.InterfaceConfig;
import com.jixu.dataInterface.service.AdditionValFunc;
import com.orm.obj.BkCashAcct;


@Service("calFuncGetBkAcctNum")
public class CalFuncGetBkAcctNum extends AdditionValFunc{
	
	private String funcname="银行帐号系统编号";
	
	public int getFuncType() {
		return InterfaceConfig.advanceType;
	}
	
	public boolean validateFiled(Object field) {
		return true;
	}
	
	public String fundUsefulDesc() {
		return "根据银行代码, 获取系统银行帐号编号.		\r\n	样例: [BkAcctCode]";
	}
	
	public String getFuncName() {
		return funcname;
	}
	
	public String calFunc(String inputValue, HashMap<String, Object> colcfgMap) throws ScriptException{
		String bkCashCode=genConvertFormula(inputValue, colcfgMap);;
		BkCashAcct cashacct=bkCashAcctDao.loadBankCashByBkCode(bkCashCode);
		int bkacct=0;
		if(cashacct!=null){
			bkacct=cashacct.getId();
		}
		return String.valueOf(bkacct);
		
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
