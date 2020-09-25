package com.jixu.dataInterface.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.script.ScriptException;

import org.springframework.stereotype.Service;

import com.jixu.common.type.InterfaceConfig;
import com.jixu.dataInterface.service.AdditionValFunc;
import com.orm.obj.PortfolioHdr;


@Service("calFuncGetPortfolioNum")
public class CalFuncGetPortfolioNum extends AdditionValFunc{
	private String funcname="投资组合系统编号";
	
	public int getFuncType() {
		return InterfaceConfig.advanceType;
	}
	
	public boolean validateFiled(Object field) {
		return true;
	}
	
	public String fundUsefulDesc() {
		return "根据基金代码,获取系统内部编号.	\r\n	样例: [PortCode]";
	}
	
	public String getFuncName() {
		return funcname;
	}
	
	public String calFunc(String inputValue, HashMap<String, Object> colcfgMap) throws ScriptException{
		String column=getColumName(inputValue);
		PortfolioHdr portfolio=portfolioHdrDao.loadPortfolioByPortCode(colcfgMap.get(column).toString());
		String portnum="";
		if(portfolio!=null){
			portnum=portfolio.getId();
		}
		return portnum;
		
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
