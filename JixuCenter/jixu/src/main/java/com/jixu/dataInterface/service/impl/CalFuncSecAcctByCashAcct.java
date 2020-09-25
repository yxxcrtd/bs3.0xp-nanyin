package com.jixu.dataInterface.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jixu.common.exception.InterfaceException;
import com.jixu.common.type.InterfaceConfig;
import com.jixu.dao.intf.BkCashAcctDao;
import com.jixu.dataInterface.service.AdditionValFunc;
import com.orm.obj.BkCashAcct;


@Service("calFuncSecAcctByCashAcct")
public class CalFuncSecAcctByCashAcct extends AdditionValFunc{
	
	@Autowired
	private BkCashAcctDao bkCashAcctDao;
	
	private String funcname="根据银行账户获取证券账户";
	
	public int getFuncType() {
		return InterfaceConfig.advanceType;
	}
	
	public boolean validateFiled(Object field) {
		return true;
	}
	
	public String fundUsefulDesc() {
		return "根据银行账户获取证券账户	样例:[几许商业银行XXX现金账户系统内部编号]";
	}
	
	public String getFuncName() {
		return funcname;
	}
	
	public String calFunc(String inputValue, HashMap<String, Object> colcfgMap) throws Exception{
		String bkid=genConvertFormula(inputValue, colcfgMap);
		BkCashAcct cashacct=bkCashAcctDao.loadBankCash(Integer.valueOf(bkid));
		if(cashacct.getSecAcctCode()!=null){
			return cashacct.getSecAcctCode();
		}else{
			throw new InterfaceException("找不到该银行账户对应的证券账户"+": "+cashacct.getBankdesc());
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
