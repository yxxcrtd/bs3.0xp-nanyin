/**
 * 
 */package com.jixu.dataInterface.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jixu.common.type.InterfaceConfig;
import com.jixu.dataInterface.service.AdditionValFunc;
import com.orm.obj.IssueInfo;
/** 
 * @ClassName 		CalFuncMatchSecurityID
 * @author  		Pan Wei 
 * @Date 			2016年12月7日 下午10:45:16 	
 * @Description		[]  
 */

@Service("calFuncMatchSecID")
public class CalFuncMatchSecID extends AdditionValFunc{	
	
	private String funcname="匹配系统证券代码";

	public int getFuncType() {
		return InterfaceConfig.advanceType;
	}
	
	public boolean validateFiled(Object field) {
		return true;
	}
	
	public String fundUsefulDesc() {
		return "把外部接口的证券代码转换为系统内部的证券编号。	\r\n	样例: [SecID]";
	}
	
	public String getFuncName() {
		return funcname;
	}
	
	public String calFunc(String inputValue, HashMap<String, Object> colcfgMap) {
		inputValue=getColumName(inputValue);
		String secID=colcfgMap.get(inputValue).toString();
		IssueInfo sec=issueInfoDao.loadSecurity(secID);
		return sec.getId();
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
