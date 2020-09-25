package com.jixu.dataInterface.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jixu.common.type.InterfaceConfig;
import com.jixu.dao.intf.CashFlowCfgDao;
import com.jixu.dataInterface.service.AdditionValFunc;
import com.orm.obj.CashFlowCfg;


@Service("getCashFlowID")
public class GetCashFlowID extends AdditionValFunc{
	@Autowired
	private CashFlowCfgDao cashFlowCfgDao;
	
	private String funcname="获取现金流ID";
	
	public int getFuncType() {
		return InterfaceConfig.advanceType;
	}
	
	public boolean validateFiled(Object field) {
		return true;
	}
	
	public String fundUsefulDesc() {
		return "通过名字获取现金流代码 ";
	}
	
	public String getFuncName() {
		return funcname;
	}
	
	public String calFunc(String inputValue, HashMap<String, Object> colcfgMap) {
		String cashFlowName = genConvertFormula(inputValue, colcfgMap);
		CashFlowCfg cfc=cashFlowCfgDao.loadCashFlowCfgByName(cashFlowName);
		String cfcID="0";
		if (cfc!=null){
			cfcID = cfc.getCashFlowID();
		}
		return cfcID;
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
