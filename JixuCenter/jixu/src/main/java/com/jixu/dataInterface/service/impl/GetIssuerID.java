package com.jixu.dataInterface.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jixu.common.type.InterfaceConfig;
import com.jixu.dao.intf.IssuerDao;
import com.jixu.dataInterface.service.AdditionValFunc;
import com.orm.obj.Issuer;


@Service("getIssuerID")
public class GetIssuerID extends AdditionValFunc{
	
	@Autowired
	private IssuerDao issuerDao;
	
	private String funcname="获取发行商代码";
	
	public int getFuncType() {
		return InterfaceConfig.advanceType;
	}
	
	public boolean validateFiled(Object field) {
		return true;
	}
	
	public String fundUsefulDesc() {
		return "通过名字获取发行商代码 ";
	}
	
	public String getFuncName() {
		return funcname;
	}
	
	public String calFunc(String inputValue, HashMap<String, Object> colcfgMap) {
		String issuerName = genConvertFormula(inputValue, colcfgMap);
		String issuerID = "";
		List<Issuer> issuers = issuerDao.getIssuersByName(issuerName);
		if (issuers.size() > 0){
			issuerID = issuers.get(0).getIssuerID();
		}
		return issuerID;
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
